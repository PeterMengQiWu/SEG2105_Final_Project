package ca.uottawa.leyaoli.seg2105_final_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseIndexRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.security.PublicKey;

public class UserActivity extends AppCompatActivity {

    private RecyclerView userlist ;
    private DatabaseReference usersDatabse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //getSupportActionBar().setTitle("ALL USER");
        //getSupportActionBar().setHomeButtonEnabled(true);
        usersDatabse = FirebaseDatabase.getInstance().getReference("Users");
        userlist = (RecyclerView)findViewById(R.id.user_list);
        //userlist.setHasFixedSize(true);
        userlist.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<User,UsersViewHolder> adapter = new FirebaseRecyclerAdapter<User, UsersViewHolder>(
                User.class,R.layout.people_layout,UsersViewHolder.class,usersDatabse
        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, User model, int position) {
                viewHolder.setName (model.getName());
                viewHolder.setOther(model.getEmail());
            }
        };
           // userlist.setAdapter(adapter);
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
