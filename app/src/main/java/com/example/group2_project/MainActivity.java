package com.example.group2_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardView medicine, faq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medicine = findViewById(R.id.btnMedicine);
        faq = findViewById(R.id.btn_faq);

        medicine.setOnClickListener(v -> medicineReminder(v));
        faq.setOnClickListener(v -> faq(v));
    }

    public void medicineReminder(View v){
        Intent explicit = new Intent(MainActivity.this, MainPage.class);
        startActivity(explicit );
    }

    public void faq(View v){
        Intent explicit = new Intent(MainActivity.this, FaqActivity.class);
        startActivity(explicit );
    }
}