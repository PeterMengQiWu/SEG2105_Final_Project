package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by yuanzheng on 2017/11/21.
 */



public class PersonalFile extends AppCompatActivity {
    private String chatUser;
    private DatabaseReference userdatabase;
    private FirebaseAuth firebaseAuth;
    private Toolbar toolbar;
    private Button bt ;
    private ImageView avatar ;
    private TextView email;
//    private  DatabaseReference userdatabase1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_file);
        firebaseAuth = FirebaseAuth.getInstance();
        userdatabase = FirebaseDatabase.getInstance().getReference();
        toolbar = (Toolbar) findViewById(R.id.personalProfileToolbar);
        bt = (Button) findViewById(R.id.btnSendMessage);
        setSupportActionBar(toolbar);
        avatar = (ImageView) findViewById(R.id.personalProfileAvatar);
        email = (TextView) findViewById(R.id.personalProfileText);
        chatUser = getIntent().getStringExtra("user_id");
        //userdatabase1 = FirebaseDatabase.getInstance().getReference().child("Users").child(chatUser);



        // ===================================================================




        userdatabase.child("Users").child(chatUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String user_name = dataSnapshot.child("name").getValue().toString();
                String emailAddress = dataSnapshot.child("email").getValue().toString();
                getSupportActionBar().setTitle(user_name);
                email.setText(emailAddress);

        }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(PersonalFile.this, ChatActivity.class);
                intent.putExtra("user_id",chatUser);
                startActivity(intent);

            }
        });

    }
}
