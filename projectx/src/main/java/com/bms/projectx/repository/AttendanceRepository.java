package com.bms.projectx.repository;

import com.bms.projectx.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {

    List<AttendanceEntity> findByUserId(Long userId);
    Optional<AttendanceEntity> findTopByUserIdAndDateTimeBetweenOrderByDateTimeDesc(
            Long userId,
            LocalDateTime start,
            LocalDateTime end
    );

}