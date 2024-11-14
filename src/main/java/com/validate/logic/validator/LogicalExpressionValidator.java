package com.validate.logic.validator;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.validate.logic.constants.ValidationConstants;
import com.validate.logic.exception.LogicalExpressionMisMatchException;
import com.validate.logic.model.FiltersAndSearchResponseAttributesRequest;

@Component
public class LogicalExpressionValidator {

	private static final String PLACEHOLDER_REGEX = "\\$\\d+";

	public void validateFilterSearchRequest(FiltersAndSearchResponseAttributesRequest request) {
		validateLogicalExpression(request);
	}

	private void validateLogicalExpression(FiltersAndSearchResponseAttributesRequest request)
			throws LogicalExpressionMisMatchException {
		if (request.getLogicalExpression().trim().isEmpty()) {
			throw new LogicalExpressionMisMatchException("Logical Expression can't be Null");
		}

		if (!validatePatternOfLogicalExpression(request.getLogicalExpression())) {
			throw new LogicalExpressionMisMatchException("Logical Expression can't be repeated");
		}

	}

	private boolean validatePatternOfLogicalExpression(String logicalExpression) {
		logicalExpression = insertAndIfNotExistsAndPlaceHolderCheck(logicalExpression);
		System.out.println(logicalExpression);
		return duplicatePlaceHolderCheck(logicalExpression);
	}

	private boolean duplicatePlaceHolderCheck(String logicalExpression) {
		String[] variables = logicalExpression.split(ValidationConstants.AND);
		Set<String> uniqueVariables = new HashSet<>();
	
		for (String variable : variables) {
			String trimmed = variable.trim();
			if (!uniqueVariables.add(trimmed)) {
				return false;
			}
		}
		return true;
	}

	
	private String insertAndIfNotExistsAndPlaceHolderCheck(String logicalExpression) {
		if (!logicalExpression.contains(ValidationConstants.AND) && logicalExpression.matches(".*\\$\\d+.*")) {
			String[] filters = logicalExpression.split("\\s+");
	
			StringBuilder transformedExpression = new StringBuilder();
			for (int i = 0; i < filters.length; i++) {
				String filter = filters[i].trim();
				if (filter.matches(PLACEHOLDER_REGEX)) {
					if (i > 0) {
						transformedExpression.append(" & ");
					}
					transformedExpression.append(filter);
				} 
			}
			logicalExpression = transformedExpression.toString();
		}else {
			throw new LogicalExpressionMisMatchException("Logical Expression is not a valid expression");
		}
		return logicalExpression;
	}
}
