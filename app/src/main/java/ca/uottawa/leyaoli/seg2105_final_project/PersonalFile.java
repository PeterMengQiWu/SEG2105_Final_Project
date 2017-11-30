package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_file);
        firebaseAuth = FirebaseAuth.getInstance();
        userdatabase = FirebaseDatabase.getInstance().getReference();
        toolbar = (Toolbar) findViewById(R.id.personalProfileToolbar);
        bt = (Button) findViewById(R.id.btnSendMessage);
        setSupportActionBar(toolbar);

        // ===================================================================


        chatUser = getIntent().getStringExtra("user_id");

        userdatabase.child("Users").child(chatUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String user_name = dataSnapshot.child("name").getValue().toString();
                getSupportActionBar().setTitle(user_name);
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
