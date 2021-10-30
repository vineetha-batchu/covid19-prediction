package com.example.group2_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class CovidPrediction extends AppCompatActivity {
    String age, temp, bp, oxy, cret, gender, cough, ache, chron, cp;
    int age_int, bp_up, bp_low;
    double temp_double, oxy_double, cret_double;
    static int countIndex = 0, countrg_check = 0;
    RadioGroup rg_gender, rg_cough, rg_headache, rg_chronic_diseases, rg_contaced_covid_p;
    RadioButton rb_gender_female, rb_gender_male, rb_cough_yes, rb_cough_no, rb_ache_yes, rb_ache_no, rb_chro_yes, rb_chro_no, rb_cp_yes, rb_cp_no;
    Button btn_predict_covid;
    boolean checked_gender, checked_cough, checked_headache, checked_chronic, checked_contacted;
    TextInputEditText tie_age, tie_temperature, tie_bp, tie_oxygen_levels, tie_cretinine_levels;
    TextView tv_predict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_prediction);
        btn_predict_covid = findViewById(R.id.btn_predict_covid);
        //btn_predict_covid.setEnabled(false);
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

        rb_cough_no = findViewById(R.id.rb_cough_no);
        rb_cough_yes = findViewById(R.id.rb_cough_yes);

        rb_ache_no = findViewById(R.id.rb_headache_no);
        rb_ache_yes = findViewById(R.id.rb_headache_yes);

        rb_chro_no = findViewById(R.id.rb_chronic_diseases_no);
        rb_chro_yes = findViewById(R.id.rb_chronic_diseases_yes);

        rb_cp_no = findViewById(R.id.rb_contaced_covid_p_no);
        rb_cp_yes = findViewById(R.id.rb_contaced_covid_p_yes);

        tv_predict=findViewById(R.id.tv_predict);


    }

    public void predict_method(View v) {

        if (onRadioAllCheck(v) && onallTVCheck(v)) {

            age = tie_age.getText().toString();
            temp = tie_temperature.getText().toString();
            bp = tie_bp.getText().toString();
            oxy = tie_oxygen_levels.getText().toString();
            cret = tie_cretinine_levels.getText().toString();
            //converting form text to int/double
            age_int = Integer.parseInt(age);
            temp_double = Double.parseDouble(temp);
            String[] arrOfbp = bp.split("/");
            bp_up = Integer.parseInt(arrOfbp[0]);
            bp_low = Integer.parseInt(arrOfbp[1]);
            oxy_double = Double.parseDouble(oxy);
            cret_double = Double.parseDouble(cret);
            getAllradioValues(v);
            if(cp.equalsIgnoreCase("yes"))
            {   countIndex=45;
                tv_predict.setText("High");
            }
            checkbtn(v);
            if(countIndex<=25)
                tv_predict.setText("Low");
            else if(countIndex>25 && countIndex<=35)
                tv_predict.setText("Moderate");
            else
                tv_predict.setText("High");



        }


    }

    public boolean onRadioAllCheck(View v) {
        if (rg_gender.getCheckedRadioButtonId() == -1 || rg_cough.getCheckedRadioButtonId() == -1 || rg_headache.getCheckedRadioButtonId() == -1 || rg_contaced_covid_p.getCheckedRadioButtonId() == -1 || rg_chronic_diseases.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), "Please Fill all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean onallTVCheck(View v) {
        age = tie_age.getText().toString();
        temp = tie_temperature.getText().toString();
        bp = tie_bp.getText().toString();
        oxy = tie_oxygen_levels.getText().toString();
        cret = tie_cretinine_levels.getText().toString();
        if (age.length() == 0 || temp.length() == 0 || bp.length() == 0 || oxy.length() == 0 || cret.length() == 0) {
            Toast.makeText(getApplicationContext(), "Please Fill all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void getAllradioValues(View v) {
        if (rb_gender_female.isChecked()) {
            gender = "female";
        } else {
            gender = "male";
        }
        if (rb_cough_yes.isChecked()) {
            cough = "yes";
        } else {
            cough = "no";
        }

        if (rb_ache_yes.isChecked()) {
            ache = "yes";
        } else {
            ache = "no";
        }
        if (rb_chro_yes.isChecked()) {
            chron = "yes";
        } else {
            chron = "no";
        }
        if (rb_cp_yes.isChecked()) {
            cp = "yes";
        } else {
            cp = "no";
        }


    }
    public void checkbtn(View v){

        if(oxy_double<90)
            countIndex+=10;
        else if(oxy_double>=90 && oxy_double<95)
            countIndex+=7;
        else
            countIndex+=5;
        //cret
        if(cret_double>=5)
            countIndex+=10;
        else
            countIndex+=5;

        //chron
        if(chron.equalsIgnoreCase("yes"))
            countIndex+=10;
        else if(chron.equalsIgnoreCase("no"))
            countIndex+=5;
        //bp
        if(bp_up>130 && bp_up<139)
            countIndex+=7;
        else
            countIndex+=5;
        if(bp_low>80 && bp_low<89)
            countIndex+=7;
        else
            countIndex+=5;

        //age
        if(age_int<10 || age_int>55)
            countIndex+=7;
        else
            countIndex+=5;


    }
}