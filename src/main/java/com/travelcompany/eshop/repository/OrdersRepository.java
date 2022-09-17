package com.travelcompany.eshop.repository;

import com.travelcompany.eshop.domain.Order;
import java.util.List;

/**
 *  This is the OrderRepository interface, here we have all the methods that
 *  a OrderRepository implementation needs to implement in order to have
 *  the appropriate logic.
 * 
 *  @author Grproth
 */
public interface OrdersRepository {
    
    boolean addOrder( final Order order);
    boolean deleteOrder( final long orderId);
    List<Order> readOrders();
    Order readOrder( final long orderId);
}
