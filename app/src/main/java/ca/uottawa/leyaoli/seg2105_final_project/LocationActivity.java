package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity {

    private RadioGroup martket;
    private RadioButton walmart;
    private RadioButton loblaws;
    private RadioButton metro;
    private RadioButton CT;
    private RadioButton bestbuy;
    private RadioButton TnT;
    private RadioButton shoppers;
    private RadioButton other;
    EditText marketAddress;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        martket = (RadioGroup)findViewById(R.id.market);
        walmart = (RadioButton)findViewById(R.id.walmart);
        loblaws = (RadioButton)findViewById(R.id.loblaw);
        metro = (RadioButton)findViewById(R.id.metro);
        CT = (RadioButton)findViewById(R.id.canadian_tire);
        bestbuy = (RadioButton)findViewById(R.id.bestbuy);
        TnT = (RadioButton)findViewById(R.id.T_n_T);
        shoppers = (RadioButton)findViewById(R.id.shoppers_drug_mart);
        other = (RadioButton)findViewById(R.id.other);

        martket.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == walmart.getId()){
                    address = "walmart";
                }
                else if (checkedId == loblaws.getId()){
                    address = "loblaws";
                }
                else if (checkedId == metro.getId()){
                    address = "metro";
                }
                else if (checkedId == CT.getId()){
                    address = "Canadian Tire";
                }
                else if (checkedId == bestbuy.getId()){
                    address = "bestbuy";
                }
                else if (checkedId == TnT.getId()){
                    address = "T and T";
                }
                else if (checkedId == shoppers.getId()){
                    address = "shoppers drug mart";
                }
                else if (checkedId == other.getId()){
                    marketAddress = (EditText)findViewById(R.id.editText);
                    address = marketAddress.getText().toString();
                }
            }
        });
    }

    public void NavigateButton (View view) {
        // Create a Uri from an intent string. Use the result to create an Intent.
        Uri gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q="+address);
        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        // Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");
        // Attempt to start an activity that can handle the Intent
        startActivity(mapIntent);
    }
}
