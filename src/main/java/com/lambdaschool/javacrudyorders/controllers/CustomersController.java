package com.lambdaschool.javacrudyorders.controllers;


import com.lambdaschool.javacrudyorders.models.Customer;
import com.lambdaschool.javacrudyorders.repositories.CustomersRepository;
import com.lambdaschool.javacrudyorders.services.CustomersService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController
{

    @Autowired
    CustomersRepository customersrepos;
    @Autowired
    CustomersService customersService;

    @GetMapping(value = "/customer/{custcode}")
    public ResponseEntity<?> getCustomerById(@PathVariable long custcode)
    {
        Customer c = customersService.findCustomerById(custcode);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @GetMapping(value = "/namelike/{letter}", produces = {"application/json"})
    public ResponseEntity<?> getCustomerByName(@PathVariable String letter)
    {
        List customers = customersService.findCustomerByCustname(letter);

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> getCustomersOrders()
    {

        List ordersList = customersService.findCustomersOrders();

        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @GetMapping(value = "/orders/count", produces = {"application/json"})
    public ResponseEntity<?> getCustomersOrdersCount()
    {

        List ordersList = customersService.getCustNameCountOrders();

        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    // http://localhost:2019/customers/customer
    @PostMapping(value = "/customer", consumes = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Customer newCustomer)
    {
        newCustomer.setCustcode(0);
        newCustomer = customersService.save(newCustomer);

        // SET THE LOCATION HEADER FOR THE NEWLY CREATED RESOURCE
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()   // get the URI for this request
        .path("/{customerid}") // add to it a path variable
        .buildAndExpand(newCustomer.getCustcode()) // add to the varible the newly created customer id
        .toUri(); // convert  that work into a human readable URI
        responseHeaders.setLocation(newCustomerURI); // in the header, set the location to that URI
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
   // http://localhost:2019/customers/customer/19
    @PutMapping(value = "/customer/{custcode}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> replaceCustomer(@PathVariable long custcode,
                                             @Valid @RequestBody Customer replaceCustomer)
    {
        replaceCustomer.setCustcode(custcode);
        Customer c = customersService.save(replaceCustomer);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    // http://localhost:2019/customers/customer/19

    @PatchMapping(value = "/customer/{custcode}", consumes = {"application/json"})
    public ResponseEntity<?> updateCustomerById(@PathVariable long custcode,
                                                @RequestBody Customer updateCustomer)
    {
       customersService.update(custcode, updateCustomer);
        Customer c = customersService.save(updateCustomer);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
   // DELETE http://localhost:2019/customers/customer/54

    @DeleteMapping(value = "/customer/{custcode}")
    public ResponseEntity<?> deletCustomerById(@PathVariable long custcode)
    {
        customersService.delete(custcode);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
