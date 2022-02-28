package com.ocio.backend17.dto;

public class PermissionsDto {
    private String permissions;

    public PermissionsDto(String permissions) {
        this.permissions = permissions;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
