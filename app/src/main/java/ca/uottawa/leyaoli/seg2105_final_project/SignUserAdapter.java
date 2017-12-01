package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by KevinLee on 2017/12/1.
 */

public class SignUserAdapter extends BaseAdapter {
    private Context context;
    private List<String> user;

    public SignUserAdapter (Context context, List<String>user){
        this.context = context;
        this.user = user;
    }

    @Override
    public int getCount() {
        return user.size();
    }

    @Override
    public Object getItem(int position) {
        return user.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.people_layout, null);
            viewHolder.ico = (ImageView) convertView.findViewById(R.id.icon);
            viewHolder.name = (TextView)convertView.findViewById(R.id.itemName);
            viewHolder.description = (TextView)convertView.findViewById(R.id.itemDescription);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        return convertView;
    }

    private final static class ViewHolder{
        public ImageView ico;
        public TextView name;
        public TextView description;
    }


}
