package com.app.service;

import java.util.List;

import com.app.exception.ClaimException;
import com.app.model.Claim;

public interface ClaimService {

	public Claim createClaim(Claim claim);
	
	public Claim updateClaim(Claim claim,Integer claimId)throws ClaimException;
	
	public Claim deleteClaim(Integer claimId)throws ClaimException;
	
	public List<Claim> getAllClaims()throws ClaimException;
	
	public Claim getClaimById(Integer claimId)throws ClaimException;
}
