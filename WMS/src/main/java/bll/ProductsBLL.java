package bll;

import dao.ClientsDAO;
import dao.ProductsDAO;
import gui.*;
import model.Clients;
import model.Products;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**

 The ProductsBLL class is the business logic layer for managing products.
 It interacts with the ProductsDAO to perform CRUD operations on products.
 */

public class ProductsBLL {
    private ProductsDAO productsDAO;

    public ProductsBLL() {
        productsDAO=new ProductsDAO();
    }
    public static int checkInput(String tocheck1, String tocheck2, String tocheck3, String tocheck4, AddNewProduct addNewProduct){

        if(!tocheck1.matches("\\d+") || !tocheck3.matches("\\d+") || !tocheck4.matches("\\d+(\\.\\d+)?$")){
            JOptionPane.showMessageDialog(addNewProduct,"ID and Quantity fields have to be integers and price have to be double or integer","Try Again",JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        if(tocheck1.isEmpty() || tocheck2.isEmpty() || tocheck3.isEmpty() || tocheck4.isEmpty()){
            JOptionPane.showMessageDialog(addNewProduct,"All fields have to be inserted","Try Again",JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        return 1;
    }

    public static int checkEditInput(String tocheck1, String tocheck2, String tocheck3, EditProduct editProduct){

        if( !tocheck2.matches("\\d+") && !tocheck2.isEmpty()){ //|| !tocheck3.matches("\\d+(\\.\\d+)?$")){
            JOptionPane.showMessageDialog(editProduct," Quantity field has to be integers and price have to be double or integer","Try Again",JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        if(!tocheck3.matches("\\d+(\\.\\d+)?$") && !tocheck3.isEmpty()){
            JOptionPane.showMessageDialog(editProduct," Quantity field has to be integers and price have to be double or integer","Try Again",JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        if(tocheck1.isEmpty() && tocheck2.isEmpty() && tocheck3.isEmpty() && tocheck3.isEmpty()){
            JOptionPane.showMessageDialog(editProduct,"At least one field has to be inserted","Try Again",JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        return 1;
    }
    public void insertProduct(Products products, AddNewProduct addNewProduct){
        //Clients clients=new Clients(id,name,address,age);
        Products productsExists;
        productsExists=productsDAO.findById(products.getId());
        if(productsExists==null) {
            productsDAO.insert(products);
            JOptionPane.showMessageDialog(addNewProduct,"Product added added");
        }
        else {
            JOptionPane.showMessageDialog(addNewProduct,"This ID already exists","Try Again",JOptionPane.ERROR_MESSAGE);

        }
    }
    public ArrayList<Integer> getProductIDs(){
        List<Products> products=productsDAO.findALL();
        ArrayList<Integer> productsIDs=new ArrayList<>();
        for(Products products1: products){
            productsIDs.add(products1.getId());
        }
        return productsIDs;
    }



    public void deleteProduct(int id, DeleteProduct deleteProduct){
        productsDAO.remove(id);
        JOptionPane.showMessageDialog(deleteProduct,"Product successfully deleted");
    }
    public Products findProduct(int id){
        Products products=productsDAO.findById(id);
        return products;
    }

    public void updateProduct(Products products, EditProduct editProduct){
        productsDAO.upate(products);
        JOptionPane.showMessageDialog(editProduct,"Product suffered a change");
    }
    public void updateProduct2(Products products, OrdersOperations ordersOperations){
        productsDAO.upate(products);
        JOptionPane.showMessageDialog(ordersOperations,"The stock of the product has been updated");
    }
    public JTable viewProducts() throws IllegalAccessException {
        List<Products> clients=productsDAO.findALL();
        ProductsDAO productsDAO1=new ProductsDAO();

        return productsDAO1.createTable(clients);
    }
}
