package ca.uottawa.leyaoli.seg2105_final_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class UserActivity extends AppCompatActivity {

    private RecyclerView userlist ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //getSupportActionBar().setTitle("ALL USER");
        //getSupportActionBar().setHomeButtonEnabled(true);


        userlist=  (RecyclerView)findViewById(R.id.user_list);

    }
}
