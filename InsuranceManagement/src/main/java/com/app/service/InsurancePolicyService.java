package com.app.service;

import java.util.List;

import com.app.exception.InsurancePolicyException;
import com.app.model.InsurancePolicy;

public interface InsurancePolicyService {

	public InsurancePolicy createInsurance(InsurancePolicy insurancePolicy)throws InsurancePolicyException;
	
	public InsurancePolicy updateInsurance(InsurancePolicy insurancePolicy,Integer policyId)throws InsurancePolicyException;
	
	public InsurancePolicy deleteInsurance(Integer policyId)throws InsurancePolicyException;
	
	public List<InsurancePolicy> getAllInsurances()throws InsurancePolicyException;
	
	public InsurancePolicy getInsuranceById(Integer policyId)throws InsurancePolicyException;
}
