package bll;

import dao.ClientsDAO;
import gui.AddNewClient;
import gui.DeleteClient;
import gui.EditClient;
import model.Clients;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ClientsBLL {

    private ClientsDAO clientsDAO;

    public ClientsBLL() {
        this.clientsDAO = new ClientsDAO();
    }

    public static int checkInput(String tocheck1, String tocheck2, String tocheck3, String tocheck4, AddNewClient addNewClient){

        if(!tocheck1.matches("\\d+") || !tocheck4.matches("\\d+")){
            JOptionPane.showMessageDialog(addNewClient,"AGE and ID fields have to be integers","Try Again",JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        if(tocheck1.isEmpty() || tocheck2.isEmpty() || tocheck3.isEmpty() || tocheck4.isEmpty()){
            JOptionPane.showMessageDialog(addNewClient,"All fields have to be inserted","Try Again",JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        return 1;
    }
    public static int checkEditInput(String tocheck1, String tocheck2, String tocheck3, EditClient editClient){

        if( !tocheck3.matches("\\d+") && !tocheck3.isEmpty()){
            JOptionPane.showMessageDialog(editClient,"AGE field has to be integers","Try Again",JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        if(tocheck1.isEmpty() && tocheck2.isEmpty() && tocheck3.isEmpty() ){
            JOptionPane.showMessageDialog(editClient,"At least one field has to changed","Try Again",JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        return 1;
    }
    public void insertClient(Clients clients,AddNewClient addNewClient){
        //Clients clients=new Clients(id,name,address,age);
        Clients clientExists;
        clientExists=clientsDAO.findById(clients.getId());
        if(clientExists==null) {
            clientsDAO.insert(clients);
            JOptionPane.showMessageDialog(addNewClient,"Client added");
        }
        else {
            JOptionPane.showMessageDialog(addNewClient,"This ID already exists","Try Again",JOptionPane.ERROR_MESSAGE);

        }
    }
    public ArrayList<Integer> getClientsIDs(){
        List<Clients> clients=clientsDAO.findALL();
        ArrayList<Integer> clientsIDs=new ArrayList<>();
        for(Clients clients1: clients){
            clientsIDs.add(clients1.getId());
        }
        return clientsIDs;
    }

    public void deleteClient(int id, DeleteClient deleteClient){
        clientsDAO.remove(id);
        JOptionPane.showMessageDialog(deleteClient,"Client successfully deleted");
    }

    public Clients findClient(int id){
        Clients clients=clientsDAO.findById(id);
        return clients;
    }
    public void updateClient(Clients clients, EditClient editClient){
        clientsDAO.upate(clients);
        JOptionPane.showMessageDialog(editClient,"Client suffered a change");
    }

    public JTable viewProducts() throws IllegalAccessException {
        List<Clients> clients=clientsDAO.findALL();
        ClientsDAO clientsDAO1=new ClientsDAO();

        return clientsDAO1.createTable(clients);
    }

}
