package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CupBoard extends AppCompatActivity {
    private List<Shopping> groceries;
    private CupBoardAdapter adapter;
    private Context context;
    private List<Shopping> tools;
    private ListView lv;
    private ToolDBHandle db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cup_board);
        groceries = new ArrayList<Shopping>();
        lv = (ListView)findViewById(R.id.cupboardlist);
        db =new ToolDBHandle(context);

        getList();
        displayCupBoardList();

    }
    public void displayCupBoardList(){
        adapter = new CupBoardAdapter(context,groceries);
        lv.setAdapter(adapter);
    }
    public void getList() {
        tools = db.getShopList();
        for (int i = 0; i < tools.size(); i++) {
            if (tools.get(i).getType().compareTo("groceries") == 0) {
                groceries.add(tools.get(i));
            }
        }

    }
}