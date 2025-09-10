package com.example.boilerplate_java_springboot.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponse {
    private Long id;

    private String nameOrder;
    private String emailOrder;

    private double totalAmount;

    private String orderStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
