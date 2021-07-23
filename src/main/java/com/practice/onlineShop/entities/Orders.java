package com.practice.onlineShop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Collection<OrderItem> orderItems;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isDelivered;
    private boolean isReturned;
    private boolean isCancelled;


}
