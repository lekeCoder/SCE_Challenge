package com.sce.challenge.mgt;

import com.sce.challenge.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManagement {
    private static UserManagement userManagement = null;
    private Map<Integer, User> users;
    private static int cnt =0;

    private UserManagement() {
        users = new HashMap<>();
        createUsers();
    }

    public static UserManagement getInstance(){
        if(userManagement == null){
            userManagement = new UserManagement();
        }
        return userManagement;
    }

    public int addUser(String user){
        int newId = ++cnt;
        users.put(newId,new User(newId, user));
        return newId;

    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    private void createUsers(){
        User user1 = new User(1, "Joe Duncan");
        User user2 = new User(2, "John Willow");
        User user3 = new User(3, "Jim Grey");
        users.put(1,user1);
        users.put(2,user2);
        users.put(3,user3);
        cnt += 3;
    }

    public User getUser(int userId){
        for (User user : users.values()) {
            if(user.getId() == userId){
                return user;
            }
        }
        return null;
    }

    public void updateUser(User user) {
        users.put(user.getId(), user);
    }

    public User removeUser(int userId) {
        return users.remove(userId);
    }

    public int getUserCount() {
        return users.size();
    }
}
