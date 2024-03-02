package model;

/**

 The Bill class represents a bill record.
 It contains information about the bill's ID, client ID, product ID, quantity, and price.
 @param id the ID of the bill
 @param IDclient the ID of the client associated with the bill
 @param IDproduct the ID of the product associated with the bill
 @param quantity the quantity of the product in the bill
 @param price the price of the product in the bill
 */
public record Bill(Integer id, Integer IDclient, Integer IDproduct, Integer quantity, Double price) {

}
