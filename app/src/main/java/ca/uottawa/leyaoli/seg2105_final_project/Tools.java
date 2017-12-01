package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Tools extends AppCompatActivity implements ToolAdapter.InnerItemOnclickListener {
    private List<Shopping> material;
    private ToolAdapter adapter;
    private List<Shopping> tools;
    private ListView lv;
    private ToolDBHandle db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        material = new ArrayList<Shopping>();
        lv = (ListView)findViewById(R.id.speratetoollist);
        db =new ToolDBHandle(Tools.this);
        getList();
    }

    public void setAdapter(){
        adapter = new ToolAdapter(Tools.this, material);
        adapter.setOnInnerItemOnClickListener(this);
        lv.setAdapter(adapter);
    }

    public void getList() {
        tools = db.getShopList();
        material = new ArrayList<Shopping>();
        for (int i = 0; i < tools.size(); i++) {
            if (tools.get(i).isSelected().compareTo("true")==0)
                if (tools.get(i).getType().compareTo("material") == 0)
                    material.add(tools.get(i));
        }
        setAdapter();
    }

    @Override
    public void itemClick(View view, boolean isChecked) {
        int position = (Integer)view.getTag();
        if (isChecked){
            tools.get(position).setIsUsed("in used");
            if (db.deleteTools(tools.get(position).getName())) {
                db.addTool(tools.get(position));
                getList();
            }
        }else{
            tools.get(position).setIsUsed("free");
            if (db.deleteTools(tools.get(position).getName())) {
                db.addTool(tools.get(position));
                getList();
            }
        }
    }
}
