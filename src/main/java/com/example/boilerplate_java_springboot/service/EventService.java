package com.example.boilerplate_java_springboot.service;

import com.example.boilerplate_java_springboot.dto.event.CreateEventRequest;
import com.example.boilerplate_java_springboot.dto.event.EventResponse;
import com.example.boilerplate_java_springboot.entity.EventEntity;
import com.example.boilerplate_java_springboot.entity.OrderEntity;
import com.example.boilerplate_java_springboot.repository.EventRepository;
import com.example.boilerplate_java_springboot.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final OrderRepository orderRepository;

    public EventService(EventRepository eventRepository, OrderRepository orderRepository){
        this.eventRepository = eventRepository;
        this.orderRepository = orderRepository;
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

    public ResponseEntity<EventResponse<List<OrderEntity>>> getListEventByParticipantId(Long id){
        EventEntity event = eventRepository.findById(id).orElse(null);
        List<OrderEntity> result = new ArrayList<>();
        List<OrderEntity> dataUser = orderRepository.findAll();
        
        if (event == null){
            EventResponse<List<OrderEntity>> responseEvent = new EventResponse<>(
                    HttpStatus.NOT_FOUND.value(),
                    "Event ID is null",
                    LocalDateTime.now(),
                    result
            );

            return ResponseEntity.ok(responseEvent);
        }

        for (OrderEntity user : dataUser){
            if((long)user.getEventID() == event.getId()){
                result.add(user);
            }
        }

        EventResponse<List<OrderEntity>> responseEvent = new EventResponse<>(
                HttpStatus.OK.value(),
                "Success fetch data",
                LocalDateTime.now(),
                result
        );

        return ResponseEntity.ok(responseEvent);

    }
}
