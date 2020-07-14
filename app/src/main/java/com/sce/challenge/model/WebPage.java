package com.sce.challenge.model;

import java.util.ArrayList;
import java.util.List;

public class WebPage {
    private int id;
    private String url;
    private List<Permission> permissions = new ArrayList<>();

    public WebPage(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(Permission p) {
        permissions.add(p);
    }
}
