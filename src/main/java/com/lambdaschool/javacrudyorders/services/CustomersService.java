package com.lambdaschool.javacrudyorders.services;

import com.lambdaschool.javacrudyorders.models.Customer;
import com.lambdaschool.javacrudyorders.views.CustNameCountOrders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomersService
{

    List<Customer> findCustomersOrders();

    Customer findCustomerById(long id);

   List<Customer> findCustomerByCustname(String name);

   List<CustNameCountOrders> getCustNameCountOrders();


   Customer save(Customer customer);


   Customer update(long id, Customer replaceCustomer);


   void delete(long id);

   void deleteAll();




}
