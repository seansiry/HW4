package com.to626.hw4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReportActivity extends AppCompatActivity implements View.OnClickListener{
    Button submitButton;
    EditText birdEditText, nameEditText, zipcodeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        birdEditText = findViewById(R.id.editText_bird);
        zipcodeEditText = findViewById(R.id.editText_zipcode);
        nameEditText = findViewById(R.id.editText_name);
        submitButton = findViewById(R.id.button_submit);

        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == submitButton) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference("birdSightings");

            String bird = birdEditText.getText().toString();
            String zipString = zipcodeEditText.getText().toString();
            String name = nameEditText.getText().toString();
            if(bird.isEmpty()){
                Toast.makeText(this, "Enter Type of Bird for Sighting", Toast.LENGTH_SHORT).show();
                return;
            }
            if(zipString.isEmpty()){
                Toast.makeText(this, "Enter Zip Code for Sighting", Toast.LENGTH_SHORT).show();
                return;
            }
            if(name.isEmpty()){
                Toast.makeText(this, "Enter Birdwatcher Name for Sighting", Toast.LENGTH_SHORT).show();
                return;
            }
            int zipcode = Integer.parseInt(zipString);
            BirdSighting createSighting = new BirdSighting(bird, zipcode, name);
            myRef.push().setValue(createSighting);
            String toastString = bird + " sighting at " + zipString + " by " + name + " added to database.";
            Toast.makeText(this, toastString, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_report){
            Toast.makeText(this, "Already in Report Sighting Screen", Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.item_search){
            startActivity(new Intent(ReportActivity.this, SearchActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
