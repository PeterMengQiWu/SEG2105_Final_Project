package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by yuanzheng on 2017/11/21.
 */

public class ChoreDetail extends AppCompatActivity {

    private String task_name;
    private Task task;

    private EditText taskName;
    private TextView points;
    private TextView dueDate;
    private TextView dueTime;
    private TextView states;
    private TextView creator;
    private TextView worker;
    private TasksDBHandler db = new TasksDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_detail);

        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        taskName = (EditText)findViewById(R.id.taskName);
        points = (TextView)findViewById(R.id.points);
        dueDate = (TextView)findViewById(R.id.dueDate);
        dueTime = (TextView)findViewById(R.id.dueTime);
        states = (TextView)findViewById(R.id.states);
        creator = (TextView)findViewById(R.id.creator);
        worker = (TextView)findViewById(R.id.worker);

        task_name = getIntent().getStringExtra("task name");


        task = db.findTaskByName(task_name);
        if(task!=null) {
            taskName.setText(task_name);
            points.setText((task.getPoints() + ""));
            dueDate.setText(task.getDueDate());
            dueTime.setText(task.getDueTime());
            states.setText(task.getStates());
            creator.setText(task.getCreator());
            if (task.getWorker()!=null)
                worker.setText(task.getWorker());
        }


    }

    public void deleteChore(View view){
        if(db.deleteTask(task_name,task.getCreator()))
            Toast.makeText(ChoreDetail.this,"Successful delete",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(ChoreDetail.this,"Fail to delete",Toast.LENGTH_LONG).show();
        finish();
    }
}