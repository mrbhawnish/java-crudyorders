package com.lambdaschool.javacrudyorders.services;

import com.lambdaschool.javacrudyorders.models.Customer;
import com.lambdaschool.javacrudyorders.models.Order;
import com.lambdaschool.javacrudyorders.repositories.CustomersRepository;
import com.lambdaschool.javacrudyorders.repositories.OrdersRepository;
import com.lambdaschool.javacrudyorders.views.CustNameCountOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customersService")
public class CustomersServiceImpl implements CustomersService
{
  @Autowired
    CustomersRepository customersrepos;

  @Autowired
    OrdersRepository ordersrepos;
  @Override
  public Customer findCustomerById(long custcode) throws EntityNotFoundException
  {
     return  customersrepos.findById(custcode)
                 .orElseThrow(() ->
                    new EntityNotFoundException("Customer " + custcode + " NOT FOUND"));
  }

  @Override
  public List<Customer> findCustomerByCustname(String custname)
      throws EntityNotFoundException
  {
    return customersrepos.findByCustnameContainingIgnoringCase(custname);
  }

  @Override
  public List<Customer> findCustomersOrders() throws
      EntityNotFoundException
  {
    List<Customer> customersList =  new ArrayList<>();
    customersrepos.findAll().iterator().forEachRemaining(customersList::add);
   return customersList;
  }

  @Override
  public List<CustNameCountOrders> getCustNameCountOrders()
  {
    return customersrepos.getCountCustOrders();
  }

  @Transactional
  @Override
  public Customer save(Customer customer)
  {
    Customer newCustomer = new Customer();

    if(customer.getCustcode() != 0)
    {
      customersrepos.findById(customer.getCustcode())
          .orElseThrow(() ->
              new EntityNotFoundException("Customer with " + customer.getCustcode() + "NOT FOUND"));

      newCustomer.setCustcode(customer.getCustcode());
    }




    newCustomer.setCustname(customer.getCustname());
    newCustomer.setCustcity(customer.getCustcity());
    newCustomer.setWorkingarea(customer.getWorkingarea());
    newCustomer.setCustcountry(customer.getCustcountry());
    newCustomer.setGrade(customer.getGrade());
    newCustomer.setOpeningamt(customer.getOpeningamt());
    newCustomer.setReceiveamt(customer.getReceiveamt());
    newCustomer.setPaymentamt(customer.getPaymentamt());
    newCustomer.setOutstandingamt(customer.getOutstandingamt());
    newCustomer.setPhone(customer.getPhone());
    newCustomer.setAgent(customer.getAgent());

    newCustomer.getOrders().clear();

    for(Order o : customer.getOrders())
    {
      Order newOrder = new Order();
      newOrder.setOrdnum(o.getOrdnum());
      newOrder.setAdvanceamount(o.getAdvanceamount());
      newOrder.setDescription(o.getOrderdescription());
      newOrder.setCustomer(newCustomer);
      newOrder.setOrdamount(o.getOrdamount());
      newCustomer.getOrders().add(newOrder);
      newOrder.setPayments(o.getPayments());

    }


    return customersrepos.save(newCustomer);
  }

  @Transactional
  @Override
  public Customer update(long custcode, Customer customer)
  {
    Customer updateCustomer = customersrepos.findById(custcode)
          .orElseThrow(() ->
              new EntityNotFoundException("Customer with " + custcode + " NOT FOUND"));


    if(customer.getCustname() != null) updateCustomer.setCustname(customer.getCustname());
    if(customer.getCustcity() != null)updateCustomer.setCustcity(customer.getCustcity());
    if(customer.getWorkingarea() != null)updateCustomer.setWorkingarea(customer.getWorkingarea());
    if(customer.getCustcountry() != null)updateCustomer.setCustcountry(customer.getCustcountry());
    if(customer.getGrade() != null)updateCustomer.setGrade(customer.getGrade());
    if(customer.getOpeningamt() != 0.00)updateCustomer.setOpeningamt(customer.getOpeningamt());
    if(customer.getReceiveamt() != 0.00)updateCustomer.setReceiveamt(customer.getReceiveamt());
    if(customer.getPaymentamt() != 0.00)updateCustomer.setPaymentamt(customer.getPaymentamt());
    if(customer.getOutstandingamt() != 0.00)updateCustomer.setOutstandingamt(customer.getOutstandingamt());
    if(customer.getPhone() != null)updateCustomer.setPhone(customer.getPhone());
    if(customer.getAgent() != null) updateCustomer.setAgent(customer.getAgent());

    if(customer.getOrders().size() > 0)
    {

      updateCustomer.getOrders()
          .clear();
      for (Order o : customer.getOrders())
      {
        Order newOrder = new Order();
        newOrder.setOrdnum(o.getOrdnum());
        newOrder.setAdvanceamount(o.getAdvanceamount());
        newOrder.setDescription(o.getOrderdescription());
        newOrder.setCustomer(updateCustomer);
        newOrder.setOrdamount(o.getOrdamount());
        updateCustomer.getOrders()
            .add(newOrder);
        newOrder.setPayments(o.getPayments());

      }
    }

    return customersrepos.save(updateCustomer);
  }

  @Transactional
  @Override
  public void delete(long custid){
    customersrepos.deleteById(custid);
  }

  @Transactional
  @Override
  public void deleteAll(){
    customersrepos.deleteAll();
  }
}
