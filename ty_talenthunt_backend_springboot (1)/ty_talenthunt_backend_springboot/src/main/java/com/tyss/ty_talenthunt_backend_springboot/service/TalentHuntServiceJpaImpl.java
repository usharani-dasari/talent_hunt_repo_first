package com.tyss.ty_talenthunt_backend_springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.ty_talenthunt_backend_springboot.dto.CandidateInfo;
import com.tyss.ty_talenthunt_backend_springboot.dto.RequirementIdGenerator;
import com.tyss.ty_talenthunt_backend_springboot.dto.RequirementInfo;
import com.tyss.ty_talenthunt_backend_springboot.exception.GlobalCustomException;
import com.tyss.ty_talenthunt_backend_springboot.repo.CandidateInfoRepo;
import com.tyss.ty_talenthunt_backend_springboot.repo.RequirementInfoRepo;


@Service
public class TalentHuntServiceJpaImpl implements TalentHuntService {
	

	
	@Autowired
	private RequirementInfoRepo requirementRepo;
	@Autowired
	private CandidateInfoRepo candidateRepo;
	@Autowired
	private RequirementIdGenerator generator;
	
	@Override
	public RequirementInfo addRequirement(RequirementInfo req) {
		if(req == null) throw new GlobalCustomException("Requirement Information cannot be null");
		if(req.getStartDate() != null && req.getEndDate() != null) {
			if(req.getStartDate().isAfter(req.getEndDate())) throw new GlobalCustomException("start date cannot be greater than end Date");
		}else {
			throw new GlobalCustomException("Start Date and End Date are Mandatory");
		}
		if(req.getPositions() != null) {
			if(req.getPositions() <= 0) throw new GlobalCustomException("postions cannot be Zero or lesser than that");
		}else {
			throw new GlobalCustomException("positions are mandatory");
		}
		if(req.getSalaryFrom() != null && req.getSalaryTo() != null) {
			if(req.getSalaryFrom() > req.getSalaryTo()) throw new GlobalCustomException("starting salary cannot be greater than ending salary");
		}else {
			throw new GlobalCustomException("salary From and salaryTo are Mandatory");
		}
	   System.err.println( generator.generate(req));
	    req.setStatus("Active");
	    requirementRepo.save(req);
	    return req;
		
	}

	@Override
	public List<RequirementInfo> getAllRequirements() {
		return requirementRepo.findAllRequirements();
	}

	@Override
	public RequirementInfo deleteRequirement(String reqId) {
		requirementRepo.deleteRequirement(reqId);
		return requirementRepo.searchById(reqId);
	}

	@Override
	public RequirementInfo searchRequirementById(String reqId) {
		return requirementRepo.searchById(reqId);
	}

	@Override
	public RequirementInfo updateRequirement(RequirementInfo req) {
		RequirementInfo requirement = requirementRepo.findById(req.getReqId()).get();
		requirement.getSkills().clear();
		requirement.setSkills(req.getSkills());
		requirementRepo.updateRequirement(req.getClientName() , req.getClientshortName() , req.getDescription() , req.getEndDate() ,
				req.getStartDate() , req.getLocation() , req.getPositions() , req.getQualification() , req.getSalaryFrom() ,
				req.getSalaryTo() , req.getTitle() , req.getYearOfExp() , req.getStatus() , req.getReqId());
		return requirement;
	}
	
   @Override
	public List<RequirementInfo> getAllActiveRequirements() {
		return requirementRepo.getActiveRequirements();
	}

   
   
	@Override
	public CandidateInfo addCandidate(CandidateInfo candidateInfo) {
		candidateInfo.setStatus("Active");
		RequirementInfo requirement = requirementRepo.findById(candidateInfo.getClient()).get();
		requirement.getCandidatesUnderRequirement().add(candidateInfo);
		
		requirementRepo.save(requirement);
		return candidateInfo;
	}

	@Override
	public List<CandidateInfo> getAllCandidates() {
		return candidateRepo.findAll();
	}

	@Override
	public List<CandidateInfo> getAllActiveCandidates() {
		return candidateRepo.getActiveCandidates();
	}

	@Override
	public CandidateInfo getCandidateById(Integer candidateId) {
		return candidateRepo.findById(candidateId).get();
	}

	@Override
	public CandidateInfo deleteCandidate(Integer candidateId) {
		candidateRepo.deleteCandidate(candidateId);
		return candidateRepo.findById(candidateId).get();
	}

	@Override
	public CandidateInfo getCandidateByEmail(String mailId) {
		return candidateRepo.searchByMailId(mailId);
	}

	@Override
	public CandidateInfo updateCandidate(CandidateInfo candidateInfo) {
		CandidateInfo candidateInfoNew = candidateRepo.findById(candidateInfo.getCandidateId()).get();
		candidateInfoNew.getSkills().clear();
		candidateInfoNew.setSkills(candidateInfo.getSkills());
		candidateRepo.updateCandidate(candidateInfo.getCandidateName() , candidateInfo.getContactNumber() ,
				candidateInfo.getClient() , candidateInfo.getCtc() , candidateInfo.getEctc() ,
				candidateInfo.getCurrentLoc() , candidateInfo.getCurrOrganisation() , candidateInfo.getDeploymentDate() ,
				candidateInfo.getJoiningDate() , candidateInfo.getNoticePeriod() , candidateInfo.getRecruiterName() , 
				candidateInfo.getRelExperience() , candidateInfo.getTotalExperience() ,
				candidateInfo.getStatus() , candidateInfo.getCandidateId() , candidateInfo.getPrefferedLocation() );
		return candidateInfo;
	}

	@Override
	public RequirementInfo getCandidatesUnderRequirement(String reqId) {
		return requirementRepo.getCandidatesUnderRequirement(reqId);
	}

	
	
	

}
