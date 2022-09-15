package com.travelcompany.eshop.repository;

import com.travelcompany.eshop.domain.CategoryType;
import com.travelcompany.eshop.domain.Customer;
import java.util.List;

/**
 *
 * @author Grproth
 */
public interface CustomersRepository {
    
    boolean addCustomer ( Customer customer);
    boolean deleteCustomer( long customerId);
    List<Customer> readCustomers();
    Customer readCustomer( long customerId);
    boolean updateCustomerName( long customerId, String newName);
    boolean updateCustomerEmail( long customerId, String newEmail);
    boolean updateCustomerAddress( long customerId, String newAddress);
    boolean updateCustomerNationality( long customerId, String [] newNationality);
    boolean updateCustomerCategoryType( long customerId, CategoryType newCategoryType);
    
}
