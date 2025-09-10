package com.example.boilerplate_java_springboot.controller;

import com.example.boilerplate_java_springboot.dto.event.CreateEventRequest;
import com.example.boilerplate_java_springboot.dto.event.EventResponse;
import com.example.boilerplate_java_springboot.entity.EventEntity;
import com.example.boilerplate_java_springboot.entity.OrderEntity;
import com.example.boilerplate_java_springboot.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventResponse<EventEntity>> CreateEvent(
            @Valid @RequestBody CreateEventRequest createEventRequest){
        return eventService.createEvent(createEventRequest);
    }

    @GetMapping
    public ResponseEntity<EventResponse<List<EventEntity>>> getAllEvent(){
        return eventService.getAllData();
    }

    @GetMapping("/{id}/user")
    public ResponseEntity<EventResponse<List<OrderEntity>>> getListEventByParticipantId(@PathVariable Long id){
        return eventService.getListEventByParticipantId(id);
    }
}
