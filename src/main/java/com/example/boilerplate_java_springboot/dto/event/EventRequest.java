package com.example.boilerplate_java_springboot.dto.event;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    @NotBlank
    private String eventName;

    @NotBlank
    private String eventAddress;

    @NotBlank
    private String location;

    @NotBlank
    private int capacity;
}
