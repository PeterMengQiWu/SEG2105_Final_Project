package ca.uottawa.leyaoli.seg2105_final_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by yuanzheng on 2017/11/21.
 */


public class AddChore extends AppCompatActivity {
    /*
    public  boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }

        return  super.onOptionsItemSelected(item);
    }*/


    private TextView nameBox;
    private TextView pointsBox;
    private TextView dueDateBox;
    private TextView dueTimeBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chore);
        // getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameBox = (TextView)findViewById(R.id.name);
        pointsBox = (TextView)findViewById(R.id.points);
        dueDateBox = (TextView)findViewById(R.id.date);
        dueTimeBox = (TextView)findViewById(R.id.time);
    }

    public void confirm (View view){
        double points = Double.parseDouble(pointsBox.getText().toString());
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String userID = firebaseAuth.getCurrentUser().getUid();
        Task task = new Task(nameBox.getText().toString(),points,dueDateBox.getText().toString(),dueTimeBox.getText().toString(),userID,"PENDING");
        TasksDBHandler dbHandler = new TasksDBHandler(this);
        dbHandler.addTask(task);
        nameBox.setText("");
        pointsBox.setText("");
        dueDateBox.setText("");
        dueTimeBox.setText("");
        finish();
    }
}
