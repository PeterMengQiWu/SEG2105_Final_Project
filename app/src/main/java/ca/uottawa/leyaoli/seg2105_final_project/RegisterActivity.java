package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout name;
    private TextInputLayout email;
    private TextInputLayout pass;
    private  Button bt;
    private FirebaseAuth mAuth;
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

    private void register(String disName, String disEmail, String disPass) {
        mAuth.createUserWithEmailAndPassword(disEmail, disPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent startApp = new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(startApp);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            String TAG = "EXCEPTION";
                            FirebaseException e = (FirebaseException) task.getException();

                            Toast.makeText(RegisterActivity.this, " 你是傻逼吗？",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }
}
