package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.DailyReport;
import com.example.demo.model.User;
import com.example.demo.repository.DailyReportRepository;
import com.example.demo.servic.DailyReportService;

@Controller
public class DailyReportController {
	
	@Autowired
	private DailyReportService dailyReportService;
	
	@Autowired
	private DailyReportRepository dailyReportRepository;
	
	//フォーム表示
	@GetMapping("/report/new")
	public String showReportForm(Model model) {
		model.addAttribute("report", new DailyReport());
		return "dailyReport_form";
	}
	
	//フォーム送信
	@PostMapping("/report/submit")
	public String submitReport(@ModelAttribute DailyReport report, HttpSession session) {
		User user = (User) session.getAttribute("user");
		report.setUser(user);
		dailyReportService.saveReport(report);
		return "redirect:/home";
	}
	
	//日報一覧表示
	@GetMapping("/reports")
	public String listReports(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";		// セッションが切れていた場合
		}
		List<DailyReport> reports = dailyReportRepository.findByUserOrderByReportDateDesc(user);
		System .out .println("I am here! "+user);
		model.addAttribute("reports", reports);
		return "report_list";
	}
	
	//詳細表示
	@GetMapping("/reports/{id}")
	public String showReport(@PathVariable Integer id, Model model) {
		DailyReport report = dailyReportRepository.findById(id).orElse(null);
		model.addAttribute("report", report);
		return "report_detail";
	}
	
	//編集ページ表示
	@GetMapping("/reports/{id}/edit")
	public String editReport(@PathVariable Integer id, Model model) {
		DailyReport report = dailyReportRepository.findById(id).orElse(null);
		model.addAttribute("report", report);
		return "report_edit";
	}
	
	//編集を保存
	@PostMapping("/reports/{id}/edit")
	public String updateReport(@PathVariable Integer id, @ModelAttribute DailyReport updatedReport) {
		DailyReport report = dailyReportRepository.findById(id).orElse(null);
		if(report != null) {
			report.setTitle(updatedReport.getTitle());
			report.setContent(updatedReport.getContent());
			report.setImpression(updatedReport.getImpression());
			//report.setReportDate(updatedReport.getReportDate());
			report.setUpdatedAt(updatedReport.getUpdatedAt());
			dailyReportRepository.save(report);
		}
		return "redirect:/reports";
	}
	
	//削除
	@PostMapping("/reports/{id}/delete")
	public String deleteReport(@PathVariable Integer id) {
		dailyReportRepository.deleteById(id);
		return "redirect:/reports";
	}
	
}
