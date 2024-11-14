package com.validate.logic.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.validate.logic.exception.FiltersValidationException;
import com.validate.logic.model.Filter;
import com.validate.logic.model.FiltersAndSearchResponseAttributesRequest;

@Component	
public class FiltersValidator {
	
	private static final String MODULE="module";
	private static final String ROLE_ID="roleId";
	private static final String APPLICATION_IDENTIFIER="applicationIdentifier";
	private static final String APIAAP="APIAAP";
	private static final String PRODUCT_ID="productId";
	
	public void validateFilters(FiltersAndSearchResponseAttributesRequest request) {
		validatefilterByModule(request);
		validatefilterByRoleId(request);
		validatefilterByApplicationIdentifier(request);
		validateFiltersByProductId(request);
	}

	
	
	private void validatefilterByModule(FiltersAndSearchResponseAttributesRequest request) {
		System.out.println("Validating modules for size greater than 5");
		request.getFilters().stream()
        .filter(f -> MODULE.equals(f.getPropertyName())) 
        .filter(f -> f.getValues().size() > 5) 
        .findFirst()
        .ifPresent(filter -> {
            throw new FiltersValidationException("Filter modules are supported only upto 5 values");
        });		
		 Optional<Filter> moduleFilter = request.getFilters().stream()
	                .filter(filter ->MODULE.equals(filter.getPropertyName()))
	                .findFirst();
	        Optional<Filter> applicationIdentifierFilter = request.getFilters().stream()
	                .filter(filter -> APPLICATION_IDENTIFIER.equals(filter.getPropertyName()))
	                .findFirst();
	        if (moduleFilter.isPresent() && !applicationIdentifierFilter.isPresent()) {
	            throw new FiltersValidationException("ApplicationIdentifier is required when module is provided.");
	        }
	}
	
	
	
	private void validatefilterByRoleId(FiltersAndSearchResponseAttributesRequest request) {
		System.out.println("Validating RoleId Values supported upto 1");
		if(request.getFilters().size()==1 && request.getFilters().get(0).getPropertyName().equals(ROLE_ID)) {
			request.getFilters().stream()
            .filter(f->f.getValues().size()> 1)
            .findFirst()
            .ifPresent(f->{
                throw new FiltersValidationException("Filter roleID are supported only upto 1 values with no other filters combined");
            });
		}
	}

	
	

	private void validatefilterByApplicationIdentifier(FiltersAndSearchResponseAttributesRequest request) {
		System.out.println("Validating Application Identifier");
		request.getFilters().stream()
        .filter(filter -> APPLICATION_IDENTIFIER.equals(filter.getPropertyName())) 
        .filter(filter -> filter.getValues().size() > 5) 
        .findFirst()
        .ifPresent(filter -> {
            throw new FiltersValidationException("Filter applicationIdentifier are supported only upto 5 values");
        });
	}
	

	
	private void validateFiltersByProductId(FiltersAndSearchResponseAttributesRequest request) {
		System.out.println("Validating ProductId...");
		  Optional<Filter> productIdsFilter = request.getFilters().stream()
	                .filter(filter ->PRODUCT_ID.equals(filter.getPropertyName()))
	                .findFirst();

	        Optional<Filter> applicationIdentifierFilter = request.getFilters().stream()
	                .filter(filter -> APPLICATION_IDENTIFIER.equals(filter.getPropertyName()))
	                .findFirst();

	        if (productIdsFilter.isPresent()) {
	            if (!applicationIdentifierFilter.isPresent() || 
	                !applicationIdentifierFilter.get().getValues().contains(APIAAP)) {
	                throw new FiltersValidationException("ApplicationIdentifier must be 'APIAAP' when productIds are provided.");
	            }
	        }
	}
}
