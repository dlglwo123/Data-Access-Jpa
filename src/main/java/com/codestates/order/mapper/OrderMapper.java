package com.codestates.order.mapper;

import com.codestates.coffee.entity.Coffee;
import com.codestates.member.entity.Member;
import com.codestates.order.dto.OrderCoffeeResponseDto;
import com.codestates.order.dto.OrderPatchDto;
import com.codestates.order.dto.OrderPostDto;
import com.codestates.order.dto.OrderResponseDto;
import com.codestates.order.entity.Order;
import com.codestates.order.entity.OrderCoffee;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    default Order orderPostDtoToOrder(OrderPostDto orderPostDto)
    {
        Order order = new Order();
        Member member = new Member();
        member.setMemberId(orderPostDto.getMemberId());

        List<OrderCoffee> orderCoffees = orderPostDto.getOrderCoffees().stream()
                .map(orderCoffeeDto -> {
                    OrderCoffee orderCoffee = new OrderCoffee();
                    Coffee coffee = new Coffee();
                    coffee.setCoffeeId(orderCoffeeDto.getCoffeeId());
                    orderCoffee.addCoffee(coffee);
                    orderCoffee.addOrder(order);
                    orderCoffee.setQuantity(orderCoffeeDto.getQuantity());
                    return orderCoffee;
                }).collect(Collectors.toList());

        order.addMember(member);
        order.setOrderCoffees(orderCoffees);
        return order;
    }


    Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto);

    default OrderResponseDto orderToOrderResponseDto(Order order){

        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.setOrderId(order.getOrderId());

        orderResponseDto.setMemberId(order.getMember().getMemberId());

        orderResponseDto.setOrderStatus(order.getOrderStatus());

        orderResponseDto.setOrderCoffees(orderCoffeesToOrderCoffeeResponseDtos(order.getOrderCoffees()));

        orderResponseDto.setCreatedAt(LocalDateTime.now());

        return orderResponseDto;

    }

    default List<OrderCoffeeResponseDto> orderCoffeesToOrderCoffeeResponseDtos(List<OrderCoffee> orderCoffees){


        return orderCoffees.stream().map(
                orderCoffee -> OrderCoffeeResponseDto.builder()
                        .coffeeId(orderCoffee.getCoffee().getCoffeeId())
                        .korName(orderCoffee.getCoffee().getKorName())
                        .engName(orderCoffee.getCoffee().getEngName())
                        .price(orderCoffee.getCoffee().getPrice())
                        .quantity(orderCoffee.getQuantity()).build()
        ).collect(Collectors.toList());
    }

    List<OrderResponseDto> ordersToOrderResponseDtos(List<Order> orders);
}
