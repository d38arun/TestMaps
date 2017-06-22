package com.example.dell_pc.testmaps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {
    EditText name, number ,email;
    String latitude, longitude;
    String urlAdd = "https://chefsondemand.000webhostapp.com/datapost.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        name= (EditText)findViewById(R.id.nameField);
        number= (EditText)findViewById(R.id.numberField);
        email=(EditText)findViewById(R.id.emailField);

        Bundle mapdata = getIntent().getExtras();
        latitude = mapdata.getString("Latitude");
        longitude=mapdata.getString("Longitude");
        Toast.makeText(this,"lat "+latitude+" long "+longitude,Toast.LENGTH_LONG).show();
    }
    public void submitClicked (View v)
    {
        Sender s = new Sender(DetailsActivity.this,urlAdd,latitude,longitude,name,number,email);
        s.execute();


    }
}
