package com.sce.challenge.mgt;


import com.sce.challenge.model.Permission;
import com.sce.challenge.model.WebPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

;

public class PermissionManagement {
    private static PermissionManagement permissionManagement = null;
    private Map<Integer, Permission> permissions;
    private static int cnt = 0;

    private PermissionManagement() {
        permissions = new HashMap<>();
        createPermissions();
    }

    public static PermissionManagement getInstance(){
        if(permissionManagement == null){
            permissionManagement = new PermissionManagement();
        }
        return permissionManagement;
    }

    public int addPermission(){
        int newId = ++cnt;
        permissions.put(newId,new Permission(newId));
        return newId;
        
    }

    public List<Permission> getPermissions() {
        return new ArrayList<>(permissions.values());
    }

    private void createPermissions(){
        Permission perm1 = new Permission(1);
        Permission perm2 = new Permission(2);
        Permission perm3 = new Permission(3);
        permissions.put(1,perm1);
        permissions.put(2,perm2);
        permissions.put(3,perm3);
        cnt+=3;

    }

    public Permission getPermission(int permId){
        for (Permission perm : permissions.values()) {
            if(perm.getId() == permId) return perm;
        }
        return null;
    }

    public boolean assignWebPage(int permid, WebPage webPage){
        Permission p = getPermission(permid);
        if(p != null){
            p.addWebPage(webPage);
            return true;
        }
        return false;
    }

    public boolean assignWebPages(int permid, List<WebPage> webPages){
        Permission p = getPermission(permid);
        if(p != null){
            p.setWebPageList(webPages);
            return true;
        }
        return false;
    }


    public void updatePermissions(Permission r) {
        permissions.put(r.getId(),r);
    }

    public int getCount() {
        return permissions.size();
    }
}
