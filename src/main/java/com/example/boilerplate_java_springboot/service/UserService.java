package com.example.boilerplate_java_springboot.service;

import com.example.boilerplate_java_springboot.dto.event.CreateEventRequest;
import com.example.boilerplate_java_springboot.dto.event.EventResponse;
import com.example.boilerplate_java_springboot.dto.user.CreateUserRequest;
import com.example.boilerplate_java_springboot.dto.user.UserResponse;
import com.example.boilerplate_java_springboot.entity.EventEntity;
import com.example.boilerplate_java_springboot.entity.UserEntity;
import com.example.boilerplate_java_springboot.repository.EventRepository;
import com.example.boilerplate_java_springboot.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public UserService(EventRepository eventRepository, UserRepository userRepository){
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<UserResponse<UserEntity>> createUser(CreateUserRequest createUserRequest){

        UserEntity dataUser = UserEntity.builder()
                .eventID(createUserRequest.getEventID())
                .username(createUserRequest.getUsername())
                .email(createUserRequest.getEmail())
                .build();

        var savedData = userRepository.save(dataUser);

        UserResponse<UserEntity> responseCreate = new UserResponse<>(
                HttpStatus.CREATED.value(),
                "Success Create Data Application",
                null,
                savedData
        );

        return ResponseEntity.ok(responseCreate);
    }

    public ResponseEntity<UserResponse<List<EventEntity>>> getListEventByParticipantId(Long id){
        UserEntity participant = userRepository.findById(id).orElse(null);
        List<EventEntity> result = new ArrayList<>();
        List<EventEntity> dataEvent = eventRepository.findAll();

        for (EventEntity event : dataEvent){
            if((long)participant.getEventID() == event.getId()){
                result.add(event);
            }
        }

        UserResponse<List<EventEntity>> responseUser = new UserResponse<>(
                HttpStatus.OK.value(),
                "Success fetch data",
                LocalDateTime.now(),
                result
        );

        return ResponseEntity.ok(responseUser);

    }
}
