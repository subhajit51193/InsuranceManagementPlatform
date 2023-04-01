package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.ClaimException;
import com.app.model.Claim;
import com.app.repository.ClaimRepository;

@Service
public class ClaimServiceImpl implements ClaimService{

	@Autowired
	private ClaimRepository claimRepository;
	
	@Override
	public Claim createClaim(Claim claim) {
	
		return claimRepository.save(claim);
	}

	@Override
	public Claim updateClaim(Claim claim, Integer claimId) throws ClaimException {
		
		Optional<Claim> opt = claimRepository.findById(claimId);
		if (opt.isEmpty()) {
			throw new ClaimException("Not found");
		}
		else {
			Claim updatedClaim = opt.get();
			updatedClaim.setClaimNo(claim.getClaimNo());
			updatedClaim.setClaimStatus(claim.getClaimStatus());
			updatedClaim.setDescription(claim.getDescription());
			updatedClaim.setClaimDate(claim.getClaimDate());
			return claimRepository.save(updatedClaim);
		}
	}

	@Override
	public Claim deleteClaim(Integer claimId) throws ClaimException {
		
		Optional<Claim> opt = claimRepository.findById(claimId);
		if (opt.isEmpty()) {
			throw new ClaimException("Not found");
		}
		else {
			Claim claim = opt.get();
			claimRepository.delete(claim);
			return claim;
			
		}
	}

	@Override
	public List<Claim> getAllClaims() throws ClaimException {
		
		List<Claim> list = claimRepository.findAll();
		if (list.isEmpty()) {
			throw new ClaimException("Not found");
		}
		else {
			return list;
		}
	}

	@Override
	public Claim getClaimById(Integer claimId) throws ClaimException {
		
		Optional<Claim> opt = claimRepository.findById(claimId);
		if (opt.isEmpty()) {
			throw new ClaimException("Not found");
		}
		else {
			return opt.get();
			
		}
	}

	
}
