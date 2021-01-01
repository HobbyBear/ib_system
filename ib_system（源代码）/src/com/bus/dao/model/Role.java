package com.bus.dao.model;

import java.io.Serializable;

public class Role implements Serializable {

    /**
     * ROLE
     */
    private static final long serialVersionUID = 1L;

    private String roleCode;

    private String roleName;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Role(String roleCode, String roleName) {
        super();
        this.roleCode = roleCode;
        this.roleName = roleName;
    }

    public Role() {
        super();
    }
}
