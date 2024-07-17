package com.event_management.service;

import com.event_management.model.Admin;
import com.event_management.model.Event;
import com.event_management.repository.AdminRepository;
import com.event_management.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public EventService(EventRepository eventRepository, AdminRepository adminRepository) {
        this.eventRepository = eventRepository;
        this.adminRepository = adminRepository;
    }

    public Event addEvent(Event event, Long adminId) {
        Optional<Admin> adminOptional = adminRepository.findById(adminId);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            event.setAdmin(admin);
            return eventRepository.save(event);
        } else {
            throw new RuntimeException("Admin not found with id: " + adminId);
        }
    }

    public List<Event> getEventsByAdminId(Long adminId) {
        return eventRepository.findByAdminId(adminId);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
