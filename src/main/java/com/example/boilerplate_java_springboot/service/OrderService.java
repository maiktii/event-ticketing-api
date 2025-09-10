package com.example.boilerplate_java_springboot.service;

import com.example.boilerplate_java_springboot.dto.order.CreateOrderRequest;
import com.example.boilerplate_java_springboot.dto.order.OrderResponse;
import com.example.boilerplate_java_springboot.entity.EventEntity;
import com.example.boilerplate_java_springboot.entity.OrderEntity;
import com.example.boilerplate_java_springboot.repository.EventRepository;
import com.example.boilerplate_java_springboot.repository.OrderRepository;
import org.apache.coyote.Response;
import org.hibernate.query.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
  private final OrderRepository orderRepository;
  private final EventRepository eventRepository;

  public OrderService(OrderRepository orderRepository, EventRepository eventRepository){
      this.orderRepository = orderRepository;
      this.eventRepository = eventRepository;
  }

  public ResponseEntity<OrderResponse<OrderEntity>> createOrder(CreateOrderRequest createOrderRequest){
      EventEntity dataEvent = eventRepository.findById((long)createOrderRequest.getEventID()).orElse(null);

      OrderResponse<OrderEntity> responseOrder = new OrderResponse<>();

      if(dataEvent == null){
          responseOrder.setStatusCode(HttpStatus.BAD_GATEWAY.value());
          responseOrder.setMessage("Data Event Not Found!");
          responseOrder.setTimestamp(LocalDateTime.now());
          responseOrder.setResult(null);
          return ResponseEntity.ok(responseOrder);
      }

      if(!createOrderRequest.getOrderStatus().equalsIgnoreCase("PENDING")
      && !createOrderRequest.getOrderStatus().equalsIgnoreCase("PAID")
      && !createOrderRequest.getOrderStatus().equalsIgnoreCase("REFUNED")){
          responseOrder.setStatusCode(HttpStatus.BAD_REQUEST.value());
          responseOrder.setMessage("Order Status Invalid!");
          responseOrder.setTimestamp(LocalDateTime.now());
          responseOrder.setResult(null);
          return ResponseEntity.ok(responseOrder);
      }

      OrderEntity orderEntity = orderRepository.save(OrderEntity.builder()
                      .id(null).nameOrder(createOrderRequest.getNameOrder())
                      .emailOrder(createOrderRequest.getEmailOrder())
                      .totalAmount(createOrderRequest.getTotalAmount())
                      .orderStatus(createOrderRequest.getOrderStatus())
                      .eventEntity(dataEvent)
                      .createdAt(LocalDateTime.now())
                      .updatedAt(LocalDateTime.now())
                      .deletedAt(null)
              .build());

      responseOrder.setStatusCode(HttpStatus.CREATED.value());
      responseOrder.setMessage("Success Created Order!");
      responseOrder.setTimestamp(LocalDateTime.now());
      responseOrder.setResult(orderEntity);
      return ResponseEntity.ok(responseOrder);
  }

  public ResponseEntity<OrderResponse<OrderEntity>> findOrderDetailById(Long id){
      OrderEntity dataOrder = orderRepository.findById(id).orElse(null);
      OrderResponse<OrderEntity> responseOrder = new OrderResponse<>();

      if(dataOrder == null){
          responseOrder.setStatusCode(HttpStatus.BAD_GATEWAY.value());
          responseOrder.setMessage("Data Order Not Found!");
          responseOrder.setTimestamp(LocalDateTime.now());
          responseOrder.setResult(null);
          return ResponseEntity.ok(responseOrder);
      }

      responseOrder.setStatusCode(HttpStatus.OK.value());
      responseOrder.setMessage("Success Find Order Data!");
      responseOrder.setTimestamp(LocalDateTime.now());
      responseOrder.setResult(dataOrder);
      return ResponseEntity.ok(responseOrder);
  }
}
