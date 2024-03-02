package gui;

import bll.ClientsBLL;
import bll.ProductsBLL;
import model.Clients;

import javax.swing.*;
import java.awt.*;
/**
 * Represents a dialog for viewing products.
 */
public class ViewProducts extends JDialog{
    private JTable table1;
    private JPanel ViewProducts;
    public ViewProducts(JFrame parent) throws IllegalAccessException {
        super(parent);

        setMinimumSize(new Dimension(950,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ProductsBLL productsBLL=new ProductsBLL();

        JTable table = productsBLL.viewProducts();


        ViewProducts.this.add(new JScrollPane(table));



        setVisible(true);
    }
}
