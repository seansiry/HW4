package com.to626.hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    Button searchButton;
    EditText zipcodeEditText;
    TextView speciesTextView, sighterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchButton = findViewById(R.id.button_search);
        zipcodeEditText = findViewById(R.id.editText_search);
        speciesTextView = findViewById(R.id.textView_foundSpecies);
        sighterTextView = findViewById(R.id.textView_sighter);

        searchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == searchButton){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference("birdSightings");


        }
    }
}
