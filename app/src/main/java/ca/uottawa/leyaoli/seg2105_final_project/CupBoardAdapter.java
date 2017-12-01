/**
 * Created by tyson on 2017-12-01.
 */
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

import java.util.ArrayList;
import java.util.List;

public class CupBoardAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {
    private  Context context;
    private  List<Shopping> tools;
    private InnerItemOnclickListener mListener;

    public  CupBoardAdapter( Context context, List<Shopping> tools) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_add_cup_board, null);
            viewHolder.checkBox = (CheckBox)convertView.findViewById(R.id.CupBoardcheckbox);
            viewHolder.textView = (TextView)convertView.findViewById(R.id.cupboardstatus);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.checkBox.setTag(position);
        viewHolder.checkBox.setText(tools.get(position).getName());
        if (tools.get(position).getIsUsed().compareTo("used")==0)
            viewHolder.checkBox.setChecked(true);
        viewHolder.textView.setText(tools.get(position).getIsUsed());
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
