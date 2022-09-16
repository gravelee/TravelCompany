package com.travelcompany.eshop.domain;


/**
 *  A class that represents the different customers and their info.
 *  We do extend from IdParser in order to reduce the number of methods called
 *  within GeneralUtility class.
 * 
 *  @author Grproth
 */
public class Customer extends IdParser{

    private final long id;
    private String name;
    private String email;
    private String address;
    private String[] nationality;
    private CategoryType categoryType;

    /*
        Here we make our nationality array into a stirng.
    */
    private String nationalityToString(){
        
        String temp = "";
        for( String str : nationality) 
            temp += str + " ";
        
        return temp;
    }
    
    public Customer(long id, String name, String email, String address, 
            String[] nationality, CategoryType categoryType) {
        
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.nationality = nationality;
        this.categoryType = categoryType;
    }
    
    /*
        We did the override cause we inherit that method from IdParser abstruct class.
    */
    @Override
    public long getId(){
        
        return id;
    }
    
    public String getName(){
        
        return name;
    }
    
    public String getEmail(){
        
        return email;
    }
    
    public String getAddress(){
        
        return address;
    }
    
    public String[] getNationality(){
        
        return nationality;
    }
    
    public CategoryType getCategory(){
        
        return categoryType;
    }
    
    public void setName( String name){
    
        this.name = name;
    }
    
    public void setEmail( String email){
        
        this.email = email;
    }
    
    public void setAddress( String address){
        
        this.address = address;
    }
    
    public void setNationality( String[] nationality){
        
        this.nationality = nationality;
    }
    
    public void setCategoryType( CategoryType categoryType){
        
        this.categoryType = categoryType;
    }
    
    public static String header(){
        
        return "\n\nid,name,email,address,nationality,cateforyType\n\n";
    }
    
    @Override
    public String toString(){
        
        return "" + id + "," + name + "," + email + "," + address + ","
            + nationalityToString() + "," + categoryType;
    }
}
