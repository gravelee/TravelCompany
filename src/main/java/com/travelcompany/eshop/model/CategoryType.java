
package com.travelcompany.eshop.model;

/**
 * 
 * An enum helper type that represents the different customer types.
 * 
 * @author Grproth
 */
public enum CategoryType {
    
    BUSINESS("Business"),
    INDIVIDUAL("Individual");
    
    private final String name;
    
    private CategoryType(String name){
        this.name = name;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
