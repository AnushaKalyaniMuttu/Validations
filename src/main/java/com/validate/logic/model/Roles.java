package com.validate.logic.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Roles {
    private String applicationIdentifier;
    private String roleId;
    private boolean isAutoGrantAccessRole;
    private boolean isDefaultAccessRole;
    private String description;
    private String roleName;
    private String roleType;
    private String module;
    private String productId;
    private String roleCategory;
    private String partyType;
    private Map<String, RolePermissions> permissions;
    private Map<String, Map<String, List<String>>> appPermissions;
}