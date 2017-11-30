package ca.uottawa.leyaoli.seg2105_final_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountSetting extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;
    private CircleImageView imageAv ;
    private TextView disname;
    private TextView numChore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);


        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String curren_uid = currentUser.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(curren_uid);
        disname = (TextView) findViewById(R.id.SettingName);
        imageAv = (CircleImageView) findViewById(R.id.settingAvatar);
        numChore = (TextView) findViewById(R.id.somethingElse);
        // ===========================================================

/*
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                String image = dataSnapshot.child("image").getValue().toString();
                String thumb_image = dataSnapshot.child("thumb_image").getValue().toString();
               // disname.setText(name);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/
    }
}
