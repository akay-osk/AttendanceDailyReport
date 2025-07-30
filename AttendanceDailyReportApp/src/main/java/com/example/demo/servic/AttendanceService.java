package com.example.demo.servic;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Attendance;
import com.example.demo.model.User;
import com.example.demo.repository.AttendanceRepository;

@Service
public class AttendanceService {
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	public Attendance clockIn(User userId) {
		Attendance attendance = new Attendance();
		attendance.setUser(userId);

		attendance.setClockIn(LocalDateTime.now());
		return attendanceRepository.save(attendance);
	}
	
	public Attendance clockOut(User user) {
		 Optional<Attendance> latest = attendanceRepository.findTopByUser_IdOrderByIdDesc(user.getId());
		 if(latest.isPresent()) {
			 Attendance attendance = latest.get();
			 attendance.setClockOut(LocalDateTime.now());
			 return attendanceRepository.save(attendance);
		 }
		 return null;
	}
	
	//リポジトリから勤怠記録一覧を取得する処理
	public List<Attendance> getAttendancesByUser(User user){
		return attendanceRepository.findByUserIdOrderByClockInDesc(user.getId());
	}
}
