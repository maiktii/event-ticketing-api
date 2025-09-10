package com.example.boilerplate_java_springboot.service;

import com.example.boilerplate_java_springboot.dto.order.CreateOrderRequest;
import com.example.boilerplate_java_springboot.dto.order.OrderResponse;
import com.example.boilerplate_java_springboot.entity.EventEntity;
import com.example.boilerplate_java_springboot.entity.OrderEntity;
import com.example.boilerplate_java_springboot.repository.EventRepository;
import com.example.boilerplate_java_springboot.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final EventRepository eventRepository;
    private final OrderRepository orderRepository;

    public OrderService(EventRepository eventRepository, OrderRepository orderRepository){
        this.eventRepository = eventRepository;
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<OrderResponse<OrderEntity>> createUser(CreateOrderRequest createOrderRequest){

        OrderEntity dataUser = OrderEntity.builder()
                .eventID(createOrderRequest.getEventID())
                .username(createOrderRequest.getUsername())
                .email(createOrderRequest.getEmail())
                .build();

        var savedData = orderRepository.save(dataUser);

        OrderResponse<OrderEntity> responseCreate = new OrderResponse<>(
                HttpStatus.CREATED.value(),
                "Success Create Data Application",
                null,
                savedData
        );

        return ResponseEntity.ok(responseCreate);
    }

    public ResponseEntity<OrderResponse<List<EventEntity>>> getListEventByParticipantId(Long id){
        OrderEntity participant = orderRepository.findById(id).orElse(null);
        List<EventEntity> result = new ArrayList<>();
        List<EventEntity> dataEvent = eventRepository.findAll();

        for (EventEntity event : dataEvent){
            if((long)participant.getEventID() == event.getId()){
                result.add(event);
            }
        }

        OrderResponse<List<EventEntity>> responseUser = new OrderResponse<>(
                HttpStatus.OK.value(),
                "Success fetch data",
                LocalDateTime.now(),
                result
        );

        return ResponseEntity.ok(responseUser);

    }
}
