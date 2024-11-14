package com.validate.logic.model;

import java.util.List;


import lombok.Data;

@Data
public class FiltersAndSearchResponseAttributesRequest {

    private String logicalExpression;
    private List<Filter> filters;
    private List<String> responseAttributes;

    
}