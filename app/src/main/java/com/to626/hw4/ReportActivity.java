package com.to626.hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ReportActivity extends AppCompatActivity  implements View.OnClickListener{
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

    }

    @Override
    public void onClick(View view) {
        String bird = birdEditText.getText().toString();
        String zipcode = zipcodeEditText.getText().toString();
        String name = nameEditText.getText().toString();


    }
}
