package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.InsurancePolicyException;
import com.app.model.InsurancePolicy;
import com.app.repository.InsurancePolicyRepository;

@Service
public class InsurancePolicyServiceImpl implements InsurancePolicyService{

	@Autowired
	private InsurancePolicyRepository insurancePolicyRepository;
	
	@Override
	public InsurancePolicy createInsurance(InsurancePolicy insurancePolicy) throws InsurancePolicyException {
		
//		return insurancePolicyRepository.save(insurancePolicy);
		InsurancePolicy ip = insurancePolicyRepository.save(insurancePolicy);
		if (ip != null) {
			return ip;
		}
		else {
			throw new InsurancePolicyException();
		}
		
	}

	@Override
	public InsurancePolicy updateInsurance(InsurancePolicy insurancePolicy, Integer policyId)
			throws InsurancePolicyException {
		
		Optional<InsurancePolicy> opt = insurancePolicyRepository.findById(policyId);
		if (opt.isEmpty()) {
			throw new InsurancePolicyException("Not found");
		}
		else {
			InsurancePolicy updatedIp = opt.get();
			updatedIp.setCoverageAmount(insurancePolicy.getCoverageAmount());
			updatedIp.setPolicyNo(insurancePolicy.getPolicyNo());
			updatedIp.setPolicyType(insurancePolicy.getPolicyType());
			updatedIp.setStartDate(insurancePolicy.getStartDate());
			updatedIp.setEndDate(insurancePolicy.getEndDate());
			updatedIp.setPremiumAmount(insurancePolicy.getPremiumAmount());
			return insurancePolicyRepository.save(updatedIp);
		}
	}

	@Override
	public InsurancePolicy deleteInsurance(Integer policyId) throws InsurancePolicyException {
		
		Optional<InsurancePolicy> opt = insurancePolicyRepository.findById(policyId);
		if (opt.isEmpty()) {
			throw new InsurancePolicyException("Not found");
		}
		else {
			InsurancePolicy ip = opt.get();
			insurancePolicyRepository.delete(ip);
			return ip;
		}
	}

	@Override
	public List<InsurancePolicy> getAllInsurances() throws InsurancePolicyException {
		
		List<InsurancePolicy> list = insurancePolicyRepository.findAll();
		if (list.isEmpty()) {
			throw new InsurancePolicyException("List is empty");
		}
		else {
			return list;
		}
	}

	@Override
	public InsurancePolicy getInsuranceById(Integer policyId) throws InsurancePolicyException {
		
		if (policyId ==  null) {
			throw new InsurancePolicyException("Not found");
		}
		else {
			Optional<InsurancePolicy> opt = insurancePolicyRepository.findById(policyId);
			if (opt == null) {
				throw new InsurancePolicyException("Not found");
			}
			else {
				if (opt.isEmpty()) {
					throw new InsurancePolicyException("Not found");
				}
				else {
					return opt.get();
				}
			}
			
		}
		
	}

}
