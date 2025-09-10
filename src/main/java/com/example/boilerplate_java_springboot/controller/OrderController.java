package com.example.boilerplate_java_springboot.controller;

import com.example.boilerplate_java_springboot.dto.order.CreateOrderRequest;
import com.example.boilerplate_java_springboot.dto.order.OrderResponse;
import com.example.boilerplate_java_springboot.entity.EventEntity;
import com.example.boilerplate_java_springboot.entity.OrderEntity;
import com.example.boilerplate_java_springboot.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse<OrderEntity>> createUser(@Valid @RequestBody CreateOrderRequest createOrderRequest){
        return orderService.createUser(createOrderRequest);
    }

    @GetMapping("/{id}/event")
    public ResponseEntity<OrderResponse<List<EventEntity>>> getListParticipantByEventID(
            @PathVariable Long id){
        return orderService.getListEventByParticipantId(id);
    }
}
