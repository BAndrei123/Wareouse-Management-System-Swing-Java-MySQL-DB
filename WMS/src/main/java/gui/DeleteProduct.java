package gui;

import bll.OrdersBLL;
import bll.ProductsBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * Represents a dialog for deleting a product.
 */
public class DeleteProduct extends JDialog {
    private JComboBox deleteComboBox;
    private JButton DELETEButton;
    private JPanel DeleteProduct;

    public DeleteProduct(JFrame parent) {
        super(parent);
        setContentPane(DeleteProduct);
        setMinimumSize(new Dimension(500, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ProductsBLL productsBLL=new ProductsBLL();
        ArrayList<Integer> productsIDs=productsBLL.getProductIDs();
        for(Integer integer: productsIDs){
            deleteComboBox.addItem(String.valueOf(integer));
        }



        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(deleteComboBox.getSelectedItem()!=null){
                String get=deleteComboBox.getSelectedItem().toString();
                Integer id=Integer.parseInt(get);

                ProductsBLL productsBLL1=new ProductsBLL();
                productsBLL1.deleteProduct(id,DeleteProduct.this);

                OrdersBLL ordersBLL=new OrdersBLL();
                ordersBLL.deleteOrderFromProduct(id);

                deleteComboBox.removeItem(deleteComboBox.getSelectedItem());
                }
            }
        });
        setVisible(true);
    }
}
