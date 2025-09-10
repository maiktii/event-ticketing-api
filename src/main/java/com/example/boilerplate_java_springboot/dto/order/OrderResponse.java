package com.example.boilerplate_java_springboot.dto.order;

import com.example.boilerplate_java_springboot.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse<T> {
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;
    private CreateOrderResponse result;
}
