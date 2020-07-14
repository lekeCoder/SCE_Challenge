package com.sce.challenge;

import com.sce.challenge.model.Permission;
import com.sce.challenge.model.Role;
import com.sce.challenge.model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccessControlTest {
    AccessControl accessControl;
    @BeforeAll
    public void setUp() {
        accessControl = AccessControl.getInstance();
    }

    @AfterAll
    public void tearDown() {

    }

    @Test
    @Order(1)
    public void assignRole() {
        User user = accessControl.getUser(1);
        assertNotNull(user);
        assertNull(user.getRole());
        accessControl.assignRole(1,1);
        assertNotNull(accessControl.getUser(1).getRole());
    }

    @Test
    @Order(2)
    public void removeRole() {
        accessControl.removeRole(1,1);
        User user = accessControl.getUser(1);
        assertNull(user.getRole());

    }

    @Test
    @Order(3)
    public void addUser() {
        int newUserId = accessControl.addUser("Adeleke Adeola");
        assertEquals(4,newUserId);
    }

    @Test
    @Order(4)
    public void removeUser() {
        int totalCount = accessControl.getUserCount();
        accessControl.removeUser(2);
        int newTotalCount = accessControl.getUserCount();
        assertTrue(totalCount > newTotalCount);
    }

    @Test
    @Order(5)
    public void assignWebPermission() {
        int perm1 = accessControl.getWebPermissions(1).size();
        accessControl.assignWebPermission(1,1);
        int perm2 = accessControl.getWebPermissions(1).size();
        assertTrue(perm2 > perm1);
    }

    @Test
    @Order(6)
    public void removeWebPermission() {
        List<Permission> perm1 = accessControl.getWebPermissions(1);
        accessControl.removeWebPermission(1,1);
        List<Permission> perm2 = accessControl.getWebPermissions(1);
        assertFalse(perm2.size() > perm1.size());
    }

    @Test
    @Order(7)
    public void assignRolePermission() {
        int total = accessControl.getRolePermissionCount(1);
        accessControl.assignRolePermission(1,1);
        accessControl.assignRolePermission(1,2);
        int total2 = accessControl.getRolePermissionCount(1);
        assertEquals(total+2, total2);
    }

    @Test
    @Order(16)
    public void removeRolePermission() {
        int total = accessControl.getRolePermissionCount(1);
        accessControl.removeRolePermission(1,1);
        accessControl.removeRolePermission(1,2);
        int total2 = accessControl.getRolePermissionCount(1);
        assertEquals(total-2, total2);
    }

    @Test
    @Order(9)
    public void getUserRoleCount() {
        assertEquals(0, accessControl.getUserRoleCount(2));
    }

    @Test
    @Order(10)
    public void getRoleCount() {
        accessControl.addRole("Technical Manager");
        int newCount = accessControl.getRoleCount();
        assertEquals(4, newCount);
    }

    @Test
    @Order(11)
    public void getRolePermissionCount() {
        int count = accessControl.getRolePermissionCount(1);
        assertEquals(2, count);
    }

    @Test
    @Order(12)
    public void getWebPageCount() {
        int count = accessControl.getWebPageCount();
        assertEquals(5,count);
    }

    @Test
    @Order(13)
    public void getUserCount() {
        int count = accessControl.getUserCount();
        assertEquals(3, count);
    }

    @Test
    @Order(14)
    public void checkUserWebAccess() {
        boolean res1 = accessControl.checkUserWebAccess(1,1);
        assertFalse(res1);
        boolean res2 = accessControl.checkUserWebAccess(2,1);
        assertFalse(res2);

    }

    @Test
    @Order(15)
    public void getPermissionCount() {
        assertEquals(3, accessControl.getPermissionCount());
    }
}