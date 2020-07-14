package com.sce.challenge.mgt;



import com.sce.challenge.Utils;
import com.sce.challenge.model.Role;
import com.sce.challenge.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
;

public class RoleManagement {
    private static RoleManagement roleManagement = null;
    private Map<Integer, Role> roles;
    private static int cnt = 0;

    private RoleManagement() {
        roles = new HashMap<>();
        createRoles();
    }

    public static RoleManagement getInstance(){
        if(roleManagement == null){
            roleManagement = new RoleManagement();
        }
        return roleManagement;
    }

    public int addRole(String role_name){
        int newId = ++cnt;
        roles.put(newId,new Role(newId, role_name));
        return newId;

    }

    public List<Role> getRoles() {
        return new ArrayList<>(roles.values());
    }

    private void createRoles(){
        Role role1 = new Role(1,"Administrator");
        Role role2 = new Role(2,"Manager");
        Role role3 = new Role(3,"Supervisor");
        roles.put(1,role1);
        roles.put(2,role2);
        roles.put(3,role3);
        cnt += 3;

    }

    public Role getRole(int roleId){
        for (Role role : roles.values()) {
            if(role.getId() == roleId) return role;
        }
        return null;
    }

    public int getUserRoleCount(int roleId){
        Role r = getRole(roleId);
        if(r != null){
            return r.getUsers().size();
        }
        Utils.printLog("getUserRoleCount()","Invalid roleId: "+roleId,"e");
        return 0;
    }

    public int getRoleCount(){
        int num = roles.size();
        Utils.printLog("getRoleCount()","total roles: "+num,"i");
        return num;
    }


    public void updateRole(Role r) {
        roles.put(r.getId(),r);
    }


    public int getRolePermissionCount(int roleId) {
        Role r = getRole(roleId);
        if(r != null){
            return r.getPermissions().size();
        }
        Utils.printLog("getRolePermissionCount","Invalid roleId: "+roleId,"e");
        return 0;
    }


}
