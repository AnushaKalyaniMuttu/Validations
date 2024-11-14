package com.validate.logic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.validate.logic.model.FiltersAndSearchResponseAttributesRequest;
import com.validate.logic.model.RolesSearchResponse;
import com.validate.logic.validator.FiltersValidator;
import com.validate.logic.validator.LogicalExpressionValidator;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class Controller {

	
	private final LogicalExpressionValidator logicalExpressionValidator;
	private final FiltersValidator filtersValidator;
	
	 @PostMapping(value = "roles/search")
	    public ResponseEntity<RolesSearchResponse> search(@RequestBody FiltersAndSearchResponseAttributesRequest request) {
		 logicalExpressionValidator.validateFilterSearchRequest(request);
		 filtersValidator.validateFilters(request);	
	        return new ResponseEntity<>(new RolesSearchResponse(),HttpStatus.OK);
	    }

	  
	@GetMapping("/health")
	public String isHealthy() {
		return "Healthy....";
	}
}
