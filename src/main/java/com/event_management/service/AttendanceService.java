package com.event_management.service;

import com.event_management.model.Attendance;
import com.event_management.model.Event;
import com.event_management.model.User;
import com.event_management.repository.AttendanceRepository;
import com.event_management.repository.EventRepository;
import com.event_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public Attendance markAttendance(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Attendance attendance = new Attendance();
        attendance.setEvent(event);
        attendance.setUser(user);
        attendance.setAttendedAt(LocalDateTime.now());

        return attendanceRepository.save(attendance);
    }



    public List<User> getUsersAttendedEvent(Long eventId) {
        List<Attendance> attendances = attendanceRepository.findByEventId(eventId);
        List<User> users = new ArrayList<>();

        for (Attendance attendance : attendances) {
            users.add(attendance.getUser());
        }

        return users;
    }
}
