package com.lzr.model;

import java.util.List;

public class Menu {
    private Integer id;
    private String icon;
    private String menuname;
    private String url;
    private Integer fid;
    private Integer menuorder;
    private List<Menu> children;

    public Menu() {

    }

    public Menu(Integer id, String icon, String menuname, String url, Integer fid, Integer menuorder, List<Menu> children) {
        this.id = id;
        this.icon = icon;
        this.menuname = menuname;
        this.url = url;
        this.fid = fid;
        this.menuorder = menuorder;
        this.children = children;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getMenuorder() {
        return menuorder;
    }

    public void setMenuorder(Integer menuorder) {
        this.menuorder = menuorder;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", menuname='" + menuname + '\'' +
                ", url='" + url + '\'' +
                ", fid=" + fid +
                ", menuorder=" + menuorder +
                ", children=" + children +
                '}';
    }
}
