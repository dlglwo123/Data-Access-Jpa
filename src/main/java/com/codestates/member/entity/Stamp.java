package com.codestates.member.entity;

import com.codestates.coffee.entity.Coffee;
import com.codestates.order.entity.Order;
import com.codestates.order.entity.OrderCoffee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Stamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stampId;

    @Setter
    @Column(nullable = false)
    private int stampCount;

    public Stamp(int stampCount){
        this.stampCount = stampCount;
    }

    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(nullable = false,name = "LAST_MODIFIED_AT")
    private LocalDateTime ModifiedAt = LocalDateTime.now();

    // ========== member 연관관계 매핑 =============

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void addMember(Member member){
        this.member = member;
    }


    public void QuantityPlusStampCount(OrderCoffee orderCoffee,Member member){

        int stamp = orderCoffee.getQuantity();

        int memberStamp = member.getStamp().getStampCount();

        stampCount = memberStamp + stamp;
    }
}
