package com.example.boilerplate_java_springboot.controller;

import com.example.boilerplate_java_springboot.dto.user.CreateUserRequest;
import com.example.boilerplate_java_springboot.dto.user.UserResponse;
import com.example.boilerplate_java_springboot.entity.EventEntity;
import com.example.boilerplate_java_springboot.entity.UserEntity;
import com.example.boilerplate_java_springboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse<UserEntity>> createUser(@Valid @RequestBody CreateUserRequest createUserRequest){
        return userService.createUser(createUserRequest);
    }

    @GetMapping("/{id}/event")
    public ResponseEntity<UserResponse<List<EventEntity>>> getListParticipantByEventID(
            @PathVariable Long id){
        return userService.getListEventByParticipantId(id);
    }
}
