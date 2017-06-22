package com.example.dell_pc.testmaps;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // to launch the GPS setttings activity on the device
        Intent gpsOptionsIntent = new Intent(
                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(gpsOptionsIntent);

    }
    public void onImageClick(View view)
    {
       /* FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!"); */

        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }
    public void onBinImageClicked(View view)
    {
        Intent i = new Intent(this,DustbinActivity.class);
        startActivity(i);
    }
    public void onSubmitFeedbackClicked (View view)
    {
        Uri uri = Uri.parse("https://form.jotform.me/71473429270457");
        Intent intent = new Intent (Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
