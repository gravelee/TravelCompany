package com.travelcompany.eshop.domain;


/**
 *  A class that represents the different customers and their info.
 *  We do extend from IdParser in order to reduce the number of methods called
 *  within GeneralUtility class.
 * 
 *  @author Grproth
 */
public class Customer extends IdParser {

    private final long id;
    private String name;
    private String email;
    private String address;
    private String[] nationality;
    private CategoryType categoryType;

    /*
        Here we make our nationality array into a stirng.
    */
    private String nationalityToString() {
        
        String temp = "";
        for( String str : nationality) 
            temp += str + " ";
        
        return temp;
    }
    
    public Customer( final long id, final String name, final String email, 
            final String address, final String[] nationality, 
            final CategoryType categoryType) {
        
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.nationality = nationality;
        this.categoryType = categoryType;
    }
    
    /*
        We override cause we inherit that method from IdParser abstruct class.
    */
    @Override
    public final long getId() {
        
        return id;
    }
    
    public final String getName() {
        
        return name;
    }
    
    public final String getEmail() {
        
        return email;
    }
    
    public final String getAddress() {
        
        return address;
    }
    
    public final String[] getNationality() {
        
        return nationality;
    }
    
    public final CategoryType getCategory() {
        
        return categoryType;
    }
    
    public final void setName( final String name) {
    
        this.name = name;
    }
    
    public final void setEmail( final String email) {
        
        this.email = email;
    }
    
    public final void setAddress( final String address) {
        
        this.address = address;
    }
    
    public final void setNationality( final String[] nationality) {
        
        this.nationality = nationality;
    }
    
    public final void setCategoryType( final CategoryType categoryType) {
        
        this.categoryType = categoryType;
    }
    
    public static final String header() {
        
        return "\n\nid,name,email,address,nationality,cateforyType\n\n";
    }
    
    @Override
    public final String toString() {
        
        return "" + id + "," + name + "," + email + "," + address + ","
            + nationalityToString() + "," + categoryType;
    }
}
