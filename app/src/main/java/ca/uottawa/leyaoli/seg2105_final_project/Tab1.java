package ca.uottawa.leyaoli.seg2105_final_project;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by yuanzheng on 2017/11/21.
 */


public class  Tab1 extends Fragment{
    private ListView lv;
    private ListView lv2;
    private List<Shopping>groceries;
    private List<Shopping>materials;
    private ShoppingAdapter adapter;
    private ShoppingAdapter adapter2;
    private Button add;
    // Button type;
    private EditText text;
    private String typeSelected;
    private CheckBox ch1;
    private CheckBox ch2;
    private ToolDBHandler db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1,container,false);

        lv = (ListView)view.findViewById(R.id.list1);//ini listview 1
        lv2= (ListView)view.findViewById(R.id.list2);//ini listview 2
        add = (Button)view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shopping sh = new Shopping();
                if(ch1.isChecked()){
                    typeSelected="groceries";
                    sh.setName(text.getText().toString());
                    sh.setType(typeSelected);
                    sh.setSelected("false");
                    db.addTool(sh);
                }else if(ch2.isChecked()){
                    typeSelected = "material";
                    sh.setName(text.getText().toString());
                    sh.setType(typeSelected);
                    sh.setSelected("false");
                    db.addTool(sh);
                }
                else{
                    Toast.makeText(getContext(),"Please select the type you want to add to shopping list!",Toast.LENGTH_LONG).show();
                }
                getList();
            }
        });
        ch1 = (CheckBox)view.findViewById(R.id.checkBoxtype1);
        ch2 = (CheckBox)view.findViewById(R.id.checkBoxtype2);
        text = (EditText)view.findViewById(R.id.textadd);
        groceries = new ArrayList<Shopping>();
        materials = new ArrayList<Shopping>();
        db = new ToolDBHandler(getContext());
        groceries = db.FindShoppingList("groceries", "false");
        materials = db.FindShoppingList("material", "false");
        setAdapter();
        return view;
    }

    public void getList() {
        groceries = db.FindShoppingList("groceries", "false");
        materials = db.FindShoppingList("material", "false");
        setAdapter();
    }

    private void setAdapter(){
        adapter = new ShoppingAdapter(getContext(),groceries);
        adapter.setOnInnerItemOnClickListener(new ShoppingAdapter.InnerItemOnclickListener() {
            @Override
            public void itemClick(View view, boolean isChecked) {
                int position = (Integer)view.getTag();
                if (isChecked){
                    groceries.get(position).setSelected("true");
                    db.updateStates(groceries.get(position).isSelected(), groceries.get(position).getName());
                    getList();
                }
            }
        });
        adapter2 = new ShoppingAdapter(getContext(),materials);
        adapter2.setOnInnerItemOnClickListener(new ShoppingAdapter.InnerItemOnclickListener() {
            @Override
            public void itemClick(View view, boolean isChecked) {
                int position = (Integer)view.getTag();
                if (isChecked){
                    materials.get(position).setSelected("true");
                    db.updateStates(materials.get(position).isSelected(), materials.get(position).getName());
                    getList();
                }
            }
        });
        lv.setAdapter(adapter);
        lv2.setAdapter(adapter2);
    }
}