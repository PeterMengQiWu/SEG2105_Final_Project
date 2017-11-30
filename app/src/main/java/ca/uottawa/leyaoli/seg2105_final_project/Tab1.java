package ca.uottawa.leyaoli.seg2105_final_project;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Intent.getIntent;


/**
 * Created by yuanzheng on 2017/11/21.
 */


public class  Tab1 extends Fragment  implements android.widget.CompoundButton.OnCheckedChangeListener{
    ListView lv;
    ListView lv2;
    ArrayList<Shopping>chores;
    ArrayList<Shopping>chores2;
    ShoppingAdapter adapter;
    ShoppingAdapter adapter2;
    Button add;
    // Button type;
    EditText text;
    String typeSelected;
    CheckBox ch1;
    CheckBox ch2;
    ToolDBHandle dbHandle;




    @Nullable
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
        chores = new ArrayList<Shopping>();
        chores2 = new ArrayList<Shopping>();
        dbHandle = new ToolDBHandle(getActivity());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shopping sh = new Shopping();
                if(ch1.isChecked()){
                    typeSelected="";
                    sh.setName(text.getText().toString());
                    sh.setType(typeSelected);
                    sh.setSelected(false);
                    dbHandle.addTool(sh);
                    chores.add(sh);
                }else if(ch2.isChecked()){
                    typeSelected = "material";
                    sh.setName(text.getText().toString());
                    sh.setType(typeSelected);
                    sh.setSelected(false);
                    dbHandle.addTool(sh);
                    chores2.add(sh);
                }
                else{
                    Toast.makeText(getContext(),"Please select the type you want to add to shopping list!",Toast.LENGTH_LONG).show();
                }
            }
        });
        displayShoppingList();
        return view;

    }
    private void displayShoppingList(){
        adapter = new ShoppingAdapter(getContext(),chores);
        adapter2 = new ShoppingAdapter(getContext(),chores2);
        lv.setAdapter(adapter);
        lv2.setAdapter(adapter2);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int pos = lv.getPositionForView(buttonView);
        if(pos != ListView.INVALID_POSITION){
            Shopping sh = chores.get(pos);
            sh.setSelected(isChecked);
            dbHandle.deleteTask(sh.getName(),"false");
        }
    }
}





