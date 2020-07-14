package com.sce.challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runTrials();

    }

    void runTrials(){
        AccessControl accessControl = AccessControl.getInstance();
        Utils.printLog("getUser Count", accessControl.getUserCount()+" users", "i");
        Utils.printLog("getRole Count", accessControl.getRoleCount()+" roles", "i");
        Utils.printLog("getPermission Count", accessControl.getPermissionCount()+" permissions", "i");
        Utils.printLog("getWeb Count", accessControl.getWebPageCount()+" web pages", "i");

        //get permission per role
        accessControl.getRolePermissionCount(1); //0

        //assign role to user
        accessControl.assignRole(1,1);
        accessControl.assignRole(1,2);
        accessControl.assignRole(2,3);
        //get users count for a given role
        accessControl.getUserRoleCount(1); //2

        //assign permission to roles
        accessControl.assignRolePermission(1,1);
        accessControl.assignRolePermission(2,2);
        accessControl.assignRolePermission(1,3);
        //get role permission count
        accessControl.getRolePermissionCount(1); //2

        //assign web page permissions
        accessControl.assignWebPermission(1,2);
        accessControl.assignWebPermission(2,1);
        accessControl.assignWebPermission(1,3);
        accessControl.assignWebPermission(3,4);

        //check if user has access to web page
        accessControl.checkUserWebAccess(1,1); //false
        accessControl.checkUserWebAccess(2,3); //true
        accessControl.checkUserWebAccess(3,2); //false

        //add a new user
        accessControl.addUser("Adeola Adeleke");
        //get users count
        Utils.printLog("getUserCount", accessControl.getUserCount()+" users", "i");



    }
}