package com.lambdaschool.javacrudyorders.services;

import com.lambdaschool.javacrudyorders.models.Order;
import com.lambdaschool.javacrudyorders.models.Payment;
import com.lambdaschool.javacrudyorders.repositories.OrdersRepository;
import com.lambdaschool.javacrudyorders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Service(value = "ordersService")
public class OrdersServiceImpl implements OrdersService
{
    @Autowired
    OrdersRepository ordersrepos;

    @Autowired
    PaymentRepository paymentrepos;
    @Override
    public Order findOrdersById(long id) throws EntityNotFoundException
    {
        return ordersrepos.findById(id)
            .orElseThrow( () ->  new EntityNotFoundException("Order " + id + " Not found"));
    }
    @Transactional
    @Override
    public Order save( Order order)
    {

     //     paymentrepos.findById(order.getPayments())
      //       .orElseThrow(() -> new EntityNotFoundException());

        Order newOrder = new Order();
        if(order.getOrdnum() != 0) {
            ordersrepos.findById(order.getOrdnum())
                .orElseThrow(() -> new EntityNotFoundException());

            newOrder.setOrdnum(order.getOrdnum());
        }

        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setDescription(order.getOrderdescription());
        newOrder.setCustomer(order.getCustomer());

        newOrder.getPayments().clear();

        for(Payment p : order.getPayments())
        {
            Payment newPayment = new Payment();
            newPayment.setPaymentid(p.getPaymentid());
            newPayment.setType(p.getType());
            newOrder.getPayments().add(newPayment);

        }


        return ordersrepos.save(newOrder);
    }


    @Transactional
    @Override
    public void deleteAll(){
        ordersrepos.deleteAll();
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        ordersrepos.deleteById(id);
    }
}
