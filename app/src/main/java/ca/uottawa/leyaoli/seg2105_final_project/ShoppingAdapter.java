package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by tyson on 2017-11-28.
 */
class Shopping{
    private String name;
    private String type;
    private String selected = "false";
    private String isUsed = "free";


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getIsUsed() {return isUsed;}

    public String isSelected() {
        return selected;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public void setIsUsed(String isUsed){
        this.isUsed = isUsed;
    }
}



public class ShoppingAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {
    private  Context context;
    private  List<Shopping> tools;
    private  InnerItemOnclickListener mListener;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
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
        viewHolder.textView.setText(tools.get(position).getName());
        viewHolder.checkBox.setTag(position);
        viewHolder.checkBox.setOnCheckedChangeListener(this);
        return convertView;
    }

    interface InnerItemOnclickListener {
        void itemClick(View view, boolean isChecked);
    }

    public void setOnInnerItemOnClickListener(InnerItemOnclickListener listener){
        this.mListener=listener;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mListener.itemClick(buttonView, isChecked);
    }
}