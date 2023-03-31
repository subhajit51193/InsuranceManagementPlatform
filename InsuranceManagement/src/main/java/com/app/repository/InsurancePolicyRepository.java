package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.InsurancePolicy;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Integer>{

}
