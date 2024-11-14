package com.validate.logic.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.validate.logic.exception.FiltersValidationException;
import com.validate.logic.model.Filter;
import com.validate.logic.model.FiltersAndSearchResponseAttributesRequest;

@ExtendWith(MockitoExtension.class)
public class FiltersValidatorTest {

	@InjectMocks
	FiltersValidator filterValidator;

	@Test
	public void testValidateFilters_modulesValueTest() {
		FiltersAndSearchResponseAttributesRequest request = mockRequest();

		assertThrows(FiltersValidationException.class, () -> {
			filterValidator.validateFilters(request); // Should throw the exception
		}, "Filter modules are supported only upto 5 values");

	}

	@Test
	public void testValidateFilters_roleIdTest() {
		FiltersAndSearchResponseAttributesRequest request = mockRequestRoleIdTest();

		assertThrows(FiltersValidationException.class, () -> {
			filterValidator.validateFilters(request); // Should throw the exception
		}, "Filter roleID are supported only upto 1 values with no other filters combined");

	}


	@Test
	public void testValidateFilters_productIdTestIsPresent() {
		FiltersAndSearchResponseAttributesRequest request = mockRequestProductId();
		filterValidator.validateFilters(request); // Should throw the exception
		}

	@Test
	public void testValidateFilters_productIdTestIsNotPresent() {
		FiltersAndSearchResponseAttributesRequest request = mockRequestProductIdNotPresent();
	assertThrows(FiltersValidationException.class, () -> {
		filterValidator.validateFilters(request); // Should throw the exception
	}, "ApplicationIdentifier must be  \'APIAAP\'when productIds are provided");
	}

	

	private FiltersAndSearchResponseAttributesRequest mockRequestProductId() {
		FiltersAndSearchResponseAttributesRequest req = new FiltersAndSearchResponseAttributesRequest();
		List<Filter> f = getFilters();
		Filter productId = new Filter();
		productId.setPropertyName("productId");
		productId.setValues(List.of("dgfhf"));
		f.add(productId);
		Filter applicationIdentifier = new Filter();
		applicationIdentifier.setPropertyName("applicationIdentifier");
		applicationIdentifier.setValues(List.of("APIAAP"));
		f.add(applicationIdentifier);
		req.setFilters(f);
		return req;
	}

	private FiltersAndSearchResponseAttributesRequest mockRequestProductIdNotPresent() {
		FiltersAndSearchResponseAttributesRequest req = new FiltersAndSearchResponseAttributesRequest();
		List<Filter> f = getFilters();
		Filter productId = new Filter();
		productId.setPropertyName("productId");
		productId.setValues(List.of("bksjs"));
		f.add(productId);
		Filter applicationIdentifier = new Filter();
//		applicationIdentifier.setPropertyName("applicationIdentifier");
//		applicationIdentifier.setValues(List.of("APIAAkdsP"));
		f.add(applicationIdentifier);
		req.setFilters(f);
		return req;
	}

	private FiltersAndSearchResponseAttributesRequest mockRequestRoleIdTest() {
		FiltersAndSearchResponseAttributesRequest req = new FiltersAndSearchResponseAttributesRequest();
		List<Filter> f = getFilters();
		f.add(getModuleFilterRoleId());
		req.setFilters(f);
		return req;
	}

	@Test
	public void testValidateFilters_roleIdSizeLessThanOne() {
		FiltersAndSearchResponseAttributesRequest request = mockRoleIdLessThanOne();

		filterValidator.validateFilters(request); // Should throw the exception
	}

	private FiltersAndSearchResponseAttributesRequest mockRoleIdLessThanOne() {
		FiltersAndSearchResponseAttributesRequest req = new FiltersAndSearchResponseAttributesRequest();
		List<Filter> f = getFilters();
		f.add(getModuleFilterRoleIdForLessThanOne());
		req.setFilters(f);
		return req;
	}

	private Filter getModuleFilterRoleIdForLessThanOne() {
		Filter f = new Filter();
		f.setPropertyName("roleId");
		f.setValues(List.of("GD"));
		return f;
	}

	private Filter getModuleFilterRoleId() {
		Filter f = new Filter();
		f.setPropertyName("roleId");
		f.setValues(List.of("SDF", "hbf"));
		return f;
	}

	@Test
	public void testValidateFilters_modulesValueTestSizeLessThanFive() {
		FiltersAndSearchResponseAttributesRequest request = mockRequestForLessThanFive();

		assertThrows(FiltersValidationException.class, () -> {
			filterValidator.validateFilters(request); // Should throw the exception
		}, "ApplicationIdentifier is required when module is provided.");

	}

	@Test
	public void testValidateFilters_ApplicationIdentifierNoError() {
		FiltersAndSearchResponseAttributesRequest request = new FiltersAndSearchResponseAttributesRequest();
		List<Filter> f = getFilters();
		Filter module = new Filter();
		module.setPropertyName("module");
		module.setValues(List.of("AP", "AO"));
		f.add(module);
		Filter applicationIdentifer = new Filter();
		applicationIdentifer.setPropertyName("applicationIdentifier");
		applicationIdentifer.setValues(List.of("AP"));
		f.add(applicationIdentifer);
		request.setFilters(f);

		filterValidator.validateFilters(request);

	}
	@Test
	public void testValidateFilters_ApplicationIdentifierIsnotPresent() {
		FiltersAndSearchResponseAttributesRequest request = new FiltersAndSearchResponseAttributesRequest();
		List<Filter> f = getFilters();
		Filter productId = new Filter();
		productId.setPropertyName("productId");
		productId.setValues(List.of("AP", "AO"));
		f.add(productId);
		Filter applicationIdentifer = new Filter();
		applicationIdentifer.setPropertyName("applicationIdentifier");
		applicationIdentifer.setValues(List.of("APIAAP"));
		f.add(applicationIdentifer);
		request.setFilters(f);

		
			filterValidator.validateFilters(request); // Should throw the exception
		}

	
	@Test
	public void testValidateFilters_ApplicationIdentifier() {
		FiltersAndSearchResponseAttributesRequest request = new FiltersAndSearchResponseAttributesRequest();
		List<Filter> f = getFilters();
		Filter module = new Filter();
		module.setPropertyName("module");
		module.setValues(List.of("AP", "AO"));
		f.add(module);
		Filter applicationIdentifer = new Filter();
		applicationIdentifer.setPropertyName("applicationIdentifier");
		applicationIdentifer.setValues(List.of("AP","dg","edg","eg","rg","rghr"));
		f.add(applicationIdentifer);
		request.setFilters(f);

		assertThrows(FiltersValidationException.class, () -> {
			filterValidator.validateFilters(request); // Should throw the exception
		}, " Filter ApplicationIdentifier are supported only upto 5 values.");

	}

	

	private FiltersAndSearchResponseAttributesRequest mockRequestForLessThanFive() {
		FiltersAndSearchResponseAttributesRequest req = new FiltersAndSearchResponseAttributesRequest();
		List<Filter> f = getFilters();
		f.add(getModuleFilterLessThanFive());
		req.setFilters(f);
		return req;
	}

	private Filter getModuleFilterLessThanFive() {
		Filter f = new Filter();
		f.setPropertyName("module");
		f.setType("StringEquals");
		f.setValues(List.of("AB", "BC", "CD", "DE", "EF"));
		return f;
	}

	private FiltersAndSearchResponseAttributesRequest mockRequest() {
		FiltersAndSearchResponseAttributesRequest req = new FiltersAndSearchResponseAttributesRequest();
		List<Filter> f = getFilters();
		f.add(getModuleFilter());
		req.setFilters(f);
		return req;
	}

	private List<Filter> getFilters() {
		List<Filter> filters = new ArrayList<>();
		return filters;
	}

	private Filter getModuleFilter() {
		Filter f = new Filter();
		f.setPropertyName("module");
		f.setType("StringEquals");
		f.setValues(List.of("AB", "BC", "CD", "DE", "EF", "PF"));
		return f;
	}

}
