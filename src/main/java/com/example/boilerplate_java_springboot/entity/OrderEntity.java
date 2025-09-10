package com.example.boilerplate_java_springboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jdk.jfr.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameOrder;
    private String emailOrder;

    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private String orderStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity eventEntity;

}
