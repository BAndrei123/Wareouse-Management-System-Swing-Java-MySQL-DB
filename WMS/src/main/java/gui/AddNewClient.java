package gui;

import bll.ClientsBLL;
import model.Clients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static bll.ClientsBLL.checkInput;

/**
 * Represents a dialog for adding a new client.
 */
public class AddNewClient extends JDialog{
    private JTextField idField;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField ageField;
    private JButton ADDButton;
    private JPanel AddNewClient;


    public AddNewClient(JFrame parent){
        super(parent);
        setContentPane(AddNewClient);
        setMinimumSize(new Dimension(500,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              int check= checkInput(idField.getText(), nameField.getText(), addressField.getText(), ageField.getText(),AddNewClient.this);
              if(check == 1){
                  int id=Integer.parseInt(idField.getText());
                  int age=Integer.parseInt(ageField.getText());

                  Clients clients=new Clients(id,nameField.getText(),addressField.getText(),age);
                  ClientsBLL clientsBLL=new ClientsBLL();
                  clientsBLL.insertClient(clients,AddNewClient.this);
              }

            }
        });
        setVisible(true);
    }
}
