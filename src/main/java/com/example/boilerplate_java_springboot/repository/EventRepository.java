package com.example.boilerplate_java_springboot.repository;

import com.example.boilerplate_java_springboot.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
}
