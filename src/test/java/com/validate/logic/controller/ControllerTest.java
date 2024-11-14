package com.validate.logic.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.validate.logic.model.FiltersAndSearchResponseAttributesRequest;
import com.validate.logic.model.RolesSearchResponse;
import com.validate.logic.validator.FiltersValidator;
import com.validate.logic.validator.LogicalExpressionValidator;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {

	
	@InjectMocks
	Controller controller;
	
	@Mock
	LogicalExpressionValidator logicalExpressionValidator;
	
	@Mock
	FiltersValidator filtersValidator;
	
	@Test
	public void testSearch() {
		FiltersAndSearchResponseAttributesRequest request=mock(FiltersAndSearchResponseAttributesRequest.class);
	
		ResponseEntity<RolesSearchResponse> response=	controller.search(request);
		Assertions.assertEquals(200, response.getStatusCodeValue());
	}
	
	
	
	@Test
	public void testIsHealthy() {
		assertEquals("Healthy....",controller.isHealthy());
	}
}
