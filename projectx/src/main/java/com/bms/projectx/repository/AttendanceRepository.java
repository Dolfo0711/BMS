package com.bms.projectx.repository;

import com.bms.projectx.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {

    List<AttendanceEntity> findByUserId(Long userId);

}