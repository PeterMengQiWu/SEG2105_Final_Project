package ca.uottawa.leyaoli.seg2105_final_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CupBoard extends AppCompatActivity implements CupBoardAdapter.InnerItemOnclickListener {
    private List<Shopping> groceries;
    private CupBoardAdapter adapter;
    private ListView lv;
    private ToolDBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cup_board);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        groceries = new ArrayList<Shopping>();
        lv = (ListView)findViewById(R.id.cupboardlist);
        db =new ToolDBHandler(CupBoard.this);
        getList();
    }

    public void setAdapter(){
        adapter = new CupBoardAdapter(CupBoard.this, groceries);
        adapter.setOnInnerItemOnClickListener(this);
        lv.setAdapter(adapter);
    }

    public void getList() {
        groceries = db.FindShoppingList("groceries", "true");
        setAdapter();
    }

    @Override
    public void itemClick(View view, boolean isChecked) {
        int position = (Integer)view.getTag();
        if (isChecked){
            groceries.get(position).setIsUsed("used");
            db.updateUse(groceries.get(position).getIsUsed(), groceries.get(position).getName());
            getList();
        }else{
            groceries.get(position).setIsUsed("free");
            db.updateUse(groceries.get(position).getIsUsed(), groceries.get(position).getName());
            getList();
        }
    }
}