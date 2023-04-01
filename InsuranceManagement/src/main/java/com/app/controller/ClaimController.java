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

import com.app.exception.ClaimException;
import com.app.model.Claim;
import com.app.service.ClaimService;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

	@Autowired
	private ClaimService claimService;
	
	@PostMapping("/create")
	public ResponseEntity<Claim> createClaimHandler(@RequestBody Claim claim){
		Claim newClaim = claimService.createClaim(claim);
		return new ResponseEntity<Claim>(newClaim,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{claimId}")
	public ResponseEntity<Claim> updateClaimHandler(@RequestBody Claim claim, @PathVariable("claimId") Integer claimId) throws ClaimException{
		Claim updated = claimService.updateClaim(claim, claimId);
		return new ResponseEntity<Claim>(updated,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{claimId}")
	public ResponseEntity<Claim> deleteClaimHandler(@PathVariable("claimId") Integer claimId) throws ClaimException{
		Claim claim = claimService.deleteClaim(claimId);
		return new ResponseEntity<Claim>(claim,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllClaims")
	public ResponseEntity<List<Claim>> getAllClaimsHandler() throws ClaimException{
		List<Claim> list = claimService.getAllClaims();
		return new ResponseEntity<List<Claim>>(list,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getClaimById/{claimId}")
	public ResponseEntity<Claim> getClaimByIdHandler(@PathVariable("claimId") Integer claimId) throws ClaimException{
		Claim claim = claimService.getClaimById(claimId);
		return new ResponseEntity<Claim>(claim,HttpStatus.ACCEPTED);
	}
}
