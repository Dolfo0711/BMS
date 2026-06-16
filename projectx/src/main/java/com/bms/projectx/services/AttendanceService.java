package com.bms.projectx.service;

import com.bms.projectx.entity.AttendanceEntity;
import com.bms.projectx.entity.UserEntity;
import com.bms.projectx.repository.AttendanceRepository;
import com.bms.projectx.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }

    public List<AttendanceEntity> findAll() {
        return attendanceRepository.findAll();
    }

    public AttendanceEntity findById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));
    }

    public List<AttendanceEntity> findByUserId(Long userId) {
        return attendanceRepository.findByUserId(userId);
    }

    public AttendanceEntity save(
            Long userId,
            String type,
            BigDecimal latitude,
            BigDecimal longitude,
            MultipartFile picture) throws IOException {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String uploadDir = "uploads/attendance/";

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = UUID.randomUUID() + "_" + picture.getOriginalFilename();

        Path path = Paths.get(uploadDir, fileName);

        Files.copy(picture.getInputStream(), path);

        AttendanceEntity attendance = new AttendanceEntity();
        attendance.setUser(user);
        attendance.setDateTime(LocalDateTime.now());
        attendance.setType(type);
        attendance.setLatitude(latitude);
        attendance.setLongitude(longitude);
        attendance.setPicture(uploadDir + fileName);

        return attendanceRepository.save(attendance);
    }

    public AttendanceEntity update(Long id, AttendanceEntity attendance) {
        AttendanceEntity existing = findById(id);

        existing.setDateTime(attendance.getDateTime());
        existing.setType(attendance.getType());
        existing.setPicture(attendance.getPicture());
        existing.setLatitude(attendance.getLatitude());
        existing.setLongitude(attendance.getLongitude());
        existing.setUser(attendance.getUser());

        return attendanceRepository.save(existing);
    }

    public void delete(Long id) {
        attendanceRepository.deleteById(id);
    }
}