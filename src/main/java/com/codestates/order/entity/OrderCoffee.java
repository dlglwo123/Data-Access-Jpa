package com.codestates.order.entity;

import com.codestates.coffee.entity.Coffee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Table(name = "ORDERS_COFFEE")
@Entity
public class OrderCoffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderCoffeeID;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    public void addOrder(Order order){
        this.order = order;
    }
    // ======================================
    @ManyToOne
    @JoinColumn(name = "COFFEE_ID")
    private Coffee coffee;

    public void addCoffee(Coffee coffee){
        this.coffee = coffee;
    }

    @Column(nullable = false)
    @Setter
    private int quantity;

    public OrderCoffee(int quantity){
        this.quantity = quantity;
    }
}
