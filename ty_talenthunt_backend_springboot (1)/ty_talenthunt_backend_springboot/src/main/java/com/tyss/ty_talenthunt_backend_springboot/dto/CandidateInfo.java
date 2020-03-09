package com.tyss.ty_talenthunt_backend_springboot.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Entity
@Table(name = "candidate_info")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateInfo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer candidateId;
	
	@NotNull(message = "candidate name cannot be null")
	@Size(min = 1, max = 30, message = "Candidate name should be in between 1 and 30 characters")
	@Pattern(regexp = "^[a-zA-Z_ ]*$",message = "candidate name should contain only characters")
	@Column(name = "candidate_name")
	private String candidateName;
	
	@ElementCollection
	@CollectionTable(name = "candidate_skills")
	@Column
	private List<@NotBlank(message = "skill cannot be blank") String> skills;
	
	@Column(name = "recruiter_name")
	@Size(min = 1, max = 30, message = "Recruiter name should be in between 1 and 30 characters")
	@Pattern(regexp = "^[a-zA-Z_ ]*$",message = "Recruiter name should contain only characters")
	private String recruiterName;
	
	@Column(name = "ctc")
	private double ctc;
	
	@Column(name = "ectc")
	private double ectc;
	
	
	@Column
	private Long contactNumber;
	
	@Column(name = "mail_id" , unique = true)
	@Email(message = "enter a valid email")
	private String mailId;
	
	@Column(name = "tot_exp")
	private double totalExperience;
	
	@Column(name = "rel_exp")
	private double relExperience;
	
	@Column(name = "curr_organisation")
	private String currOrganisation;
	
	@Column(name = "notice_period")
	private double noticePeriod;
	
	@Column(name = "current_location")
	private String currentLoc;
	
	@Column
	private String prefferedLocation;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "joining_date")
	private LocalDate joiningDate; 
	
	@Column(name = "deployment_date ")
	private   LocalDate deploymentDate;
	
	@Column
	@Transient
    private String client;
	


     
}
	


