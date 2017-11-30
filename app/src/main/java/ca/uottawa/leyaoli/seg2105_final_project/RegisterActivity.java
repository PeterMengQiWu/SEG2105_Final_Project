package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout name;
    private TextInputLayout email;
    private TextInputLayout pass;
    private  Button bt;
    private FirebaseAuth mAuth;

    private DatabaseReference database ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

       name = (TextInputLayout) findViewById( R.id.registerName);
       email = (TextInputLayout) findViewById(R.id.registerEmail);
        pass = (TextInputLayout) findViewById(R.id.registerPassword);
       bt = (Button) findViewById(R.id.resgisterButton2);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String disName = name.getEditText().getText().toString();
                String disEmail = email.getEditText().getText().toString();
                String disPass = pass.getEditText().getText().toString();

                register (disName,disEmail,disPass);
            }
        });



    }

    private void register(final String disName, final String disEmail, String disPass) {
        mAuth.createUserWithEmailAndPassword(disEmail, disPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                             FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                             String uid = currentUser.getUid();
                             database = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                            userProfile(disName);

                            HashMap<String,String> userMap = new HashMap<>();
                            userMap.put("name",disName);
                            userMap.put("email",disEmail);
                            userMap.put("image","avatar");
                            userMap.put("thumb_image","avatar");
                            database.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent startApp = new Intent(RegisterActivity.this,MainActivity.class);
                                        startActivity(startApp);
                                        finish();

                                    }
                                }
                            });

                            //FirebaseUser user = mAuth.getCurrentUser();



                        } else {
                            // If sign in fails, display a message to the user.
                            String TAG = "EXCEPTION";
                            FirebaseException e = (FirebaseException) task.getException();

                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }

    private void userProfile (String disName){
        FirebaseUser user = mAuth.getCurrentUser();

        if (user!= null){

            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(disName).build();


        }
    }
}
