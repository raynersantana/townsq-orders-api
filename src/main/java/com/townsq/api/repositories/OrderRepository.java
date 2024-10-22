package com.townsq.api.repositories;

import com.townsq.api.domain.order.Order;
import com.townsq.api.domain.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUserIdAndOrderStatus(Long aLong, OrderStatus orderStatus);

    @Query("select o from orders o join users u on u = o.user where u.username = :username")
    List<Order> findByUsername(String username);
}
