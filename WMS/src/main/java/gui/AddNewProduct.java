package gui;
/**
 * Represents a dialog for adding a new product.
 */
import bll.ClientsBLL;
import bll.ProductsBLL;
import model.Clients;
import model.Products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static bll.ProductsBLL.checkInput;

public class AddNewProduct extends JDialog{
    private JTextField idField;
    private JButton ADDButton;
    private JPanel AddNewProduct;
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField priceField;

    public AddNewProduct(JFrame parent){
        super(parent);
        setContentPane(AddNewProduct);
        setMinimumSize(new Dimension(500,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);



        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int check=checkInput(idField.getText(),nameField.getText(),quantityField.getText(),priceField.getText(),AddNewProduct.this);
                if(check == 1){
                    int id=Integer.parseInt(idField.getText());
                    int quantity=Integer.parseInt(quantityField.getText());
                    double price=Double.parseDouble(priceField.getText());

                    Products products=new Products(id,nameField.getText(),quantity,price);
                    ProductsBLL productsBLL=new ProductsBLL();
                    productsBLL.insertProduct(products,AddNewProduct.this);
                }
            }
        });
        setVisible(true);
    }
}
