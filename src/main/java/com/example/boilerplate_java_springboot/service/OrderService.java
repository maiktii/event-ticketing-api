package com.example.boilerplate_java_springboot.service;

import com.example.boilerplate_java_springboot.dto.order.*;
import com.example.boilerplate_java_springboot.entity.EventEntity;
import com.example.boilerplate_java_springboot.entity.OrderEntity;
import com.example.boilerplate_java_springboot.repository.EventRepository;
import com.example.boilerplate_java_springboot.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
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
          responseOrder.setStatusCode(HttpStatus.NOT_FOUND.value());
          responseOrder.setMessage("Data Event Not Found!");
          responseOrder.setTimestamp(LocalDateTime.now());
          responseOrder.setResult(null);
          return ResponseEntity.ok(responseOrder);
      }

//      if(!createOrderRequest.getOrderStatus().equalsIgnoreCase("PENDING")
//      && !createOrderRequest.getOrderStatus().equalsIgnoreCase("PAID")
//      && !createOrderRequest.getOrderStatus().equalsIgnoreCase("REFUNED")){
//          responseOrder.setStatusCode(HttpStatus.BAD_REQUEST.value());
//          responseOrder.setMessage("Order Status Invalid!");
//          responseOrder.setTimestamp(LocalDateTime.now());
//          responseOrder.setResult(null);
//          return ResponseEntity.ok(responseOrder);
//      }

      OrderEntity orderEntity = orderRepository.save(OrderEntity.builder()
                      .id(null).nameOrder(createOrderRequest.getNameOrder())
                      .emailOrder(createOrderRequest.getEmailOrder())
                      .totalAmount(createOrderRequest.getTotalAmount())
                      .orderStatus(createOrderRequest.getOrderStatus().toString())
                      .eventEntity(dataEvent)
                      .isUsed(false)
                      .createdAt(LocalDateTime.now())
                      .updatedAt(LocalDateTime.now())
                      .deletedAt(null)
              .build());

      var orderResponse = new CreateOrderResponse(orderEntity.getId(),
              orderEntity.getNameOrder(), orderEntity.getEmailOrder(), orderEntity.getTotalAmount(),
              orderEntity.getOrderStatus(), orderEntity.getIsUsed(),
              orderEntity.getCreatedAt(), orderEntity.getUpdatedAt(), orderEntity.getDeletedAt());

      responseOrder.setStatusCode(HttpStatus.CREATED.value());
      responseOrder.setMessage("Success Created Order!");
      responseOrder.setTimestamp(LocalDateTime.now());
      responseOrder.setResult(orderResponse);
      return ResponseEntity.ok(responseOrder);
  }

  public ResponseEntity<OrderResponse<OrderEntity>> findOrderDetailById(Long id){
      OrderEntity dataOrder = orderRepository.findById(id).orElse(null);
      OrderResponse<OrderEntity> responseOrder = new OrderResponse<>();

      if(dataOrder == null){
          responseOrder.setStatusCode(HttpStatus.NOT_FOUND.value());
          responseOrder.setMessage("Data Order Not Found!");
          responseOrder.setTimestamp(LocalDateTime.now());
          responseOrder.setResult(null);
          return ResponseEntity.ok(responseOrder);
      }

      var orderResponse = new CreateOrderResponse(dataOrder.getId(),
              dataOrder.getNameOrder(), dataOrder.getEmailOrder(), dataOrder.getTotalAmount(),
              dataOrder.getOrderStatus(), dataOrder.getIsUsed(),
              dataOrder.getCreatedAt(), dataOrder.getUpdatedAt(), dataOrder.getDeletedAt());

      responseOrder.setStatusCode(HttpStatus.OK.value());
      responseOrder.setMessage("Success Find Order Data!");
      responseOrder.setTimestamp(LocalDateTime.now());
      responseOrder.setResult(orderResponse);
      return ResponseEntity.ok(responseOrder);
  }

  public ResponseEntity<CheckInResponse<Boolean>> validateCheckIN(CheckInRequest checkInRequest){
      OrderEntity dataOrder = orderRepository.findById((long)checkInRequest.getOrderID()).orElse(null);

      CheckInResponse<Boolean> responseCheckIn = new CheckInResponse<>();

      if (dataOrder == null){
          responseCheckIn.setStatusCode(HttpStatus.NOT_FOUND.value());
          responseCheckIn.setMessage("Data Order Not Found!");
          responseCheckIn.setTimestamp(LocalDateTime.now());
          responseCheckIn.setResult(false);
          return ResponseEntity.ok(responseCheckIn);
      }

      if (dataOrder.getIsUsed() == true){
          responseCheckIn.setStatusCode(HttpStatus.OK.value());
          responseCheckIn.setMessage("Order already validated!");
          responseCheckIn.setTimestamp(LocalDateTime.now());
          responseCheckIn.setResult(true);
          return ResponseEntity.ok(responseCheckIn);
      }

      if (!dataOrder.getOrderStatus().equalsIgnoreCase("PAID")){
          responseCheckIn.setStatusCode(HttpStatus.BAD_GATEWAY.value());
          responseCheckIn.setMessage("Order hasn't been paid!");
          responseCheckIn.setTimestamp(LocalDateTime.now());
          responseCheckIn.setResult(false);
          return ResponseEntity.ok(responseCheckIn);
      }

      dataOrder.setIsUsed(true);
      dataOrder.setUpdatedAt(LocalDateTime.now());
      orderRepository.save(dataOrder);

      responseCheckIn.setStatusCode(HttpStatus.OK.value());
      responseCheckIn.setMessage("Order is validated!");
      responseCheckIn.setTimestamp(LocalDateTime.now());
      responseCheckIn.setResult(true);
      return ResponseEntity.ok(responseCheckIn);
  }

  public ResponseEntity<DeleteOrderResponse<Boolean>> softDeleteOrder(Long id){
      OrderEntity dataOrder = orderRepository.findById(id).orElse(null);
      DeleteOrderResponse<Boolean> responseDelete = new DeleteOrderResponse<>();
      if (dataOrder == null){
          responseDelete.setStatusCode(HttpStatus.NOT_FOUND.value());
          responseDelete.setMessage("Data Order Not Found!");
          responseDelete.setTimestamp(LocalDateTime.now());
          responseDelete.setResult(false);
          return ResponseEntity.ok(responseDelete);
      }

      if (dataOrder.getIsUsed() == true){
          responseDelete.setStatusCode(HttpStatus.BAD_GATEWAY.value());
          responseDelete.setMessage("Ticket already used!");
          responseDelete.setTimestamp(LocalDateTime.now());
          responseDelete.setResult(false);
          return ResponseEntity.ok(responseDelete);
      }

      dataOrder.setDeletedAt(LocalDateTime.now());
      dataOrder.setNameOrder(dataOrder.getNameOrder()+"_DELETED");
      orderRepository.save(dataOrder);

      responseDelete.setStatusCode(HttpStatus.OK.value());
      responseDelete.setMessage("Success delete order!");
      responseDelete.setTimestamp(LocalDateTime.now());
      responseDelete.setResult(true);
      return ResponseEntity.ok(responseDelete);
  }

  public ResponseEntity<ListOrderResponse<OrderEntity>> getAllOrder() {
      List<OrderEntity> arrDataOrder = orderRepository.findAll();
      ArrayList<CreateOrderResponse> arrResult = new ArrayList<>();
      ListOrderResponse<OrderEntity> responseList = new ListOrderResponse<>();

      if (arrDataOrder.isEmpty()) {
          responseList.setStatusCode(HttpStatus.NOT_FOUND.value());
          responseList.setMessage("Data Order Not Found!");
          responseList.setTimestamp(LocalDateTime.now());
          responseList.setResult(null);
          return ResponseEntity.ok(responseList);
      }

      for (OrderEntity order : arrDataOrder) {
          if (order.getDeletedAt() != null) {
              continue;
          }

          CreateOrderResponse resultOrder = new CreateOrderResponse(
                  order.getId(),
                  order.getNameOrder(),
                  order.getEmailOrder(),
                  order.getTotalAmount(),
                  order.getOrderStatus(),
                  order.getIsUsed(),
                  order.getCreatedAt(),
                  order.getUpdatedAt(),
                  order.getDeletedAt()
          );
          arrResult.add(resultOrder);
      }
      responseList.setStatusCode(HttpStatus.OK.value());
      responseList.setMessage("Success List Order!");
      responseList.setTimestamp(LocalDateTime.now());
      responseList.setResult(arrResult);
      return ResponseEntity.ok(responseList);
  }
}
