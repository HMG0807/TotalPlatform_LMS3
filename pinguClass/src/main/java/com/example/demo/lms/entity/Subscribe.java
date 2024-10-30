package com.example.demo.lms.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subscribe")
@Getter
@Setter
public class Subscribe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subsc_id")
	private Integer subscId;
	
	private String type;
	private Integer price;
	
	@OneToMany(mappedBy = "subscribe")
  private List<SubPayment> subPayments;
}

