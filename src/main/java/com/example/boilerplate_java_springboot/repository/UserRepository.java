package com.example.boilerplate_java_springboot.repository;

import com.example.boilerplate_java_springboot.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
