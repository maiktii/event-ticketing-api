package com.example.boilerplate_java_springboot.controller;

import com.example.boilerplate_java_springboot.dto.event.EventRequest;
import com.example.boilerplate_java_springboot.dto.event.EventResponse;
import com.example.boilerplate_java_springboot.entity.EventEntity;
import com.example.boilerplate_java_springboot.entity.OrderEntity;
import com.example.boilerplate_java_springboot.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping("/createEvent")
    public EventEntity createEvent(@RequestBody EventRequest eventRequest){
        return eventService.createEvent(eventRequest);
    }

    @GetMapping
    public List<EventEntity> getAllEvents(){
        return eventService.getAllEvent();
    }

    @GetMapping("/{id}")
    public Optional<EventEntity> getEventById(@PathVariable Long id){
        return eventService.getEventById(id);
    }

}
