package dao;

import connection.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**

 The AbstractDAO class is an abstract class that provides common functionality for data access objects.
 It contains methods for executing database operations such as insert, update, delete, and select queries.
 The class is parameterized with the type T, representing the model object associated with the DAO.
 @param <T> the type of the model object
 */
public abstract class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    protected int identifier;
    private final Class<T> type;

    protected AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    private String createSelectQuery(String field,int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ").append(field).append(" = ").append(id);
        return sb.toString();
    }
    private String createDeleteQuery(String field, int id){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ").append(field).append(" = ").append(id);
        return sb.toString();
    }
    protected  abstract String createInsertStatement(T t);
    protected  abstract String createUpdateStatement(T t);
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = null;
                if(identifier == 1) {
                    instance = (T) ctor.newInstance(0, null, null, 0);
                }
                if (identifier == 2){
                    instance = (T) ctor.newInstance(0, "", 0, 0d);
                }
                if (identifier==3){
                    instance = (T) ctor.newInstance(0, 0, 0,0, 0d);
                }

                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IntrospectionException | SQLException | InvocationTargetException | IllegalArgumentException | SecurityException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id",id);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException | IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public List<T> findALL(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * from "+ this.type.getSimpleName();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException | IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    public void remove(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query=createDeleteQuery("id",id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:remove " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

    }

    public void upate(T t){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query=createUpdateStatement(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public void insert(T t){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query=createInsertStatement(t);
        try{
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            statement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public JTable createTable(List<T> t) throws IllegalAccessException {
        JTable jTable;
        Class<T> tClass= (Class<T>) t.get(0).getClass();

        Field[] fields=tClass.getDeclaredFields();
        String[] column=new String[fields.length];

        for(int i=0;i<fields.length;i++)
        {
            column[i]=fields[i].getName();
        }

        Object[][] objects=new Object[t.size()][fields.length];
        for(int i=0;i<t.size();i++){
            Object obj=t.get(i);
            for(int j=0;j<fields.length;j++){
                Field field=fields[j];
                field.setAccessible(true);
                Object value=field.get(obj);
                objects[i][j]=value;
            }
        }
        DefaultTableModel tableModel=new DefaultTableModel(objects,column);

        jTable=new JTable(tableModel);
        return jTable;
    }

}
