package gui;

import bll.ProductsBLL;
import model.Products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static bll.ProductsBLL.checkEditInput;
/**
 * Represents a dialog for editing a product.
 */
public class EditProduct extends JDialog{
    private JComboBox idComboBox;
    private JTextField nameField;
    private JButton EDITButton;
    private JPanel EditProduct;
    private JTextField quantityField;
    private JTextField priceField;

    public EditProduct(JFrame parent){
        super(parent);
        setContentPane(EditProduct);
        setMinimumSize(new Dimension(500,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ProductsBLL productsBLL=new ProductsBLL();
        ArrayList<Integer> productsIDs=productsBLL.getProductIDs();
        for(Integer integer: productsIDs){
            idComboBox.addItem(String.valueOf(integer));
        }

        EDITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idComboBox.getSelectedItem()!=null){
                    int check=checkEditInput(nameField.getText(),quantityField.getText(),priceField.getText(),EditProduct.this);
                    if(check==1){
                        ProductsBLL productsBLL1=new ProductsBLL();
                        String get=idComboBox.getSelectedItem().toString();
                        Integer id=Integer.parseInt(get);

                        Products products=productsBLL1.findProduct(id);
                        if(!nameField.getText().isEmpty()){
                            products.setName(nameField.getText());
                        }
                        if(!quantityField.getText().isEmpty()){
                            Integer quantity=Integer.parseInt(quantityField.getText());
                            products.setQuantity(quantity);
                        }
                        if(!priceField.getText().isEmpty()){
                            Double price=Double.parseDouble(priceField.getText());
                            products.setPrice(price);
                        }
                        productsBLL1.updateProduct(products,EditProduct.this);
                    }
                }
            }
        });
        setVisible(true);
    }
}
