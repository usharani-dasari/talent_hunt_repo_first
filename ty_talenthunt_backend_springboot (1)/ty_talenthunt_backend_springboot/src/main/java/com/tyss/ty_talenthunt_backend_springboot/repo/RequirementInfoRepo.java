package com.tyss.ty_talenthunt_backend_springboot.repo;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tyss.ty_talenthunt_backend_springboot.dto.CandidateInfo;
import com.tyss.ty_talenthunt_backend_springboot.dto.RequirementInfo;

public interface RequirementInfoRepo extends JpaRepository<RequirementInfo, String>{
	   @Query("from RequirementInfo where reqId=:reqId")
	   public RequirementInfo searchById(String reqId);
	   @Query("from RequirementInfo ORDER BY startDate")
	   public List<RequirementInfo> findAllRequirements();
	   
	   @Transactional
	   @Modifying
	   @Query("update RequirementInfo set status='Deleted' where reqId=:reqId")
	   public void deleteRequirement(String reqId);
	   @Transactional
	   @Modifying
	   @Query("update RequirementInfo set clientName=:clientName, clientshortName=:clientshortName,description=:description,"
				+ "endDate=:endDate,startDate=:startDate,location=:location,positions=:positions,qualification=:qualification,"
				+ "salaryFrom=:salaryFrom,salaryTo=:salaryTo,title=:title,yearOfExp=:yearOfExp,status=:status where reqId=:reqId")
	   public void updateRequirement(String clientName , String clientshortName , String description , LocalDate endDate ,
			   LocalDate startDate , String location , Integer positions , String qualification , Long salaryFrom ,
			   Long salaryTo , String title , int yearOfExp , String status , String reqId);
	   @Query("from RequirementInfo where status='Active'")
	   public List<RequirementInfo> getActiveRequirements();
	   
	   
	   @Query("from RequirementInfo where reqId=:reqId")
	   public RequirementInfo getCandidatesUnderRequirement(String reqId);
	   
}
