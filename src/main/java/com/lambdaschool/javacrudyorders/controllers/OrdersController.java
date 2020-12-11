package com.lambdaschool.javacrudyorders.controllers;


import com.lambdaschool.javacrudyorders.models.Order;
import com.lambdaschool.javacrudyorders.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrdersController
{

    /**
     * The entry point for orders to access Order data
     */
    @Autowired
    OrdersService ordersService;

    // http://localhost:2019/orders/order/7
    @GetMapping(value = "/order/{ordernum}", produces = {"application/json"})
    public ResponseEntity<?> listOrderById(@PathVariable long ordernum)
    {
       Order o = ordersService.findOrdersById(ordernum);
       return new ResponseEntity<>(o, HttpStatus.OK);
    }
    @PostMapping(value = "/order", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewOrder(@Valid @RequestBody Order replaceOrder)
    {
        replaceOrder.setOrdnum(0);
        replaceOrder = ordersService.save(replaceOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{ordernum}")
            .buildAndExpand(replaceOrder.getOrdnum())
            .toUri();
        responseHeaders.setLocation(newOrderURI);


        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/order/{ordnum}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> replaceOrderById(@PathVariable  long ordnum, @Valid @RequestBody Order updateOrder)
    {
        updateOrder.setOrdnum(ordnum);
       updateOrder = ordersService.save(updateOrder);


        return new ResponseEntity<>(updateOrder, HttpStatus.OK);
    }

    @DeleteMapping(value = "/order/{ordnum}")
    public ResponseEntity<?> deleteOrderById(@PathVariable  long ordnum)
    {
        ordersService.delete(ordnum);


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
