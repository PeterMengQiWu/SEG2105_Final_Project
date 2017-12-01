package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class Tools extends AppCompatActivity {
    private List<Shopping> Metarials;
    private ToolAdapter adapter;
    private Context context;
    private List<Shopping> tools;
    private ListView lv;
    private ToolDBHandle db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
        getList();
        displayCupBoardList();

    }
    public void displayCupBoardList(){
        adapter = new ToolAdapter(context,Metarials);
        lv.setAdapter(adapter);
    }
    public void getList() {
        tools = db.getShopList();
        for (int i = 0; i < tools.size(); i++) {
            if (tools.get(i).getType().compareTo("material") == 0) {
                Metarials.add(tools.get(i));
            }
        }

    }
}
