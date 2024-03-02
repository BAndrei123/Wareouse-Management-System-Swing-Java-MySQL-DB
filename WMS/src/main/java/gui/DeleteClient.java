package gui;

import bll.ClientsBLL;
import bll.OrdersBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * Represents a dialog for deleting a client.
 */
public class DeleteClient extends JDialog{
    private JComboBox deleteComboBox;
    private JButton DELETEButton;
    private JPanel DeleteClient;
    public DeleteClient(JFrame parent) {
        super(parent);
        setContentPane(DeleteClient);
        setMinimumSize(new Dimension(500, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ClientsBLL clientsBLL1=new ClientsBLL();
        ArrayList<Integer> clientsIDs=clientsBLL1.getClientsIDs();
        for(Integer integer: clientsIDs){
            deleteComboBox.addItem(String.valueOf(integer));
        }


        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(deleteComboBox.getSelectedItem()!=null) {
                    String get = deleteComboBox.getSelectedItem().toString();
                    Integer id = Integer.parseInt(get);
                    ClientsBLL clientsBLL = new ClientsBLL();
                    clientsBLL.deleteClient(id, DeleteClient.this);
                    OrdersBLL ordersBLL=new OrdersBLL();
                    ordersBLL.deleteOrderFromClient(id);
                    deleteComboBox.removeItem(deleteComboBox.getSelectedItem());
                }
            }
        });
        setVisible(true);
    }
}
