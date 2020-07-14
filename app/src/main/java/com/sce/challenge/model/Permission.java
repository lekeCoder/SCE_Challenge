package com.sce.challenge.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Permission {
    private int id;
    private List<WebPage> webPageList = new ArrayList<>();
    private Role role;

    public Permission(int id) {
        this.id = id;
    }

    public boolean canAccess(int userID, int webID){
        Role userRole = null;
        if(role != null){
            for (User user : role.getUsers()) {
                if(user.getId() == userID){
                    userRole = user.getRole();
                    break;
                }
            }
            if(userRole != null){
                //user has this permission
                for (WebPage webPage : webPageList) {
                    if(webPage.getId() == webID){
                        return true;
                    }
                }
            }
            else{
                return false;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<WebPage> getWebPageList() {
        return webPageList;
    }

    public void setWebPageList(List<WebPage> webPageList) {
        this.webPageList = webPageList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Permission)) return false;
        Permission that = (Permission) o;
        return getId() == that.getId() &&
                getRole()==that.getRole();
    }

    @Override
    public int hashCode() {
        return 31* getId()* getRole().getName().length();
    }

    public void addWebPage(WebPage webPage) {
        webPageList.add(webPage);
    }
}
