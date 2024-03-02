package org.example;

import bll.ClientsBLL;
import bll.ProductsBLL;
import connection.ConnectionFactory;
import dao.AbstractDAO;
import dao.ClientsDAO;
import dao.ProductsDAO;
import gui.Dashboard;
import model.Clients;
import model.Products;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**

 The App class represents the main entry point of the application.
 It contains the main method to start the application.
 */
public class App 
{

    public static void main( String[] args ){
      ClientsBLL clientsBLL=new ClientsBLL();
        ProductsDAO productsDAO=new ProductsDAO();
        ClientsDAO clinetsDAO=new ClientsDAO();
        ClientsBLL clientsBLL1=new ClientsBLL();





        Dashboard dashboard=new Dashboard(null);

    }
}
