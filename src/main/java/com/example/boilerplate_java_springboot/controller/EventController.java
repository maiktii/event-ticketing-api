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
    public List<EventEntity> getAllEvent(){
        return eventService.getAllEvent();
    }

    @GetMapping
    public Optional<EventEntity> getEventById(Long id){
        return eventService.getEventById(id);
    }



//    @PostMapping
//    public ResponseEntity<EventResponse<EventEntity>> CreateEvent(
//            @Valid @RequestBody EventRequest eventRequest){
//        return eventService.createEvent(eventRequest);
//    }
//
//    @GetMapping
//    public ResponseEntity<EventResponse<List<EventEntity>>> getAllEvent(){
//        return eventService.getAllData();
//    }
//
//    @GetMapping("/{id}/user")
//    public ResponseEntity<EventResponse<List<OrderEntity>>> getListEventByParticipantId(@PathVariable Long id){
//        return eventService.getListEventByParticipantId(id);
//    }
}
