package com.townsq.api.repositories;

import com.townsq.api.domain.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("select p from payments p, orders o where p.order = o and o.id = :id")
    Optional<Payment> findByOrderId(Long id);
}
