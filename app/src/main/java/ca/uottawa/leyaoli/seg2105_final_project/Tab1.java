package ca.uottawa.leyaoli.seg2105_final_project;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import static android.content.Intent.getIntent;


/**
 * Created by yuanzheng on 2017/11/21.
 */


public class  Tab1 extends Fragment implements ShoppingAdapter.InnerItemOnclickListener {
    private ListView lv;
    private ListView lv2;
    private List<Shopping>groceries;
    private List<Shopping>materials;
    private List<Shopping> tools;
    private ShoppingAdapter adapter;
    private ShoppingAdapter adapter2;
    private Button add;
    // Button type;
    private EditText text;
    private String typeSelected;
    private CheckBox ch1;
    private CheckBox ch2;
    private ToolDBHandle db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1,container,false);

        lv = view.findViewById(R.id.list1);//ini listview 1
        lv2= view.findViewById(R.id.list2);//ini listview 2
        add = view.findViewById(R.id.add);
        ch1 = view.findViewById(R.id.checkBoxtype1);
        ch2 = view.findViewById(R.id.checkBoxtype2);

        text = view.findViewById(R.id.textadd);
        groceries = new ArrayList<Shopping>();
        materials = new ArrayList<Shopping>();
        db = new ToolDBHandle(getContext());
        getList();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shopping sh = new Shopping();
                if(ch1.isChecked()){
                    typeSelected="groceries";
                    sh.setName(text.getText().toString());
                    sh.setType(typeSelected);
                    sh.setSelected(false);
                    db.addTool(sh);
                    groceries.add(sh);
                }else if(ch2.isChecked()){
                    typeSelected = "material";
                    sh.setName(text.getText().toString());
                    sh.setType(typeSelected);
                    sh.setSelected(false);
                    db.addTool(sh);
                    materials.add(sh);
                }
                else{
                    Toast.makeText(getContext(),"Please select the type you want to add to shopping list!",Toast.LENGTH_LONG).show();
                }
            }
        });
        setAdapter();
        return view;
    }
    private void setAdapter(){
        adapter = new ShoppingAdapter(getContext(),groceries);
        adapter.setOnInnerItemOnClickListener(this);
        adapter2 = new ShoppingAdapter(getContext(),materials);
        adapter2.setOnInnerItemOnClickListener(this);
        lv.setAdapter(adapter);
        lv2.setAdapter(adapter2);
    }

    public void getList() {
        tools = db.getShopList();
        groceries = new ArrayList<Shopping>();
        materials = new ArrayList<Shopping>();
        for (int i = 0; i < tools.size(); i++) {
            if (tools.get(i).getType().compareTo("groceries") == 0)
                groceries.add(tools.get(i));
            if (tools.get(i).getType().compareTo("material") == 0)
                materials.add(tools.get(i));
        }
        setAdapter();
    }

    @Override
    public void itemClick(View view, boolean isChecked) {
        int position = (Integer)view.getTag();
        if (isChecked){
            if (db.deleteTools(tools.get(position).getName())) {
                getList();
                Toast.makeText(getContext(), getString(R.string.successful_delete), Toast.LENGTH_LONG).show();
            }
        }
    }
}