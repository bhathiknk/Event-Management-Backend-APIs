package com.event_management.controller;

import com.event_management.model.Event;
import com.event_management.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/add/{adminId}")
    public ResponseEntity<Event> addEvent(@RequestBody Event event, @PathVariable Long adminId) {
        Event addedEvent = eventService.addEvent(event, adminId);
        return new ResponseEntity<>(addedEvent, HttpStatus.CREATED);
    }

    @GetMapping("/get/{adminId}")
    public ResponseEntity<List<Event>> getEventsByAdminId(@PathVariable Long adminId) {
        List<Event> events = eventService.getEventsByAdminId(adminId);
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
