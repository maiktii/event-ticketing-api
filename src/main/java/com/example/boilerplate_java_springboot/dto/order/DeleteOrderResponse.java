package com.example.boilerplate_java_springboot.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteOrderResponse<B> {
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;
    private Boolean result;
}
