package com.tyss.ty_talenthunt_backend_springboot.dto;



import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TalentHuntResponse {
	
	private boolean error;
	private String message;
	private Object data;
	
}
