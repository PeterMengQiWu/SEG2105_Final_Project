package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, TaskListAdapter.InnerItemOnclickListener {
    private TasksDBHandler db;
    private List<Task> taskList;
    private ListView listView;
    private TaskListAdapter myAdapter;
    private FirebaseAuth firebaseAuth;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        firebaseAuth = FirebaseAuth.getInstance();
        userEmail = firebaseAuth.getCurrentUser().getEmail();
        db = new TasksDBHandler(ScheduleActivity.this);
        taskList = db.getTaskList();
        listView = (ListView)findViewById(R.id.scheduleList);
        Collections.sort(taskList);
        myAdapter = new TaskListAdapter(taskList, ScheduleActivity.this);
        myAdapter.setOnInnerItemOnClickListener(this);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(db.findTaskByName(taskList.get(position).getName())!=null) {
            Intent intent = new Intent(ScheduleActivity.this, ChoreDetail.class);
            intent.putExtra("task name", taskList.get(position).getName());
            startActivity(intent);
        }else{
            Toast.makeText(ScheduleActivity.this,getString(R.string.deleted_chore), Toast.LENGTH_LONG).show();
            search();
        }
    }

    public void search(){
        taskList = db.getTaskList();
        Collections.sort(taskList);
        myAdapter = new TaskListAdapter(taskList, ScheduleActivity.this);
        myAdapter.setOnInnerItemOnClickListener(this);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void itemClick(View view) {
        int position;
        position = (Integer) view.getTag();
        if (taskList.get(position).getWorker()!=null && taskList.get(position).getWorker().compareTo(userEmail)==0) {
            if (taskList.get(position).getStates().compareTo("COMPLETE")==0)
                Toast.makeText(ScheduleActivity.this, getString(R.string.already_complete), Toast.LENGTH_LONG).show();
            taskList.get(position).setStates("COMPLETE");
            db.updateStates(taskList.get(position).getStates(), taskList.get(position).getName());
            search();
            Toast.makeText(ScheduleActivity.this, getString(R.string.successful_complete), Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(ScheduleActivity.this, getString(R.string.fail_complete), Toast.LENGTH_LONG).show();
    }
}
