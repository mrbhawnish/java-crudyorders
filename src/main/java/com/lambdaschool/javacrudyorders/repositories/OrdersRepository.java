package com.lambdaschool.javacrudyorders.repositories;

import com.lambdaschool.javacrudyorders.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Order, Long>
{
}
