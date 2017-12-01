package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TaskBaclogActivity extends AppCompatActivity implements TaskListAdapter.InnerItemOnclickListener, AdapterView.OnItemClickListener {

    private ImageView peo_icon;
    private TextView user_name;
    private TextView user_email;
    private ListView myTask;
    private TasksDBHandler db;
    private FirebaseAuth firebaseAuth;
    private String userName;
    private String userEmail;
    private List<Task> tasks;
    private TaskListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_baclog);

        peo_icon = (ImageView)findViewById(R.id.peo_icon);
        user_name = (TextView)findViewById(R.id.user_name);
        user_email = (TextView)findViewById(R.id.user_email);
        myTask = (ListView)findViewById(R.id.myTask);
        db = new TasksDBHandler(TaskBaclogActivity.this);
        firebaseAuth = FirebaseAuth.getInstance();
        userName = firebaseAuth.getCurrentUser().getEmail();
        userEmail = firebaseAuth.getCurrentUser().getEmail();
        tasks = new ArrayList<Task>();
        tasks.addAll(db.findTaskByCreator(userEmail));
        tasks.addAll(db.findTaskByWorker(userEmail));

        user_name.setText(userName);
        user_email.setText(userEmail);

        setAdapter();
    }

    public void setAdapter(){
        myAdapter = new TaskListAdapter(tasks, TaskBaclogActivity.this);
        myAdapter.setOnInnerItemOnClickListener(this);
        myTask.setAdapter(myAdapter);
        myTask.setOnItemClickListener(this);
    }

    @Override
    public void itemClick(View view) {
            int position;
            position = (Integer) view.getTag();
            if (tasks.get(position).getWorker()!=null && tasks.get(position).getWorker().compareTo(userEmail)==0) {
                if (tasks.get(position).getStates().compareTo("COMPLETE")==0)
                    Toast.makeText(TaskBaclogActivity.this, getString(R.string.already_complete), Toast.LENGTH_LONG).show();
                tasks.get(position).setStates("COMPLETE");
                db.updateStates(tasks.get(position).getStates(), tasks.get(position).getName());
                search();
                Toast.makeText(TaskBaclogActivity.this, getString(R.string.successful_complete), Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(TaskBaclogActivity.this, getString(R.string.fail_complete), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (db.findTaskByName(tasks.get(position).getName()) != null) {
            Intent intent = new Intent(TaskBaclogActivity.this, ChoreDetail.class);
            intent.putExtra("task name", tasks.get(position).getName());
            startActivity(intent);
        } else {
            Toast.makeText(TaskBaclogActivity.this, getString(R.string.deleted_chore), Toast.LENGTH_LONG).show();
            search();
        }
    }

     public void search(){
        tasks = db.getTaskList();
        setAdapter();
    }
}
