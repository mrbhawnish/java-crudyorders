package com.lambdaschool.javacrudyorders.services;

import com.lambdaschool.javacrudyorders.models.Order;

public interface OrdersService
{
    /**
     * Returns the order with the given primary key.
     *
     * @param id The primary key (long) of the order you seek.
     * @return The given order or throws an exception if not found.
     */
    Order findOrdersById(long id);

    Order save( Order newOrder);

    void delete(long id);

    void deleteAll();
}
