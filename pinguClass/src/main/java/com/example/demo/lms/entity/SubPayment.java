package com.example.demo.lms.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sub_payment")
@Getter
@Setter
public class SubPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sub_pay_id")
	private Integer subPayId;
	
	@Column(name = "payment_type")
	private String paymentType; //결제 방식
	@Column(name = "create_date")
	private LocalDateTime createDate; //구매일자
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "subsc_id")
	private Subscribe subscribe;
}
