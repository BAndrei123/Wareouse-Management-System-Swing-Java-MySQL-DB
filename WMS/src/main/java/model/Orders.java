package model;

public class Orders {
    private Integer id;

    private Integer IDclient;

    private Integer IDproduct;
    private Integer quantity;
    private Double price;
    /**

     The Orders class represents an order.
     It contains information about the order's ID, client ID, product ID, quantity, and price.
     */
    public Orders(Integer id, Integer IDclient, Integer IDproduct, Integer quantity, Double price) {
        this.id = id;
        this.IDclient = IDclient;
        this.IDproduct = IDproduct;
        this.quantity = quantity;
        this.price = price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIDclient(Integer IDclient) {
        this.IDclient = IDclient;
    }

    public void setIDproduct(Integer IDproduct) {
        this.IDproduct = IDproduct;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIDclient() {
        return IDclient;
    }

    public Integer getIDproduct() {
        return IDproduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }
}
