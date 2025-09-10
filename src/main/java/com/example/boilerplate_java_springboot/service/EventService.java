package com.example.boilerplate_java_springboot.service;

import com.example.boilerplate_java_springboot.dto.event.CreateEventRequest;
import com.example.boilerplate_java_springboot.dto.event.EventResponse;
import com.example.boilerplate_java_springboot.dto.user.UserResponse;
import com.example.boilerplate_java_springboot.entity.EventEntity;
import com.example.boilerplate_java_springboot.entity.UserEntity;
import com.example.boilerplate_java_springboot.repository.EventRepository;
import com.example.boilerplate_java_springboot.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventService(EventRepository eventRepository, UserRepository userRepository){
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<EventResponse<EventEntity>> createEvent(CreateEventRequest createEntityRequest){

        EventEntity dataEvent = EventEntity.builder()
                .eventName(createEntityRequest.getEventName())
                .eventAddress(createEntityRequest.getEventAddress())
                .build();

        var savedData = eventRepository.save(dataEvent);

        EventResponse<EventEntity> responseCreate = new EventResponse<>(
                HttpStatus.CREATED.value(),
                "Success Create Data Application",
                null,
                savedData
        );

        return ResponseEntity.ok(responseCreate);
    }

    public ResponseEntity<EventResponse<List<EventEntity>>> getAllData(){
        List<EventEntity> dataEvent = eventRepository.findAll();

        EventResponse<List<EventEntity>> responseList = new EventResponse<>(
                HttpStatus.CREATED.value(),
                "Success List All data",
                null,
                dataEvent
        );

        return ResponseEntity.ok(responseList);
    }

    public ResponseEntity<EventResponse<List<UserEntity>>> getListEventByParticipantId(Long id){
        EventEntity event = eventRepository.findById(id).orElse(null);
        List<UserEntity> result = new ArrayList<>();
        List<UserEntity> dataUser = userRepository.findAll();
        
        if (event == null){
            EventResponse<List<UserEntity>> responseEvent = new EventResponse<>(
                    HttpStatus.NOT_FOUND.value(),
                    "Event ID is null",
                    LocalDateTime.now(),
                    result
            );

            return ResponseEntity.ok(responseEvent);
        }

        for (UserEntity user : dataUser){
            if((long)user.getEventID() == event.getId()){
                result.add(user);
            }
        }

        EventResponse<List<UserEntity>> responseEvent = new EventResponse<>(
                HttpStatus.OK.value(),
                "Success fetch data",
                LocalDateTime.now(),
                result
        );

        return ResponseEntity.ok(responseEvent);

    }
}
