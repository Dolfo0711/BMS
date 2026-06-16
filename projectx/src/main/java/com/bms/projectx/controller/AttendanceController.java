package com.bms.projectx.controller;

import com.bms.projectx.entity.AttendanceEntity;
import com.bms.projectx.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // GET /api/attendances
    @GetMapping
    public ResponseEntity<List<AttendanceEntity>> index() {
        return ResponseEntity.ok(attendanceService.findAll());
    }

    // GET /api/attendances/{id}
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceEntity> show(@PathVariable Long id) {
        return ResponseEntity.ok(attendanceService.findById(id));
    }

    // GET /api/attendances/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AttendanceEntity>> findByUser(
            @PathVariable Long userId) {
        return ResponseEntity.ok(attendanceService.findByUserId(userId));
    }

    // POST /api/attendances
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<AttendanceEntity> store(
            @RequestParam String type,
            @RequestParam BigDecimal latitude,
            @RequestParam BigDecimal longitude,
            @RequestParam MultipartFile picture
    ) throws IOException {

        AttendanceEntity created = attendanceService.save(
                type,
                latitude,
                longitude,
                picture
        );

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // PUT /api/attendances/{id}
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceEntity> update(
            @PathVariable Long id,
            @RequestBody AttendanceEntity attendance) {
        return ResponseEntity.ok(attendanceService.update(id, attendance));
    }

    // DELETE /api/attendances/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        attendanceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}