package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ChangeName extends AppCompatActivity {

    private Button bt;
    private EditText tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        bt = (Button) findViewById(R.id.changeNameBt);
        tx = (EditText)findViewById(R.id.editText3);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String text = tx.getText().toString();
                intent.putExtra("res",text);
                setResult(0,intent);
                finish();
            }
        });


    }
}
