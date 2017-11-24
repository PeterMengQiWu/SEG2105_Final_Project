package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by yuanzheng on 2017/11/21.
 */


public class PeopleAdapter extends ArrayAdapter<User> {


    public PeopleAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
