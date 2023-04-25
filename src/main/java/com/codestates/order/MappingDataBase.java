package com.codestates.order;

import com.codestates.coffee.entity.Coffee;
import com.codestates.member.entity.Member;
import com.codestates.member.entity.Stamp;
import com.codestates.order.entity.Order;
import com.codestates.order.entity.OrderCoffee;
import org.aspectj.weaver.ast.Or;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.swing.text.html.parser.Entity;

@Configuration
public class MappingDataBase {

    private EntityManager em;

    private EntityTransaction tx;

    @Bean
    public CommandLineRunner EntityManagerCreate(EntityManagerFactory ef){
        this.em = ef.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            CreateDataBase();
        };
    }

    public void CreateDataBase(){
        tx.begin();

        // member와 stamp 관계 매핑
        Member member = new Member("dlglwo123@naver.com","이희재","020-2302-2123");
        Stamp stamp = new Stamp(1);
        member.addStamp(stamp);
        stamp.addMember(member);

        // Member 와 Order 연관관계 매핑
        Order order = new Order();
        order.addMember(member);
        member.addOrder(order);

        // orderCoffee 와 Order 매핑
        Coffee coffee = new Coffee("카라멜 라떼","Caramel Latte",4500,"CAF");
        OrderCoffee orderCoffee = new OrderCoffee(2);


        order.addOrderCoffee(orderCoffee);
        orderCoffee.addOrder(order);

        // order, coffee 매핑
        orderCoffee.addCoffee(coffee);
        coffee.addOrderCoffee(orderCoffee);

        em.persist(member);
        em.persist(stamp);
        em.persist(order);
        em.persist(orderCoffee);
        em.persist(coffee);

        tx.commit();
    }
}
