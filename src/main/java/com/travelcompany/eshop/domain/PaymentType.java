package com.travelcompany.eshop.domain;

/**
 *  An enum helper type that represents the different payment types.
 * 
 *  @author Grproth
 */
public enum PaymentType {
    
    CASH("Cash"),
    CREDIT_CARD("Credit Card");
    
    private final String name;
    
    private PaymentType(String name){
        this.name = name;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
