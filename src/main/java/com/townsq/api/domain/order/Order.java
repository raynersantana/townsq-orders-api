package com.townsq.api.domain.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.townsq.api.domain.cart.CartItems;
import com.townsq.api.domain.payment.Payment;
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
    private OrderStatus orderStatus;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    @JsonManagedReference
    private List<CartItems> cartItems;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "order")
    @JsonManagedReference
    private Payment payment;

    public Order(Long id, Long totalPrice, OrderStatus orderStatus, Payment payment) {
    }

    public Order(Long id, Long totalPrice, OrderStatus orderStatus, User user) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.user = user;
    }
}