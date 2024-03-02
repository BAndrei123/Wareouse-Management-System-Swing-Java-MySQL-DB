package dao;


import model.Products;
/**

 The ProductsDAO class is a data access object for managing products in the database.
 It extends the AbstractDAO class and provides specific implementation for inserting and updating product records.
 */
public class ProductsDAO extends AbstractDAO<Products>{

    public ProductsDAO(){
        this.identifier=2;
    }
    @Override
    protected String createInsertStatement(Products products) {
        StringBuilder sb=new StringBuilder();
        sb.append("insert into products values( ");
        sb.append(products.getId()).append(",");
        sb.append("'").append(products.getName()).append("',");
        sb.append(products.getQuantity()).append(",");
        sb.append(products.getPrice()).append(")");
        return sb.toString();
    }

    @Override
    protected String createUpdateStatement(Products products) {
        StringBuilder sb=new StringBuilder();
        sb.append("UPDATE products SET name='").append(products.getName()).append("'");
        sb.append(", price=").append(products.getPrice()).append("");
        sb.append(", quantity=").append(products.getQuantity());
        sb.append(" where id=").append(products.getId());
        return sb.toString();
    }
}
