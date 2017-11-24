package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanzheng on 2017/11/21.
 */


public class PeopleAdapter extends ArrayAdapter<User> {
        private final Context context;
        private final ArrayList<User> nameList;
        

        public PeopleAdapter (Tab3 context1 , ArrayList<User> list){
            super(context1,R.layout.people_layout,list);

            this.context = context1;
            nameList = list;
        }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            User currentUser = nameList.get(position);

        LayoutInflater infalter = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = infalter.inflate(R.layout.people_layout, parent,false);
        TextView userName = (TextView) rowView.findViewById(R.id.itemName);
        TextView otherthings = (TextView) rowView.findViewById(R.id.itemDescription);
        ImageView avatar = (ImageView) rowView.findViewById(R.id.icon);


        userName.setText(currentUser.getName());
        otherthings.setText(currentUser.getOtherthings());

        avatar.setImageResource(R.drawable.people);

        return rowView;
    }
}
