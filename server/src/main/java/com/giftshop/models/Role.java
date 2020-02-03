package com.giftshop.models;

import java.io.Serializable;

public class Role implements Serializable {
    private Integer roleId;
    private String roleName;

    public Role(Integer roleId, String name) {
        this.roleId = roleId;
        this.roleName = name;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
