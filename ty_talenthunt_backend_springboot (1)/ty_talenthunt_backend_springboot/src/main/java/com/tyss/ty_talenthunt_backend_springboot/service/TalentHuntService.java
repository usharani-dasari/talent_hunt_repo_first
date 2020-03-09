package com.tyss.ty_talenthunt_backend_springboot.service;

import java.util.List;

import com.tyss.ty_talenthunt_backend_springboot.dto.CandidateInfo;
import com.tyss.ty_talenthunt_backend_springboot.dto.RequirementInfo;

public interface TalentHuntService {
	public RequirementInfo addRequirement(RequirementInfo req); 
	public List<RequirementInfo> getAllRequirements();
	public RequirementInfo deleteRequirement(String reqId);
	public RequirementInfo searchRequirementById(String reqId);
	public RequirementInfo updateRequirement(RequirementInfo req);
	public List<RequirementInfo> getAllActiveRequirements();
	
	
	public CandidateInfo addCandidate(CandidateInfo candidateInfo) ;
	public List<CandidateInfo> getAllCandidates();
	public List<CandidateInfo> getAllActiveCandidates();
	public CandidateInfo getCandidateById(Integer cId);
	public CandidateInfo deleteCandidate(Integer cId);
	public CandidateInfo getCandidateByEmail(String email);
	public CandidateInfo updateCandidate(CandidateInfo candidateInfo);
	
	public RequirementInfo getCandidatesUnderRequirement(String clientName);

}
