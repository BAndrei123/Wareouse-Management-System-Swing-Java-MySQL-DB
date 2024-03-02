package bll;

import dao.OrdersDAO;
import gui.AddNewClient;
import gui.DeleteClient;
import gui.OrdersOperations;
import model.Clients;
import model.Orders;

import javax.swing.*;
import java.util.List;

public class OrdersBLL {
        OrdersDAO ordersDAO;

    public OrdersBLL() {
        this.ordersDAO = new OrdersDAO();
    }

    public static int checkInput(String toCheck1, String tocheck2, OrdersOperations ordersOperations){
            if(toCheck1.isEmpty() || tocheck2.isEmpty()){
                JOptionPane.showMessageDialog(ordersOperations,"All fields have to be inserted","try again",JOptionPane.ERROR_MESSAGE);
                return 0;
            }
            if(!toCheck1.matches("\\d+") || !tocheck2.matches("\\d+")){
                JOptionPane.showMessageDialog(ordersOperations,"ID and quantity fields have to be integers","Try Again",JOptionPane.ERROR_MESSAGE);
                return 0;
            }
            return 1;
        }
    public int insertOrder(Orders orders, OrdersOperations ordersOperations){

        Orders ordersExsists;
        ordersExsists=ordersDAO.findById(orders.getId());
        if(ordersExsists==null) {
            ordersDAO.insert(orders);
            JOptionPane.showMessageDialog(ordersOperations,"Order placed");
            return 1;
        }
        else {
            JOptionPane.showMessageDialog(ordersOperations,"This ID already exists","Try Again",JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public void deleteOrderFromClient(int id){
        List<Orders> ordersList=ordersDAO.findALL();
        for(Orders orders: ordersList){
            if(orders.getIDclient()==id){
                ordersDAO.remove(orders.getId());
            }
        }
    }
    public void deleteOrderFromProduct(int id){
        List<Orders> ordersList=ordersDAO.findALL();
        for(Orders orders: ordersList){
            if(orders.getIDproduct()==id){
                ordersDAO.remove(orders.getId());
            }
        }
    }
}
