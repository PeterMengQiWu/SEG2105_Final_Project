package ca.uottawa.leyaoli.seg2105_final_project;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
        //String userID = firebaseAuth.getCurrentUser().getUid();
      //  String name = firebaseAuth.getCurrentUser().getDisplayName();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        returnUserName = currentUser.getEmail();
        String[] a = returnUserName.split("@");
        return a[0];
    }

}
