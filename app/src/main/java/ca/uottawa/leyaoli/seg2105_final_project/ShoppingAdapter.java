package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by tyson on 2017-11-28.
 */
class Shopping{
    private String name;
    private String type;
    boolean selected=false;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}



public class ShoppingAdapter extends BaseAdapter {
    private  Context context;
    private  List<Shopping> tools;
    public ShoppingAdapter( Context context, List<Shopping> tools) {
        this.context = context;
        this.tools = tools;
    }

    private final static class ViewHolder{
        public CheckBox checkBox;
        public TextView textView;
    }

    @Override
    public int getCount() {
        return tools.size();
    }

    @Override
    public Object getItem(int position) {
        return tools.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_add, null);
            viewHolder.checkBox = (CheckBox)convertView.findViewById(R.id.Tools_checkBox);
            viewHolder.textView = (TextView)convertView.findViewById(R.id.tool_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.checkBox.setTag(position);
        viewHolder.textView.setTag(position);
        viewHolder.textView.setText(tools.get(position).getName());
        return convertView;
    }
}