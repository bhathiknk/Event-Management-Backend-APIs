package com.event_management.controller;

import com.event_management.model.Attendance;
import com.event_management.model.User;
import com.event_management.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/mark/{eventId}/{userId}")
    public ResponseEntity<Attendance> markAttendance(@PathVariable Long eventId, @PathVariable Long userId) {
        Attendance attendance = attendanceService.markAttendance(eventId, userId);
        return new ResponseEntity<>(attendance, HttpStatus.CREATED);
    }



    @GetMapping("/event/{eventId}/attendees")
    public ResponseEntity<List<User>> getEventAttendees(@PathVariable Long eventId) {
        List<User> attendees = attendanceService.getUsersAttendedEvent(eventId);
        if (attendees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(attendees, HttpStatus.OK);
    }
}
