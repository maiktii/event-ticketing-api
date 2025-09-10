package com.example.boilerplate_java_springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String eventName;
    private String eventAddress;
    private String location;

    private int capacity;

    @OneToMany(mappedBy = "eventEntity")
    private List<OrderEntity> order;

}
