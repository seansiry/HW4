package com.to626.hw4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
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

            String zipString = zipcodeEditText.getText().toString();
            if(zipString.isEmpty()){
                Toast.makeText(this, "Enter Zip Code to Search For", Toast.LENGTH_SHORT).show();
                return;
            }
            int zipSearch = Integer.parseInt(zipString);
            myRef.orderByChild("zipcode").equalTo(zipSearch).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    //String findKey = dataSnapshot.getKey();
                    BirdSighting foundBird = dataSnapshot.getValue(BirdSighting.class);
                    String findSpecies = foundBird.species;
                    String findSighter = foundBird.sighter;
                    speciesTextView.setText(findSpecies);
                    sighterTextView.setText(findSighter);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    BirdSighting foundBird = dataSnapshot.getValue(BirdSighting.class);
                    String findSpecies = foundBird.species;
                    String findSighter = foundBird.sighter;
                    speciesTextView.setText(findSpecies);
                    sighterTextView.setText(findSighter);
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    BirdSighting foundBird = dataSnapshot.getValue(BirdSighting.class);
                    String findSpecies = foundBird.species;
                    String findSighter = foundBird.sighter;
                    speciesTextView.setText(findSpecies);
                    sighterTextView.setText(findSighter);
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    BirdSighting foundBird = dataSnapshot.getValue(BirdSighting.class);
                    String findSpecies = foundBird.species;
                    String findSighter = foundBird.sighter;
                    speciesTextView.setText(findSpecies);
                    sighterTextView.setText(findSighter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
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
        if(item.getItemId() == R.id.item_search){
            Toast.makeText(this, "Already in Search Screen", Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.item_report){
            startActivity(new Intent(SearchActivity.this, ReportActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
