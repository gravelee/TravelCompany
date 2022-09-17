package com.travelcompany.eshop.domain;

/**
 *  An enum helper type that represents the different customer types.
 * 
 *  @author Grproth
 */
public enum CategoryType {
    
    BUSINESS("Business"),
    INDIVIDUAL("Individual");
    
    private final String name;
    
    private CategoryType(String name) {
        
        this.name = name;
    }
    
    @Override
    public final String toString() {
        
        return name;
    }
}
