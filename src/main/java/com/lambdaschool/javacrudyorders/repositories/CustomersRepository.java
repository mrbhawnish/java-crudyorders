package com.lambdaschool.javacrudyorders.repositories;

import com.lambdaschool.javacrudyorders.models.Customer;
import com.lambdaschool.javacrudyorders.views.CustNameCountOrders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomersRepository extends CrudRepository<Customer, Long>
{
    List<Customer> findByCustnameContainingIgnoringCase(String name);

    @Query(value = "SELECT c.custname as custname, count(o.custcode) as count_orders " +
        " FROM CUSTOMERS c " +
        " LEFT JOIN ORDERS o " +
        " ON c.custcode = o.custcode " +
        " GROUP BY c.custname;", nativeQuery = true)
   List<CustNameCountOrders> getCountCustOrders();
}
