package com.codestates.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderCoffeeResponseDto {


    private long coffeeId;
    private String korName;
    private String engName;
    private Integer price;
    private Integer quantity;

    @Builder
    public OrderCoffeeResponseDto(long coffeeId, String korName, String engName, Integer price, Integer quantity) {
        this.coffeeId = coffeeId;
        this.korName = korName;
        this.engName = engName;
        this.price = price;
        this.quantity = quantity;
    }

}
