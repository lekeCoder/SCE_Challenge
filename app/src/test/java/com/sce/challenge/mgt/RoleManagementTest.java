package com.sce.challenge.mgt;

import com.sce.challenge.model.Role;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.Assert.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RoleManagementTest {
    RoleManagement roleManagement;
    @BeforeAll
    public void setUp(){
        roleManagement = RoleManagement.getInstance();
    }
    @Test
    public void getInstance() {
        assertNotNull(roleManagement);
    }

    @Test
    public void addRole() {
        //Role Count
        assertEquals(3, roleManagement.getRoleCount());
        roleManagement.addRole("Programmer");
        roleManagement.addRole("Designer");
        assertEquals(5, roleManagement.getRoleCount());
    }

    @Test
    public void getRoles() {
        assertEquals(6, roleManagement.getRoleCount());
    }

    @Test
    public void getRole() {
        int newRoleId = roleManagement.addRole("Accountant");
        assertEquals(newRoleId, roleManagement.getRole(newRoleId).getId());
    }

    @Test
    public void getUserRoleCount() {
        assertEquals(0, roleManagement.getUserRoleCount(1));
    }

    @Test
    public void getRoleCount() {
        assertEquals(6, roleManagement.getRoleCount());
    }

    @Test
    public void updateRole() {
        Role r = roleManagement.getRole(3);
        r.setName("Chief Technologist");
        roleManagement.updateRole(r);
        assertSame(r, roleManagement.getRole(3));
    }

    @Test
    public void getRolePermissionCount() {
        assertEquals(0, roleManagement.getRolePermissionCount(1));
    }
}