package com.validate.logic.model;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Filter {

    private String type;
    private String propertyName;
    private List<String> values;
}