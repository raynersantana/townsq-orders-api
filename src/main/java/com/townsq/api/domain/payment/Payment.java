package com.townsq.api.domain.payment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.townsq.api.domain.order.Order;
import com.townsq.api.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "payments")
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
public class Payment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Order order;

    private PaymentStatus paymentStatus;

    public Payment(Order orderSaved, User user, PaymentType paymentType, PaymentStatus paymentStatus) {
        this.order = orderSaved;
        this.user = user;
        this.paymentType = paymentType;
        this.paymentStatus = paymentStatus;
    }
}
