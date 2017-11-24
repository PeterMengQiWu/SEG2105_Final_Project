package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by yuanzheng on 2017/11/21.
 */

public class Tab3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3,container,false);

        //ArrayList<String> userName = MainActivity.getList();
        ListView listview = view.findViewById(R.id.listv1);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.people_layout,userName);
       // listview.setAdapter(adapter);

        UserList somelist = UserList.getInstance();
        PeopleAdapter adapter = new PeopleAdapter(this, somelist.getUserlist());

        listview.setAdapter(adapter);



        return view;
    }
}
