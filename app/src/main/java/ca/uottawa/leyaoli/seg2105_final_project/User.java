package ca.uottawa.leyaoli.seg2105_final_project;

/**
 * Created by ${YuanZhengHu} on 2017-11-24.
 */

public class User {

    private String name = "No name";
    private String otherthings = "nmb";

    public User(String userName, String someOtherThings) {

        this.name = userName;
        this.otherthings = someOtherThings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtherthings() {
        return otherthings;
    }

    public void setOtherthings(String otherthings) {
        this.otherthings = otherthings;
    }
}
