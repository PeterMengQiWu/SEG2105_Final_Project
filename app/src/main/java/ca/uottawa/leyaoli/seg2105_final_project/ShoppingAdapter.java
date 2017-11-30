package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tyson on 2017-11-28.
 */
class Shopping{
    String name;
    boolean selected=false;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    String Type;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}



public class ShoppingAdapter extends ArrayAdapter {
    private  Context context;
    private  List<Shopping> chores;
    public ShoppingAdapter( Context context, List<Shopping> chores) {
        super(context, R.layout.activity_add, chores);
        this.context = context;
        this.chores = chores;
    }
    private static class Shoppingholder{
        public TextView chname1;

        public CheckBox ch1;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        Shoppingholder holder = new Shoppingholder();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.activity_add, null);

            holder.ch1 = (CheckBox) v.findViewById(R.id.checkBox1);
            holder.chname1 = (TextView) v.findViewById(R.id.textViewB1);

        }else{
            holder = (Shoppingholder)v.getTag();
        }
        Shopping s = chores.get(position);
        holder.chname1.setText(s.getName());
        holder.ch1.setChecked(s.isSelected());
        holder.ch1.setText("");
        holder.ch1.setTag(s);



        return v;
    }
}
