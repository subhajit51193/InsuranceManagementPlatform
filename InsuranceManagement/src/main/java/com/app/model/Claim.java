package com.app.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer claimId;
	
	private Integer claimNo;
	
	private String description;
	
	private LocalDate claimDate;
	
	private Boolean claimStatus=false;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "policyId")
	private InsurancePolicy insurancePolicy;
}
