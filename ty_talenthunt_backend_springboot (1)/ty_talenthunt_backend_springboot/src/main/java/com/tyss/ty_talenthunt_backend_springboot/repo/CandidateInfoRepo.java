package com.tyss.ty_talenthunt_backend_springboot.repo;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tyss.ty_talenthunt_backend_springboot.dto.CandidateInfo;


public interface CandidateInfoRepo extends JpaRepository<CandidateInfo, Integer> {

	@Query("from CandidateInfo where status='Active'")
	public List<CandidateInfo> getActiveCandidates();

	@Query("from CandidateInfo where mailId=:mailId")
	public CandidateInfo searchByMailId(String mailId);

	@Transactional
	@Modifying
	@Query("update CandidateInfo set status='Deleted' where candidateId=:candidateId")
	public void deleteCandidate(Integer candidateId);
	
	
	@Transactional
	@Modifying
	@Query("update CandidateInfo set candidateName=:candidateName, contactNumber=:contactNumber,client=:client,"
			+ "ctc=:ctc,ectc=:ectc,currentLoc=:currentLoc,currOrganisation=:currOrganisation,deploymentDate=:deploymentDate,"
			+ "joiningDate=:joiningDate,noticePeriod=:noticePeriod,recruiterName=:recruiterName,relExperience=:relExperience,"
			+ "totalExperience=:totalExperience,status=:status,prefferedLocation=:prefferedLocation where candidateId=:candidateId")
	public void updateCandidate(String candidateName , Long contactNumber , String client , double ctc ,
			double ectc , String currentLoc , String currOrganisation , LocalDate deploymentDate , LocalDate joiningDate,
			double noticePeriod , String recruiterName , double relExperience , double totalExperience ,String status,
			Integer candidateId,String prefferedLocation);
	



}
