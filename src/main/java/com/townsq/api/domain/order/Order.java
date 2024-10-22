package com.townsq.api.domain.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.townsq.api.domain.cart.CartItems;
import com.townsq.api.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "orders")
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long totalPrice;
    private String paymentType;
    private OrderStatus orderStatus;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    @JsonManagedReference
    private List<CartItems> cartItems;

    public Order(Long id, Long totalPrice, OrderStatus orderStatus, String paymentType) {
    }
}