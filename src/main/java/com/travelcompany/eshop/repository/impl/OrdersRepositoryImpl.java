package com.travelcompany.eshop.repository.impl;

import com.travelcompany.eshop.domain.Order;
import com.travelcompany.eshop.repository.OrdersRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *  This is an implementation of the OrdersRepository interface.
 * 
 *  @author Grproth
 */
public class OrdersRepositoryImpl implements OrdersRepository{
    
    private final List<Order> orders = new ArrayList<>();
    
    
    /**
     *  Tries to add a new order to the list.
     * 
     *  @param   newOrder   ( the order to be added)
     *  @return  Boolean    ( true if the order has been added, false otherwise)
     */
    @Override
    public boolean addOrder( Order newOrder){
        
        for( Order order : orders){
            
            if( order.getId() == newOrder.getId())
                return false;
        }
        
        orders.add(newOrder);
        return true;
    }
    
    
    /**
     *  Tries to delete an existing order from the list.
     * 
     *  @param  orderId     ( the id of the order in question)
     *  @return Boolean     ( true if the order has been deleted, false otherwise)
     */
    @Override
    public 
    boolean deleteOrder( long orderId){
        
        Order order = readOrder(orderId);
        
        if( order == null) 
            return false;
        
        orders.remove(order);
        return true;
    }
    
    
    /**
     *  Returns the whole order list.
     * 
     *  @return orders   ( the whole list)
     */
    @Override
    public List<Order> readOrders(){
        
        return orders;
    }
    
    
    /**
     *  Tries to find a specific order.
     * 
     *  @param  orderId     ( the id of the order in question)
     *  @return order       ( returns null if the order is not found, 
     *                          otherwise returns the order object)
     */
    @Override
    public Order readOrder( long orderId){
        
        for ( Order order : orders){
            
            if ( order.getId() == orderId)
                return order;
        }
        
        return null;
    }
}
