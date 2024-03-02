package dao;

import model.Orders;

/**

 The OrdersDAO class is a data access object for managing orders in the database.
 It extends the AbstractDAO class and provides specific implementation for inserting and updating order records.
 */
public class OrdersDAO extends AbstractDAO<Orders>{

    public OrdersDAO(){
        this.identifier=3;
    }
    @Override
    protected String createInsertStatement(Orders orders) {
        StringBuilder sb=new StringBuilder();
        sb.append("insert into orders values( ");
        sb.append(orders.getId()).append(",");
        sb.append(orders.getIDclient()).append(",");
        sb.append(orders.getIDproduct()).append(",");
        sb.append(orders.getQuantity()).append(",");
        sb.append(orders.getPrice()).append(")");
        return sb.toString();
    }

    @Override
    protected String createUpdateStatement(Orders orders) {
        return null;
    }
}
