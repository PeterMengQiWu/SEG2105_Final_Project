package ca.uottawa.leyaoli.seg2105_final_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by yuanzheng on 2017/11/21.
 */

public class Tab3 extends Fragment {
    private RecyclerView userlist ;
    private DatabaseReference usersDatabse;
    private FirebaseAuth auth;
    private String userid;
    private View view;
    public Tab3(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_tab3,container,false);
       // ListView listview = view.findViewById(R.id.listv1);
        //UserList somelist = new UserList();
        //PeopleAdapter adapter = new PeopleAdapter(getActivity(), somelist.getUserlist());
        //listview.setAdapter(adapter);


        userlist = (RecyclerView)view.findViewById(R.id.recycle1);
        auth = FirebaseAuth.getInstance();
        userid = auth.getCurrentUser().getUid();
        usersDatabse = FirebaseDatabase.getInstance().getReference("Users");

        //userlist.setHasFixedSize(true);
        userlist.setLayoutManager(new LinearLayoutManager(getContext()));



        return view;
    }

    public void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter<User,UserActivity.UsersViewHolder> adapter = new FirebaseRecyclerAdapter<User, UserActivity.UsersViewHolder>(
                User.class,R.layout.people_layout,UserActivity.UsersViewHolder.class,usersDatabse

        ) {
            @Override
            protected void populateViewHolder(final UserActivity.UsersViewHolder viewHolder, User model, int position) {
                viewHolder.setName (model.getName());
                viewHolder.setOther(model.getEmail());



                usersDatabse.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CharSequence options [] = new CharSequence[]{"Open Profile","Chat"};
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Select an Opiton");
                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                                builder.show();
                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }



        };



       userlist.setAdapter(adapter);





    }




    public static class UsersViewHolder extends RecyclerView.ViewHolder{

        View mView;



        public UsersViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setName (String name ){

            TextView userNameView = mView.findViewById(R.id.itemName);
            userNameView.setText(name);
        }

        public void setOther (String other){

            TextView otherView = mView.findViewById(R.id.itemDescription);
            otherView.setText(other);
        }
    }
}
