package ca.uottawa.leyaoli.seg2105_final_project;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChatActivity extends AppCompatActivity {
    private ImageButton chatSendBt;   // Send Button
    private EditText chatMessage;  //  Chat TextView
    private String chatUser;    // Person Talk to
    private Toolbar chatToolbar; //  Chat Tool Bar
    private DatabaseReference userdatabase;  // Database reference
    private FirebaseAuth firebaseAuth; // auth
    private String userID; // Current User ID
    //private DatabaseReference userdatabasetest;
    //==============================================================
    private final List<Messgae> messgaeList = new ArrayList<>();   // List of Message Class
    private LinearLayoutManager linearLayoutManager ;  // LayOut
    private MessageAdapter messageAdapter; //Adapter
    private RecyclerView messageView ; // Message View

// =============================================================================
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
// =================================================================================FireBase Varibaless
        userdatabase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        //userdatabasetest = FirebaseDatabase.getInstance().getReference("KKKK");


// ====================================================== Get component in UI
        chatToolbar = (Toolbar)findViewById(R.id.chat_tool_bar);
        setSupportActionBar(chatToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        chatSendBt = (ImageButton) findViewById(R.id.sendButton );
        chatMessage = (EditText) findViewById(R.id.chatTextview);
        messageView = (RecyclerView) findViewById(R.id.chatView);
// =================================================================================== adapter setup
        messageAdapter = new MessageAdapter(messgaeList);

        linearLayoutManager = new LinearLayoutManager(this);
//=============================================================================Test
       /* userdatabasetest.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userdatabasetest.child("hey").setValue("1");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });  */



//=====================================================================================Set up Chat Title
       chatUser = getIntent().getStringExtra("user_id"); // get the Other user's ID
        userdatabase.child("Users").child(chatUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    String chat_user_name = dataSnapshot.child("name").getValue().toString();
                     getSupportActionBar().setTitle(chat_user_name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//===================================================================================add  CHAT to database
        userdatabase.child("Chat").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (!dataSnapshot.hasChild(chatUser)){
                    Map chatAddmap = new HashMap();
                    chatAddmap.put("seen",false);
                    chatAddmap.put("timestamp", ServerValue.TIMESTAMP);

                    Map userMap =new HashMap();
                    userMap.put("Chat/" + userID +"/" + chatUser,chatAddmap);
                    userMap.put( "Chat/" + chatUser +"/" + userID,chatAddmap);

                    userdatabase.updateChildren(userMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                            if (databaseError != null ){

                                //error

                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

// ======================================================================================Recycle View setup
        messageView.setHasFixedSize(true);
        messageView.setLayoutManager(linearLayoutManager);
        messageView.setAdapter(messageAdapter);
        receiveMessage(); // receive data
//=========================================================================================================message



        chatSendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage ();
            }
        });



    }
// ======================================================================================= receivce
    private void receiveMessage() {

        userdatabase.child("message").child(userID).child(chatUser).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Messgae e = dataSnapshot.getValue(Messgae.class);

                messgaeList.add(e);
             messageAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
// ================================================================================================SEND MESSAGE

    private void sendMessage() {
        String message = chatMessage.getText().toString();

        if (!TextUtils.isEmpty(message)){

            String curren_user = "message/" + userID + "/" +chatUser;
            String other_user = "message/" + chatUser+ "/" + userID;

            DatabaseReference user_message_push = userdatabase.child("message").child(userID).child(chatUser).push();
            String push_id = user_message_push.getKey();

            Map messageMap = new HashMap();
            messageMap.put("message" , message);
            messageMap.put( "send" ,false);
            messageMap.put( "type", "text");
            messageMap.put("time", ServerValue.TIMESTAMP);
            messageMap.put ("from", userID);

            ;


            Map messageUserMap = new HashMap();

            messageUserMap.put (curren_user + "/" +push_id, messageMap );
            messageUserMap.put(other_user + "/" + push_id , messageMap);


            chatMessage.setText("");
//  ================================================================================================== Update Method
            userdatabase.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null ){

                        // error here
                    }
                }
            });


        }
    }


}
