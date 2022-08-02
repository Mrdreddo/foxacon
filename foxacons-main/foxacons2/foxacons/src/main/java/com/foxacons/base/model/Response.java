package com.foxacons.base.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foxacons.base.entity.ContactDetails;
import com.foxacons.base.entity.ProjectDetails;

import lombok.Data;
@Data
public class Response {
	
    @JsonProperty 
	private int statuscode;
    @JsonProperty
    private String msg;
    @JsonProperty
    
    private String token;
    private List<ProjectDetails> details;
    private List<ContactDetails> contactDetails;
   
}
 