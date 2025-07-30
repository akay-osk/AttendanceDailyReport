package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.DailyReport;
import com.example.demo.model.User;

public interface DailyReportRepository extends JpaRepository<DailyReport, Integer> {
	List<DailyReport> findByUser_Id(Integer userId);
	
	List<DailyReport> findByUserOrderByReportDateDesc(User user);
	
}
