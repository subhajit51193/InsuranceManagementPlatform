package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.InsurancePolicy;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Integer>{


}
