package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartPage extends AppCompatActivity {
    private Button bt;
    private  Button bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);


        bt = (Button) findViewById(R.id.registerButton1);
        bt2 = (Button) findViewById(R.id.Sign_In );
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  reg = new Intent(StartPage.this,RegisterActivity.class);
                startActivity(reg);

            }


        });


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log  = new Intent(StartPage.this, LogInActivity.class);
                startActivity(log);
            }
        });


    }
}
