package com.example.group2_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class CovidPrediction extends AppCompatActivity {
    static int countIndex = 0, countrg_check = 0;
    RadioGroup rg_gender, rg_cough, rg_headache, rg_chronic_diseases, rg_contaced_covid_p;
    RadioButton rb_gender_female, rb_gender_male;
    Button btn_predict_covid;
    boolean checked_gender, checked_cough, checked_headache, checked_chronic, checked_contacted;
    TextInputEditText tie_age, tie_temperature, tie_bp, tie_oxygen_levels, tie_cretinine_levels;
    TextView tv_predict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_prediction);
        btn_predict_covid = findViewById(R.id.btn_predict_covid);
        btn_predict_covid.setEnabled(false);
        tie_age = findViewById(R.id.tie_age);
        tie_temperature = findViewById(R.id.tie_temperature);
        tie_bp = findViewById(R.id.tie_bp);
        tie_oxygen_levels = findViewById(R.id.tie_oxygen_levels);
        tie_cretinine_levels = findViewById(R.id.tie_cretinine_levels);
        rg_gender = findViewById(R.id.rg_gender);
        rg_cough = findViewById(R.id.rg_cough);
        rg_headache = findViewById(R.id.rg_headache);
        rg_chronic_diseases = findViewById(R.id.rg_chronic_diseases);
        rg_contaced_covid_p = findViewById(R.id.rg_contaced_covid_p);
        tv_predict = findViewById(R.id.tv_predict);
        rb_gender_female = findViewById(R.id.rb_female);
        rb_gender_male = findViewById(R.id.rb_male);

    }

    public void predict_method(View v) {
//        checked_gender=((RadioButton) v).isChecked();

    }

    public void onRadioAllCheck(View v) {
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.rb_male | R.id.rb_female:
                checked_gender = true;
                eachRBCheck();
                break;

            case R.id.rb_cough_yes | R.id.rb_cough_no:
                checked_cough = true;
                eachRBCheck();
                break;

            case R.id.rb_chronic_diseases_yes | R.id.rb_chronic_diseases_no:
                checked_chronic = true;
                eachRBCheck();
                break;

            case R.id.rb_contaced_covid_p_yes | R.id.rb_contaced_covid_p_no:
                checked_contacted = true;
                eachRBCheck();
                break;

//            case R.id.rb_headache_yes | R.id.rb_headache_no:
//                checked_headache = true;
//                eachRBCheck();
//                break;

        }

    }

    public void eachRBCheck() {

        if (checked_contacted && checked_chronic && checked_gender && checked_cough ) {
            btn_predict_covid.setEnabled(true);
            // tv_predict.setEnabled(true);

        } else {
            btn_predict_covid.setEnabled(false);
            // tv_predict.setEnabled(false);
        }

    }

}