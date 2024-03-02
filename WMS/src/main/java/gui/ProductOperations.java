package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Represents a dialog for performing operations on products.
 */
public class ProductOperations extends JDialog{
    private JButton ADDNEWPRODUCTButton;
    private JPanel ProductOperations;
    private JButton EDITPRODUCTButton;
    private JButton DELETEPRODUCTButton;
    private JButton VIEWPRODUCTSButton;

    public ProductOperations(JFrame parent){
        super(parent);
        setContentPane(ProductOperations);
        setMinimumSize(new Dimension(500,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ADDNEWPRODUCTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewProduct addNewProduct=new AddNewProduct(null);
            }
        });
        EDITPRODUCTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditProduct editProduct=new EditProduct(null);
            }
        });

        DELETEPRODUCTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteProduct deleteProduct=new DeleteProduct(null);
            }
        });

        VIEWPRODUCTSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        VIEWPRODUCTSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ViewProducts viewProducts=new ViewProducts(null);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        setVisible(true);
    }
}
