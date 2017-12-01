package ca.uottawa.leyaoli.seg2105_final_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChooseWorkerActivity extends AppCompatActivity {

    private RecyclerView workerList;
    private DatabaseReference usersDatabse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_worker);

        usersDatabse = FirebaseDatabase.getInstance().getReference("Users");
        workerList = (RecyclerView)findViewById(R.id.workerList);
        workerList.setLayoutManager(new LinearLayoutManager(this));
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
        workerList.setAdapter(adapter);
    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder {
        View view;

        public UsersViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        public void setName(String name) {
            TextView userNameView = view.findViewById(R.id.itemName);
            userNameView.setText(name);
        }

        public void setOther(String other) {
            TextView otherView = view.findViewById(R.id.itemDescription);
            otherView.setText(other);
        }

        public void setImage(String image) {
            ImageView ico = view.findViewById(R.id.icon);
            Uri link = null;
            if (image.equals("avatar")) {
                String tmpLink = "https://firebasestorage.googleapis.com/v0/b/seg2105finalproject.appspot.com/o/profile_image%2Favatar.png?alt=media&token=3b6c6778-c6c2-4009-b6c3-bbec9f4715ec";
                link = Uri.parse(tmpLink);
                ico.setImageURI(link);
            } else {
                link = Uri.parse(image);
                ico.setImageURI(link);
            }
        }
    }
}