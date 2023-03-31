package com.app.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InsurancePolicy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer policyId;
	
	private Integer policyNo;
	
	private String policyType;
	
	private Double coverageAmount;
	
	private Double premiumAmount;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "custId")
	private Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "insurancePolicy",fetch=FetchType.EAGER)
	private List<Claim> claims = new ArrayList<>();
}
