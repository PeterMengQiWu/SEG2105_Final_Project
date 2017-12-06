package ca.uottawa.leyaoli.seg2105_final_project;

/**
 * Created by ${YuanZhengHu} on 2017-11-24.
 */

public class User {

    private String name;
    private String email;
    private  String image ;

    public User(String name, String email, String image, String points) {
        this.name = name;
        this.email = email;
        this.image = image;
        this.points = points;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    private String points ;



    public User(){

    }
}

