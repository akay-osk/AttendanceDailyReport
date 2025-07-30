package com.example.demo.servic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DailyReport;
import com.example.demo.repository.DailyReportRepository;

@Service
public class DailyReportService {
	
	@Autowired
	private DailyReportRepository dailyReportRepository;
	
	public void saveReport(DailyReport report) {
		dailyReportRepository.save(report);
	}
	
	public List<DailyReport> getReportsByUser(Integer userId){
		return dailyReportRepository.findByUser_Id(userId);
	}
	

}
