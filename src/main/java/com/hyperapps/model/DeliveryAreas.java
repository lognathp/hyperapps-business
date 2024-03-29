package com.hyperapps.model;

import org.springframework.beans.factory.annotation.Qualifier;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class DeliveryAreas {

	  	@Qualifier("name")
	    @JsonProperty("name")
	    public String name;
	    @Qualifier("lat")
	    @JsonProperty("lat")
	    public String lat;
	    public String lng;
   
}
