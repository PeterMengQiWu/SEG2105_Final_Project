package ca.uottawa.leyaoli.seg2105_final_project;

/**
 * Created by ${YuanZhengHu} on 2017-11-24.
 */

public class User {

    private String name;
    private String email;
    private  String image ;

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

    public User(String name, String email, String image) {
        this.name = name;
        this.email = email;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public User(){

    }
}

