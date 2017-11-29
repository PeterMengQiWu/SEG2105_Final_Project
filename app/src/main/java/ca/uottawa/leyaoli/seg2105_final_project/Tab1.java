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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Intent.getIntent;


/**
 * Created by yuanzheng on 2017/11/21.
 */


public class  Tab1 extends Fragment {
    ListView lv;
    ListView lv2;
    ArrayList<Shopping>chores;
    ArrayList<Shopping>chores2;
    ShoppingAdapter adapter;
    ShoppingAdapter adapter2;
    Button add;
   // Button type;
    EditText text;
    EditText type1;
    EditText type2;
    String typeSelected;
    CheckBox ch1;
    CheckBox ch2;




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
        //type=view.findViewById(R.id.type);
        type1=view.findViewById(R.id.Type1);
        type2=view.findViewById(R.id.Type2);
        text = view.findViewById(R.id.textadd);
        chores = new ArrayList<Shopping>();
        chores2 = new ArrayList<Shopping>();






        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch1.setText(type1.getText().toString());
                ch2.setText(type2.getText().toString());
                if(ch1.isChecked()){
                    typeSelected=ch1.getText().toString();
                }else if(ch2.isChecked()){
                    typeSelected = ch2.getText().toString();
                }

                if(ch1.isChecked()) {
                    chores.add(new Shopping(text.getText().toString()));
                }
                else if (ch2.isChecked()){
                    chores2.add(new Shopping(text.getText().toString()));

                }
                else{

                    Toast toast = Toast.makeText(getContext(),typeSelected+"123",Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });
        displayShoppingList1();

        return view;

    }
    private void displayShoppingList1(){


        adapter = new ShoppingAdapter(getContext(),chores);
        adapter2 = new ShoppingAdapter(getContext(),chores2);
        lv.setAdapter(adapter);
        lv2.setAdapter(adapter2);
    }


    }





