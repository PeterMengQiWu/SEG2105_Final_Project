package ca.uottawa.leyaoli.seg2105_final_project;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;

import java.util.ArrayList;


/**
 * Created by yuanzheng on 2017/11/21.
 */


public class  Tab1 extends Fragment {
    private CheckBox ch1;
    private CheckBox ch;
    private EditText text;
    private int[]id1;//Metarial
    private int []id2;//Groceries
    private View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_tab1,container,false);




        Button  bt = view.findViewById(R.id.Add);
       id1 = new int[]{R.id.checkBoxM1,R.id.checkBoxM2,R.id.checkBoxM3,R.id.checkBoxM4,R.id.checkBoxM5,R.id.checkBoxM6,R.id.checkBoxM7,R.id.checkBoxM8};
       id2 = new int[]{R.id.checkBoxG1,R.id.checkBoxG2,R.id.checkBoxG3,R.id.checkBoxG4,R.id.checkBoxG5,R.id.checkBoxG6,R.id.checkBoxG7,R.id.checkBoxG8};

       text = view.findViewById(R.id.addName);
       for (int i=0; i<id1.length;i++){
           ch= view.findViewById(id1[i]);

               ch.setVisibility(View.GONE);
       }
        for(int pos=0;pos<id1.length;pos++) {
            ch1 = view.findViewById(id1[pos]);
            if(ch1.getText().toString().equals("")){

                break;
            }
        }


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jia cailiao

                for(int pos=0;pos<id1.length;pos++) {
                    ch1 = view.findViewById(id1[pos]);
                    add(view,ch1);
                    break;
                    }
            }
        });

        return view;

}
public void add(View view,CheckBox ch1){

        if(ch1.getText().toString().equals("")){
            ch1.setText(text.getText());
            ch1.setVisibility(View.VISIBLE);
        }

}



}
