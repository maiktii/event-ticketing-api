package com.example.boilerplate_java_springboot.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListOrderResponse<T> {
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;
    private List<CreateOrderResponse> result;
}
