package com.example.demo.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Attendance;
import com.example.demo.model.User;
import com.example.demo.servic.AttendanceService;

@Controller
public class AttendanceController {
	
	@Autowired
	private AttendanceService attendanceService;
	
	@PostMapping("/clockin")
	public String clockIn(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
//		System.out.println("User");
//		System.out.println(user.toString());
//		System.out.println(user.getName());
		Attendance attendance = attendanceService.clockIn(user);
		String time = attendance.getClockIn().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分"));
		model.addAttribute("message", time + "に出勤。今日も一日よろしくお願いします！");
		model.addAttribute("loginId", user.getLoginId());
		model.addAttribute("name", user.getName());
		return "home";
	}
	
	@PostMapping("/clockout")
	public String clockOut(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		Attendance attendance = attendanceService.clockOut(user);
		String time = attendance.getClockOut().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分"));
		model.addAttribute("message", time + "に退勤。お疲れ様でした！ゆっくり休んでくださいね");
		model.addAttribute("loginId", user.getLoginId());
		model.addAttribute("name", user.getName());
		return "home";
	}
	
	@GetMapping("/attendanceRecord")
	public String showAttendanceHistory(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		List<Attendance> attendances = attendanceService.getAttendancesByUser(user);
		model.addAttribute("attendances", attendances);
		return "attendance_record";
	}
	

}
