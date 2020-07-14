package com.sce.challenge;

import com.sce.challenge.mgt.PermissionManagement;
import com.sce.challenge.mgt.RoleManagement;
import com.sce.challenge.mgt.UserManagement;
import com.sce.challenge.mgt.WebPageManagement;
import com.sce.challenge.model.Permission;
import com.sce.challenge.model.Role;
import com.sce.challenge.model.User;
import com.sce.challenge.model.WebPage;

import java.util.List;

public class AccessControl {
    private static AccessControl instance = null;
    private UserManagement userManagement;
    private RoleManagement roleManagement;
    private PermissionManagement permissionManagement;
    private WebPageManagement webPageManagement;
    private AccessControl() {
        userManagement = UserManagement.getInstance();
        roleManagement = RoleManagement.getInstance();
        permissionManagement = PermissionManagement.getInstance();
        webPageManagement = WebPageManagement.getInstance();
    }

    public static AccessControl getInstance(){
        if(instance == null){
            instance = new AccessControl();
        }
        return instance;
    }

    public void assignRole(int roleid, int userid){
        Role r = roleManagement.getRole(roleid);
        if(r == null) {
            Utils.printLog("assignRole()", "AccessControl: ","assignRole() role id: "+roleid+" is not found. Can't assign role");
            return;
        }
        User user = userManagement.getUser(userid);
        if(user != null){
            //update user
            user.setRole(r);
            userManagement.updateUser(user);
            //update role
            r.addUser(user);
            roleManagement.updateRole(r);
            Utils.printLog("assignRole()", "User "+user.getUName()+" added successfully","i");
            return;
        }
        Utils.printLog("removeRole()", " user id: "+userid+" is not found. Can't assign role","e");
    }

    public void removeRole(int roleid, int userid){
        Role r = roleManagement.getRole(roleid);
        if(r == null) {
            Utils.printLog("removeRole()", " role id: "+roleid+" is not found. Can't assign role","e");
            return;
        }

        User user = userManagement.getUser(userid);
        if(user != null){
            user.setRole(null);
            userManagement.updateUser(user);
            //update role
            r.addUser(user);
            roleManagement.updateRole(r);
            Utils.printLog("removeRole()", "User "+user.getUName()+" removed successfully","i");
            return;
        }
        Utils.printLog("removeRole()", " user id: "+userid+" is not found. Can't assign role","e");

    }

    public int addUser(String name){
        int newId = userManagement.addUser(name);
        Utils.printLog("addUser()","new user created with id: "+newId,"i");
        return newId;
    }
    public void removeUser(int userId){
        User u = userManagement.removeUser(userId);
        if(u != null)
            Utils.printLog("removeUser()","user removed with id: "+u.getId(),"i");
        else{
            Utils.printLog("removeUser()","Invalid user id: "+userId,"e");
        }
    }

    public void assignWebPermission(int permIt, int webId){
        WebPage web = webPageManagement.getWebPage(webId);
        if(web == null){
            Utils.printLog("assignWebPermission()", " web id: "+webId+" is not found. Can't assign role","e");
            return;
        }
        Permission p = permissionManagement.getPermission(permIt);
        if(p != null) {
            p.addWebPage(web);
            web.addPermission(p);
            permissionManagement.updatePermissions(p);
            webPageManagement.updateWebPage(web);
            Utils.printLog("assignWebPermission", "web page added to permission with new count: " + p.getWebPageList().size(), "i");
        }
        else{
            Utils.printLog("assignWebPermission()","Invalid permission id: "+permIt,"e");
        }
    }

    public void removeWebPermission(int permIt, int webId){
        WebPage web = webPageManagement.getWebPage(webId);
        if(web == null){
            Utils.printLog("removeWebPermission()", " web id: "+webId+" is not found. Can't assign role","e");
            return;
        }
        Permission p = permissionManagement.getPermission(permIt);
        if(p != null) {
            p.addWebPage(web);
            web.addPermission(p);
            permissionManagement.updatePermissions(p);
            webPageManagement.updateWebPage(web);
            Utils.printLog("removeWebPermission()", "web page remove to permission with new count: " + p.getWebPageList().size(), "i");
        }
        else{
            Utils.printLog("removeWebPermission()","Invalid permission id: "+permIt,"e");
        }
    }


    public void assignRolePermission(int roleId, int permId){
        Role r = roleManagement.getRole(roleId);
        if(r == null) {
            Utils.printLog("assignRolePermission()", " role id: "+roleId+" is not found. Can't assign role","e");
            return;
        }
        Permission p = permissionManagement.getPermission(permId);
        if(p != null) {
            r.addPermission(p);
            p.setRole(r);
            roleManagement.updateRole(r);
            permissionManagement.updatePermissions(p);
            Utils.printLog("assignRolePermission()", "role added to permission with new role permission count: " + r.getPermissions().size(), "i");
        }
        else{
            Utils.printLog("assignRolePermission() ","Invalid permission id: "+permId,"e");
        }
    }

    public void removeRolePermission(int roleId, int permId){
        Role r = roleManagement.getRole(roleId);
        if(r == null) {
            Utils.printLog("removeRolePermission()", " role id: "+roleId+" is not found. Can't assign role","e");
            return;
        }
        Permission p = permissionManagement.getPermission(permId);
        if(p != null) {
            r.getPermissions().remove(p);
            p.setRole(null);
            roleManagement.updateRole(r);
            permissionManagement.updatePermissions(p);
            Utils.printLog("removeRolePermission()", "role remove to permission with new role permission count: " + r.getPermissions().size(), "i");
        }
        else{
            Utils.printLog("removeRolePermission() ","Invalid permission id: "+permId,"e");
        }
    }

    public int getUserRoleCount(int roleId){
        int cnt = roleManagement.getUserRoleCount(roleId);
        Utils.printLog("getUserRoleCount ", cnt+" users count for role: "+getRole(roleId), "i");
        return cnt;
    }

    public int getRoleCount(){
        return roleManagement.getRoleCount();
    }

    public int getRolePermissionCount(int roleId){
        int cnt = roleManagement.getRolePermissionCount(roleId);
        Utils.printLog("getRolePermissionCount ", cnt+" permissions count for role: "+getRole(roleId), "i");
        return cnt;
    }

    private String getRole(int roleId) {
        Role r = roleManagement.getRole(roleId);
        if(r != null){
            return r.getName();
        }
        return null;
    }

    public int getWebPageCount(){
        return webPageManagement.getWebCount();
    }

    public int getUserCount(){
        return userManagement.getUserCount();
    }

    public boolean checkUserWebAccess(int userId, int webId){
        boolean result = false;
        User u = userManagement.getUser(userId);
        WebPage webPage = webPageManagement.getWebPage(webId);
        if(u != null && webPage != null){
           Role userRole =  u.getRole();
           if(userRole != null){
               for (Permission permission : userRole.getPermissions()) {
                   if(permission.canAccess(userId,webId)){
                       result = true;
                       break;
                   }
               }
           }
        }
        String accessStatus = "";
        if(result) {
            accessStatus = "Access granted to " + getUserName(userId) + " [" + getWeb(webId) + "]";
        }
        else {
            accessStatus = "Access denied to " + getUserName(userId) + " [" + getWeb(webId) + "]";
        }
        Utils.printLog("checkUserWebAccess()", accessStatus, "i");

        return result;
    }

    String getWeb(int webId) {
        WebPage webPage = webPageManagement.getWebPage(webId);
        if(webPage != null){
            return webPage.getUrl();
        }
        return  null;
    }

    String getUserName(int userId) {
        User u = userManagement.getUser(userId);
        if(u != null){
            return  u.getUName();
        }
        return null;
    }

    User getUser(int userId){
        return userManagement.getUser(userId);
    }


    public int getPermissionCount() {
        return permissionManagement.getCount();
    }

    public List<Permission> getWebPermissions(int webId) {
        return webPageManagement.getPermissions(webId);
    }

    public void addRole(String role) {
        roleManagement.addRole(role);
    }
}
