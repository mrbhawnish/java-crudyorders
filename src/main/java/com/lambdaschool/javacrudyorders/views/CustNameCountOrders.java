package com.lambdaschool.javacrudyorders.views;

public interface CustNameCountOrders
{
    String getCustname();  //  should match the custname in our custom query on customerRepository
    int getCount_orders();
}
