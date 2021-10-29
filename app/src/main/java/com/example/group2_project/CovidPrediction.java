package com.example.group2_project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CovidPrediction extends AppCompatActivity {
    static int countIndex=0;
    Button btn_predict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_prediction);
        btn_predict=findViewById(R.id.btnPredict);
    }
    public void predict_method(View v){


    }

}