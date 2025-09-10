package com.example.boilerplate_java_springboot.repository;

import com.example.boilerplate_java_springboot.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
