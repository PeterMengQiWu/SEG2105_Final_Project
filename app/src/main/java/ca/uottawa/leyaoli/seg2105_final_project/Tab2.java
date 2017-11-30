package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanzheng on 2017/11/21.
 */


public class Tab2 extends Fragment implements OnItemClickListener {
    private Switch myTasks = null;

    private TaskListAdapter myAdapter;
    private ListView listView;
    private List<Task> taskList;
    private Button new_task;
    private Button refresh;
    private TextView new_task_text_view;
    private TasksDBHandler db;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2,container,false);
        listView = (ListView)view.findViewById(R.id.nameListView);

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

        taskList = new ArrayList<Task>();

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

        myAdapter = new TaskListAdapter(taskList, getContext());
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    public void search(){
        taskList = new ArrayList<Task>();
        taskList = db.getTaskList();
        myAdapter = new TaskListAdapter(taskList, getContext());
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
    }

    public void newTask(){
        Intent newTasks = new Intent(getContext(), AddChore.class);
        startActivity(newTasks);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ChoreDetail.class);
                intent.putExtra("task name", taskList.get(position).getName());
                startActivity(intent);
    }
}
