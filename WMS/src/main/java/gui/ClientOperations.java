package gui;
/**
 * Represents a dialog for client operations.
 */
import dao.AbstractDAO;
import dao.ClientsDAO;
import model.Clients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientOperations extends JDialog {
    private JButton ADDNEWCLIENTSButton;
    private JPanel ClientOperations;
    private JButton EDITCLIENTButton;
    private JButton DELETECLIENTButton;
    private JButton VIEWCLIENTSButton;

    public ClientOperations(JFrame parent){
        super(parent);
        setContentPane(ClientOperations);
        setMinimumSize(new Dimension(500,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ADDNEWCLIENTSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewClient addNewClient=new AddNewClient(null);
            }
        });

        EDITCLIENTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditClient editClient=new EditClient(null);
            }
        });

        DELETECLIENTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteClient deleteClient=new DeleteClient(null);
            }
        });

        VIEWCLIENTSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        VIEWCLIENTSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ViewClients viewClients=new ViewClients(null);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        setVisible(true);
    }
}
