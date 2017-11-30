package ca.uottawa.leyaoli.seg2105_final_project;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by ${YuanZhengHu} on 2017-11-30.
 */

public class CurrentUserName {

    private static DatabaseReference userdatabase;
    private static FirebaseAuth firebaseAuth;

    private static String returnUserName;

    public static String getCurrentUserName() {
        firebaseAuth = FirebaseAuth.getInstance();
        userdatabase = FirebaseDatabase.getInstance().getReference();
        String userID = firebaseAuth.getCurrentUser().getUid();


        userdatabase.child("Users").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                returnUserName = dataSnapshot.child("name").getValue().toString();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return userID;
    }
}
