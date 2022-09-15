package com.travelcompany.eshop.repository;

import com.travelcompany.eshop.domain.Order;
import java.util.List;

/**
 *
 * @author Grproth
 */
public interface OrdersRepository {
    
    boolean addOrder( Order order);
    boolean deleteOrder( long orderId);
    List<Order> readOrders();
    Order readOrder( long orderId);
}
