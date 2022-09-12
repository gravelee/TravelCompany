
package com.travelcompany.eshop.model;

/**
 *
 * A class that represents the different customers and their info.
 * 
 * @author Grproth
 */
public class Customer {

    private long id;
    private String name;
    private String email;
    private String address;
    private String nationality;
    private CategoryType categoryType;

    public Customer(long id, String name, String email, String address, 
            String nationality, CategoryType categoryType) {
        
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.nationality = nationality;
        this.categoryType = categoryType;
    }
    
    public long getId(){
        
        return id;
    }
    
    public String getEmail(){
        
        return email;
    }
    
    public CategoryType getCategory(){
        
        return categoryType;
    }
    
    @Override
    public String toString(){
        
        return "" + id + "," + name + "," + email + "," + address 
                + "," + nationality + "," + categoryType;
    }
}
