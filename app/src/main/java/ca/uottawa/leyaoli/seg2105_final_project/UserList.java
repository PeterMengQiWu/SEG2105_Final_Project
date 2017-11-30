package ca.uottawa.leyaoli.seg2105_final_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;

/**
 * Created by ${YuanZhengHu} on 2017-11-24.
 */

public class UserList {

    private static UserList instance = null ;
    private ArrayList<User> userlist;
    public UserList() {

        String[] userName = {"Dad","Mom","Son","Daughter","Baby"};
        userlist =new ArrayList<>();

        for (int i = 0;     i< userName.length ; i++){

            User newUser = new User(userName[i], "Yo");

            userlist.add(newUser);
        }


    }


    public static UserList getInstance() {
        return instance;
    }

    public static void setInstance(UserList instance) {
        UserList.instance = instance;
    }

    public ArrayList<User> getUserlist() {
        return userlist;
    }

    public void setUserlist(ArrayList<User> userlist) {
        this.userlist = userlist;
    }

    /**
     * Created by tyson on 2017-11-29.
     */


    }

