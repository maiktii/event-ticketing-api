package com.example.boilerplate_java_springboot.dto.event;

import com.example.boilerplate_java_springboot.dto.order.CreateOrderResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventEntityResponse {
    private Long id;
    private String eventName;
    private String eventAddress;
    private String location;
    private int capacity;
    private List<CreateOrderResponse> order;
}
