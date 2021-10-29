package com.example.group2_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardView medicine, faq, bmi, vaccination, prediction, pharmacyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medicine = findViewById(R.id.btnMedicine);
        faq = findViewById(R.id.btn_faq);
        bmi = findViewById(R.id.btnBmi);
        vaccination = findViewById(R.id.btnVaccine);
        prediction = findViewById(R.id.btnPredict);
        pharmacyList = findViewById(R.id.btnPharmacy);

        medicine.setOnClickListener(v -> medicineReminder(v));
        faq.setOnClickListener(v -> faq(v));
        bmi.setOnClickListener(v -> bmi(v));
        vaccination.setOnClickListener(v -> vaccination(v));
        prediction.setOnClickListener(v -> prediction(v));
        pharmacyList.setOnClickListener(v -> pharmacyList(v));
    }

    public void medicineReminder(View v){
        Intent explicit = new Intent(MainActivity.this, RemindersActivity.class);
        startActivity(explicit );
    }

    public void faq(View v){
        Intent explicit = new Intent(MainActivity.this, FaqActivity.class);
        startActivity(explicit );
    }

    public void bmi(View v){
        Intent explicit = new Intent(MainActivity.this, BmiActivity.class);
        startActivity(explicit );
    }

    public void vaccination(View v){
        Intent explicit = new Intent(MainActivity.this, VaccinationActivity.class);
        startActivity(explicit );
    }

    public void prediction(View v){
        Intent explicit = new Intent(MainActivity.this, CovidPrediction.class);
        startActivity(explicit );
    }

    public void pharmacyList(View v){
        Intent explicit = new Intent(MainActivity.this, PharmacyListActivity.class);
        startActivity(explicit );
    }
}