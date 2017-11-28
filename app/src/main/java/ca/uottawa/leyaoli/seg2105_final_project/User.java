package ca.uottawa.leyaoli.seg2105_final_project;

/**
 * Created by ${YuanZhengHu} on 2017-11-24.
 */

public class User {

    private String name;
    private String email;

    public User(){

    }

    public User(String userName, String someOtherThings) {

        this.name = userName;
        this.email = someOtherThings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

