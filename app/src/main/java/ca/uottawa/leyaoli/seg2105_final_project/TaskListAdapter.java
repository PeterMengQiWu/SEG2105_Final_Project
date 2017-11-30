package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.Tasks;

import java.util.List;

/**
 * Created by Kevin-Lee on 2017/11/30.
 */

public class TaskListAdapter extends BaseAdapter{
    private List<Task> tasks;
    private Context context;

    public TaskListAdapter(List<Task> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.chorelayout,null);
            viewHolder.task_name = (TextView)convertView.findViewById(R.id.task_name);
            viewHolder.task_states = (TextView)convertView.findViewById(R.id.task_states);
            viewHolder.worker_ico = (ImageView)convertView.findViewById(R.id.worker_ico);
            viewHolder.finish = (CheckBox)convertView.findViewById(R.id.finish);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.task_name.setText(tasks.get(position).getName());
        viewHolder.task_states.setText(tasks.get(position).getStates());
        return convertView;
    }

    public final static class ViewHolder {
        TextView task_name;
        TextView task_states;
        ImageView worker_ico;
        CheckBox finish;
    }
}
