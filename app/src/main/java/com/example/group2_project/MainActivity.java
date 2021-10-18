package com.example.group2_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardView medicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medicine=findViewById(R.id.btnMedicine);
        medicine.setOnClickListener(v -> medicineReminder(v));
    }

    public void medicineReminder(View v){
        Intent explicit = new Intent(MainActivity.this, MainPage.class);
        startActivity(explicit );
    }
}