package gui;

import bll.ClientsBLL;
import bll.OrdersBLL;
import bll.ProductsBLL;
import connection.ConnectionFactory;
import dao.AbstractDAO;
import model.Bill;
import model.Clients;
import model.Orders;
import model.Products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static bll.OrdersBLL.checkInput;
/**
 * Represents a dialog for performing operations on orders.
 */
public class OrdersOperations extends JDialog{
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private JTextField idField;
    private JComboBox clientIDcombo;
    private JButton placeOrderButton;
    private JPanel OrderOperations;
    private JComboBox productIDcombo;
    private JTextField quantityField;

    public OrdersOperations(JFrame parent){
        super(parent);
        setContentPane(OrderOperations);
        setMinimumSize(new Dimension(500,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ClientsBLL clientsBLL1=new ClientsBLL();
        ArrayList<Integer> clientsIDs=clientsBLL1.getClientsIDs();
        for(Integer integer: clientsIDs){
            clientIDcombo.addItem(String.valueOf(integer));
        }

        ProductsBLL productsBLL=new ProductsBLL();
        ArrayList<Integer> productsIDs=productsBLL.getProductIDs();
        for(Integer integer: productsIDs){
            productIDcombo.addItem(String.valueOf(integer));
        }

        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int check= checkInput(idField.getText(),quantityField.getText(),OrdersOperations.this);
                if(check==1 && clientIDcombo.getSelectedItem()!=null && productIDcombo.getSelectedItem()!=null){

                    String getClient=clientIDcombo.getSelectedItem().toString();
                    Integer idClient=Integer.parseInt(getClient);
                    Clients clients=clientsBLL1.findClient(idClient);

                    String getProduct=productIDcombo.getSelectedItem().toString();
                    Integer idProduct=Integer.parseInt(getProduct);
                    Products products=productsBLL.findProduct(idProduct);

                    Integer idOrder=Integer.parseInt(idField.getText());
                    Integer quantity=Integer.parseInt(quantityField.getText());

                    if(quantity > products.getQuantity()){
                        JOptionPane.showMessageDialog(OrderOperations,"The inserted quantity is bigger than the stock","Try again",JOptionPane.ERROR_MESSAGE);

                    }
                    else if(quantity<=products.getQuantity()){
                        Double totalPrice=quantity*products.getPrice();
                        Integer newQuantity=products.getQuantity()-quantity;
                        Orders orders=new Orders(idOrder,idClient,idProduct,quantity,totalPrice);
                        Bill bill=new Bill(idOrder,idClient,idProduct,quantity,totalPrice);


                        Connection connection = null;
                        PreparedStatement statement = null;
                        ResultSet resultSet = null;
                        String query="insert into logtable values(" +bill.id()+ ","+bill.IDclient()+","+bill.IDproduct()+","+bill.quantity()+","+bill.price()+")";
                        try{
                            connection= ConnectionFactory.getConnection();
                            statement=connection.prepareStatement(query);
                            statement.executeUpdate();
                        }catch (SQLException a) {
                            LOGGER.log(Level.WARNING,   a.getMessage());
                        } finally {
                            ConnectionFactory.close(resultSet);
                            ConnectionFactory.close(statement);
                            ConnectionFactory.close(connection);
                        }

                        OrdersBLL ordersBLL=new OrdersBLL();

                        int ok = ordersBLL.insertOrder(orders,OrdersOperations.this);
                        if(ok==1) {
                            products.setQuantity(newQuantity);
                            productsBLL.updateProduct2(products, OrdersOperations.this);
                        }
                    }
                }
            }
        });
        setVisible(true);
    }
}
