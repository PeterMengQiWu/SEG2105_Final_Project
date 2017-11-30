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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanzheng on 2017/11/21.
 */


public class Tab2 extends Fragment{
    private Switch myTasks = null;

    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;
    private List<String> nameList;
    private List<Task> taskList;
    private Button new_task;
    private Button refresh;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2,container,false);
        listView = (ListView)view.findViewById(R.id.nameListView);

        new_task = (Button)view.findViewById(R.id.new_task);
        new_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newTasks = new Intent(getContext(), AddChore.class);
                startActivity(newTasks);
            }
        });

        nameList = new ArrayList<String>();
        taskList = new ArrayList<Task>();

        refresh = (Button)view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

        nameList = new ArrayList<String>();
        taskList = new ArrayList<Task>();
        TasksDBHandler dbHandler = new TasksDBHandler(getContext());
        taskList = dbHandler.getTaskList();
        if (taskList != null) {
            for (int i = 0; i < taskList.size(); i++) {
                nameList.add(taskList.get(i).getName());
            }
        } else {
            Toast.makeText(getContext(), "No Match Find", Toast.LENGTH_LONG).show();
        }
        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, nameList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ChoreDetail.class);
                intent.putExtra("task name", nameList.get(position).toString());
                startActivity(intent);
            }
        });
        return view;
    }

    public void search(){
        nameList = new ArrayList<String>();
        taskList = new ArrayList<Task>();
        TasksDBHandler dbHandler = new TasksDBHandler(getContext());
        taskList = dbHandler.getTaskList();
        if (taskList != null) {
            for (int i = 0; i < taskList.size(); i++) {
                nameList.add(taskList.get(i).getName());
            }
        } else {
            Toast.makeText(getContext(), "No Match Find", Toast.LENGTH_LONG).show();
        }
        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, nameList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ChoreDetail.class);
                intent.putExtra("task name", nameList.get(position).toString());
                startActivity(intent);
            }
        });
    }
}
