package gui;

import bll.ClientsBLL;
import dao.AbstractDAO;
import dao.ClientsDAO;
import model.Clients;

import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 * Represents a dialog for viewing clients.
 */
public class ViewClients extends JDialog{
    private JPanel panel1;
    private JScrollPane ViewClients;
    public ViewClients(JFrame parent) throws IllegalAccessException {
        super(parent);

        setMinimumSize(new Dimension(950,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        ClientsBLL clientsBLL=new ClientsBLL();

        JTable table = clientsBLL.viewProducts();


        ViewClients.this.add(new JScrollPane(table));


        setVisible(true);
    }
}
