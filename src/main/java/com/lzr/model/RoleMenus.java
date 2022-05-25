package com.lzr.model;

import java.util.List;

public class RoleMenus {
    private Integer roleId;
    private List<Menu> menus;

    public RoleMenus(){

    }

    public RoleMenus(Integer roleId, List<Menu> menus) {
        this.roleId = roleId;
        this.menus = menus;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "RoleMenus{" +
                "roleId=" + roleId +
                ", menus=" + menus +
                '}';
    }
}
