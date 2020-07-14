package com.sce.challenge.mgt;

import com.sce.challenge.model.User;

import org.junit.After;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserManagementTest {

    UserManagement userManagement;
    @BeforeAll
    public void setUp() throws Exception {
        userManagement = UserManagement.getInstance();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Order(1)
    public void addUser() {
        int userCountBefore = userManagement.getUserCount();
        userManagement.addUser("Adeola Adeleke");
        int userCountAfter = userManagement.getUserCount();
        System.out.println("userCountBefore: "+userCountBefore+" userCountAfter: "+userCountAfter);
        assertNotEquals(userCountBefore, userCountAfter);
        assertEquals(++userCountBefore, userCountAfter);
    }

    @Test
    @Order(2)
    public void getUsers() {
        assertEquals(4, userManagement.getUsers().size());
    }

    @Test
    @Order(3)
    public void getUser() {
        User user = userManagement.getUser(4);
        assertEquals("Adeola Adeleke", user.getUName());
    }

    @Test
    @Order(4)
    public void updateUser() {
        User u1 = userManagement.getUser(1);
        u1.setName("Ani Olohun");
        userManagement.updateUser(u1);
        User uafter = userManagement.getUser(1);
        assertEquals(uafter,u1);
    }

    @Test
    @Order(5)
    public void removeUser() {
        userManagement.removeUser(1);
        assertNull(userManagement.getUser(1));
    }

    @Test
    @Order(6)
    public void getUserCount() {
        assertEquals(3, userManagement.getUserCount());
    }
}