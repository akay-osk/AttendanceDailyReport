package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
	Optional<Attendance> findTopByUser_IdOrderByIdDesc(Integer userId);
	List<Attendance> findByUserIdOrderByClockInDesc(Integer userId);		//ログイン中のユーザーの勤怠記録を、「出勤時刻が新しい順」に並べて取得する


}
