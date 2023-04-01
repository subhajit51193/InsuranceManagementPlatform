package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.InsurancePolicyException;
import com.app.model.InsurancePolicy;
import com.app.service.InsurancePolicyService;

@RestController
@RequestMapping("/api/policies")
public class InsurancePolicyController {

	@Autowired
	private InsurancePolicyService insurancePolicyService;
	
	@PostMapping("/create")
	public ResponseEntity<InsurancePolicy> createInsuranceHandler(@RequestBody InsurancePolicy insurancePolicy){
		InsurancePolicy ip = insurancePolicyService.createInsurance(insurancePolicy);
		return new ResponseEntity<InsurancePolicy>(ip,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{policyId}")
	public ResponseEntity<InsurancePolicy> updateInsuranceHandler(@RequestBody InsurancePolicy insurancePolicy,@PathVariable("policyId") Integer policyId) throws InsurancePolicyException{
		InsurancePolicy updated = insurancePolicyService.updateInsurance(insurancePolicy, policyId);
		return new ResponseEntity<InsurancePolicy>(updated,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{policyId}")
	public ResponseEntity<InsurancePolicy> deleteInsuranceHandler(@PathVariable("policyId") Integer policyId) throws InsurancePolicyException{
		InsurancePolicy ip = insurancePolicyService.deleteInsurance(policyId);
		return new ResponseEntity<InsurancePolicy>(ip,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllInsurancePolicies")
	public ResponseEntity<List<InsurancePolicy>> getAllInsurnaceHandler() throws InsurancePolicyException{
		List<InsurancePolicy> list = insurancePolicyService.getAllInsurances();
		return new ResponseEntity<List<InsurancePolicy>>(list,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getInsuranceById/{policyId}")
	public ResponseEntity<InsurancePolicy> getInsuranceByIdHandler(@PathVariable("policyId") Integer policyId) throws InsurancePolicyException{
		InsurancePolicy ip = insurancePolicyService.getInsuranceById(policyId);
		return new ResponseEntity<InsurancePolicy>(ip,HttpStatus.ACCEPTED);
	}
}
