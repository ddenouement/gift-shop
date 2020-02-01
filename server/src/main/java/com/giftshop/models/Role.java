package com.giftshop.models;

import java.io.Serializable;

public class Role implements Serializable {
    private int roleId;
    private String roleName;

    public Role(int roleId, String name) {
        this.roleId = roleId;
        this.roleName = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
