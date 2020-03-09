package com.tyss.ty_talenthunt_backend_springboot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.ty_talenthunt_backend_springboot.dto.RequirementInfo;
import com.tyss.ty_talenthunt_backend_springboot.dto.TalentHuntResponse;
import com.tyss.ty_talenthunt_backend_springboot.service.TalentHuntService;
@CrossOrigin(allowedHeaders = "*" ,origins = "*",allowCredentials = "true" )
@RestController
@ValidateOnExecution
@RequestMapping(path = "/requirement")
public class RequirementController {

	@Autowired
	private TalentHuntService service;


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		CustomDateEditor editor = new CustomDateEditor(format, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@PostMapping(path = "/add" , consumes = MediaType.APPLICATION_JSON_VALUE ,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addRequirement(@Valid @RequestBody RequirementInfo req){
         System.err.println(req);
		RequirementInfo requirement = service.addRequirement(req);
		TalentHuntResponse resp = new TalentHuntResponse();
		if(requirement != null) {
			resp.setError(false);
			resp.setData(requirement);;
			return new ResponseEntity<>(resp , HttpStatus.OK);
		}else {
			resp.setError(true);
			resp.setMessage("Requirement not added");
			return new ResponseEntity<>(resp , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(path = "/getall" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllRequirements(){
		List<RequirementInfo> requirements = service.getAllRequirements();
		TalentHuntResponse resp = new TalentHuntResponse();
		if(requirements != null) {
			resp.setError(false);
			resp.setData(requirements);
			return new ResponseEntity<>(resp , HttpStatus.OK);
		}else {
			resp.setError(true);
			resp.setMessage("Requirements not found");
			return new ResponseEntity<>(resp , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(path = "/getallactiverequirements" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllActiveRequirements(){
		List<RequirementInfo> requirements = service.getAllActiveRequirements();
		TalentHuntResponse resp = new TalentHuntResponse();
		if(requirements != null) {
			resp.setError(false);
			resp.setData(requirements);
			return new ResponseEntity<>(resp , HttpStatus.OK);
		}else {
			resp.setError(true);
			resp.setMessage("Requirements not found");
			return new ResponseEntity<>(resp , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping(path = "/deletebyid/{reqId}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteById(@PathVariable("reqId") String reqId){
		TalentHuntResponse resp = new TalentHuntResponse();
		RequirementInfo requirement = service.deleteRequirement(reqId);
		if(requirement != null) {
			resp.setError(false);
			resp.setData("Requirement with ID " + reqId + " and name " + requirement.getClientName() + " deleted");
			return new ResponseEntity<>(resp , HttpStatus.OK);
		}else {
			resp.setError(true);
			resp.setMessage("Requirement Not Deleted");
			return new ResponseEntity<>(resp , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(path = "/searchbyid/{reqId}")
	public ResponseEntity<Object> searchById(@PathVariable("reqId") String reqId){
		TalentHuntResponse resp = new TalentHuntResponse();
		RequirementInfo req = service.searchRequirementById(reqId);
		if(req != null) {
			resp.setError(false);
			resp.setData(req);
			return new ResponseEntity<>(resp , HttpStatus.OK);
		}else {
			resp.setError(true);
			resp.setMessage("requirement not found");
			return new ResponseEntity<>(resp , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping(path = "/update" , consumes = MediaType.APPLICATION_JSON_VALUE ,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateRequirement(@Valid @RequestBody RequirementInfo req){
		TalentHuntResponse resp = new TalentHuntResponse();
		RequirementInfo requirement = service.updateRequirement(req);
		if(requirement != null) {
			resp.setError(false);
			resp.setData("Requirement with ID " + req.getReqId() + " is updated");
			return new ResponseEntity<>(resp , HttpStatus.OK);
		}else {
			resp.setError(true);
			resp.setMessage("Requirement not Modified");
			return new ResponseEntity<>(resp , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



}
