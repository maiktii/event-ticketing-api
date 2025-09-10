package com.example.boilerplate_java_springboot.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse<T> {
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;
    private T result;
}
