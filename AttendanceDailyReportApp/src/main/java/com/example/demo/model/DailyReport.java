package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="dailyreports")
@NoArgsConstructor
@AllArgsConstructor
public class DailyReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private User user;		//誰の日報か
	
	private LocalDate reportDate;		//いつの日報か
	private String title;		//タイトル
	
	@Column(length = 1000)
	private String content;		//報告事項
	
	@Column(length = 1000)
	private String impression;		//印象に残ったこと
	
	@Column(name="created_at")
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt = LocalDateTime.now();

}
