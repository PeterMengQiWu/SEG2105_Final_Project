package ca.uottawa.leyaoli.seg2105_final_project;

import ca.uottawa.leyaoli.seg2105_final_project.TaskListAdapter.InnerItemOnclickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanzheng on 2017/11/21.
 */


public class Tab2 extends Fragment implements InnerItemOnclickListener, OnItemClickListener {
    private Switch myTasks = null;

    private TaskListAdapter myAdapter;
    private ListView listView;
    private List<Task> taskList;
    private Button new_task;
    private Button refresh;
    private TextView new_task_text_view;
    private TasksDBHandler db;
    private CheckBox showTask;
    private FirebaseAuth firebaseAuth;
    private String userEmail;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2,container,false);
        listView = (ListView)view.findViewById(R.id.nameListView);

        firebaseAuth = FirebaseAuth.getInstance();
        userEmail = firebaseAuth.getCurrentUser().getEmail();

        new_task = (Button)view.findViewById(R.id.new_task);
        new_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTask();
            }
        });
        new_task_text_view = (TextView) view.findViewById(R.id.new_task_text_view);
        new_task_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTask();
            }
        });
        refresh = (Button)view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
        taskList = new ArrayList<Task>();
        db = new TasksDBHandler(getContext());
        taskList = db.getTaskList();
        showTask = (CheckBox) view.findViewById(R.id.showTask);
        showTask.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    taskList = new ArrayList<Task>();
                    taskList.addAll(db.findTaskByCreator(userEmail));
                    taskList.addAll(db.findTaskByWorker(userEmail));
                }else{
                    taskList = db.getTaskList();
                }
                setAdapter();
            }
        });
        setAdapter();
        return view;
    }

    public void search(){
        taskList = db.getTaskList();
        setAdapter();
    }

    public void setAdapter(){
        myAdapter = new TaskListAdapter(taskList, getContext());
        myAdapter.setOnInnerItemOnClickListener(this);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
    }

    public void newTask(){
        Intent newTasks = new Intent(getContext(), AddChore.class);
        startActivity(newTasks);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(db.findTaskByName(taskList.get(position).getName())!=null) {
            Intent intent = new Intent(getContext(), ChoreDetail.class);
            intent.putExtra("task name", taskList.get(position).getName());
            startActivity(intent);
        }else{
            Toast.makeText(getContext(),getString(R.string.deleted_chore), Toast.LENGTH_LONG).show();
            search();
        }
    }

    @Override
    public void itemClick(View view) {
        int position;
        position = (Integer) view.getTag();
        if (taskList.get(position).getWorker()!=null && taskList.get(position).getWorker().compareTo(userEmail)==0) {
            if (taskList.get(position).getStates().compareTo("COMPLETE")==0)
                Toast.makeText(getContext(), getString(R.string.already_complete), Toast.LENGTH_LONG).show();
                taskList.get(position).setStates("COMPLETE");
                db.updateStates(taskList.get(position).getStates(), taskList.get(position).getName());
                search();
                Toast.makeText(getContext(), getString(R.string.successful_complete), Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(getContext(), getString(R.string.fail_complete), Toast.LENGTH_LONG).show();
    }
}