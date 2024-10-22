package com.townsq.api.repositories;

import com.townsq.api.domain.cart.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
    Optional<CartItems> findByUserIdAndProductIdAndOrderId(Long aLong, Long aLong1, Long aLong2);
}
