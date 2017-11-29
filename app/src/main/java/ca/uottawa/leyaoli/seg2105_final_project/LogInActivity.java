package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LogInActivity extends AppCompatActivity {


    private Button bt;
    private TextInputLayout email ;
    private TextInputLayout pass ;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();

        bt = (Button) findViewById(R.id.sign_in_button);
        email = (TextInputLayout) findViewById(R.id.loginEmail);
        pass = (TextInputLayout) findViewById(R.id.logInPassword);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qemail = email.getEditText().getText().toString();
                String qpass = pass.getEditText().getText().toString();
                // check empty

                logInUser (qemail,qpass);
            }
        });
    }
// log in progress to do
    private void logInUser(String qemail, String qpass) {
        mAuth.signInWithEmailAndPassword(qemail,qpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent mainintent = new Intent(LogInActivity.this, MainActivity.class);
                    //ADD TO REGISTER PAGE
                    mainintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainintent);
                    finish();
                }else {
                    Toast.makeText(LogInActivity.this ,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
