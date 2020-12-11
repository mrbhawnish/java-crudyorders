package com.lambdaschool.javacrudyorders.repositories;

import com.lambdaschool.javacrudyorders.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long>
{
}
