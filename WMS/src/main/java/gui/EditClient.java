package gui;

import bll.ClientsBLL;
import model.Clients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static bll.ClientsBLL.checkEditInput;
/**
 * Represents a dialog for editing a client.
 */
public class EditClient extends JDialog{
    private JComboBox IDcomboBox;
    private JTextField nameField;
    private JButton EDITButton;
    private JPanel EditClient;
    private JTextField addresField;
    private JTextField ageField;

    public EditClient(JFrame parent){
        super(parent);
        setContentPane(EditClient);
        setMinimumSize(new Dimension(500,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ClientsBLL clientsBLL1=new ClientsBLL();
        ArrayList<Integer> clientsIDs=clientsBLL1.getClientsIDs();
        for(Integer integer: clientsIDs){
            IDcomboBox.addItem(String.valueOf(integer));
        }



        EDITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(IDcomboBox.getSelectedItem()!=null){

                    ClientsBLL clientsBLL=new ClientsBLL();
                    int check=checkEditInput(nameField.getText(),addresField.getText(),ageField.getText(),EditClient.this);
                    if(check==1){
                        String get=IDcomboBox.getSelectedItem().toString();
                        Integer id=Integer.parseInt(get);
                        Clients clients=clientsBLL.findClient(id);
                        if(!nameField.getText().isEmpty()){
                            clients.setName(nameField.getText());
                        }
                        if(!addresField.getText().isEmpty()){
                            clients.setAddress(addresField.getText());
                        }
                        if(!ageField.getText().isEmpty()){
                            Integer age=Integer.parseInt(ageField.getText());
                            clients.setAge(age);
                        }
                        clientsBLL.updateClient(clients,EditClient.this);
                    }
                }
            }
        });
        setVisible(true);
    }
}
