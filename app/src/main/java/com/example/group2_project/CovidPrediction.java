package com.example.group2_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class CovidPrediction extends AppCompatActivity {
    String age, temp, bp, oxy_levels, cret_levels, contaced_person, chron;
    int age_int, systolic, diastolic;
    int bp_sys_count, bp_diastolic_count;
    int age_count, temp_count, bp_count, cough_count, headache_count, total_count;
    double temp_double, oxy_double, cret_double;
    RadioGroup rg_gender, rg_cough, rg_headache, rg_chronic_diseases, rg_contaced_covid_p;
    RadioButton rb_gender_female, rb_gender_male, rb_gender_other, rb_cough_high, rb_cough_mod, rb_cough_low, rb_headache_high, rb_headache_low, rb_headache_mod, rb_chro_yes, rb_chro_no, rb_cp_yes, rb_cp_no;
    Button btn_predict_covid;
    TextInputEditText tie_age, tie_temperature, tie_bp, tie_oxygen_levels, tie_cretinine_levels;
    TextView tv_predict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_prediction);
        btn_predict_covid = findViewById(R.id.btn_predict_covid);
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
        rb_gender_other = findViewById(R.id.rb_otherGender);

        rb_cough_low = findViewById(R.id.rb_cough_low);
        rb_cough_high = findViewById(R.id.rb_cough_high);
        rb_cough_mod = findViewById(R.id.rb_cough_mod);

        rb_headache_low = findViewById(R.id.rb_headache_low);
        rb_headache_high = findViewById(R.id.rb_headache_high);
        rb_headache_mod = findViewById(R.id.rb_headache_mod);
        rb_chro_no = findViewById(R.id.rb_chronic_diseases_no);
        rb_chro_yes = findViewById(R.id.rb_chronic_diseases_yes);

        rb_cp_no = findViewById(R.id.rb_contaced_covid_p_no);
        rb_cp_yes = findViewById(R.id.rb_contaced_covid_p_yes);

        tv_predict = findViewById(R.id.tv_predict);


    }

    public void setCountValuesZero() {
        age_count = 0;
        temp_count = 0;
        bp_count = 0;
        cough_count = 0;
        headache_count = 0;
        total_count = 0;

    }

    public void predict_method(View v) {
        setCountValuesZero();

        if (onRadioAllCheck(v) && onallTVCheck(v)) {

            age = tie_age.getText().toString();
            temp = tie_temperature.getText().toString();
            bp = tie_bp.getText().toString();
            oxy_levels = tie_oxygen_levels.getText().toString();
            cret_levels = tie_cretinine_levels.getText().toString();
            //converting form text to int/double
            age_int = Integer.parseInt(age);
            temp_double = Double.parseDouble(temp);
            String[] arrOfbp = bp.split("/");
            systolic = Integer.parseInt(arrOfbp[0]);
            diastolic = Integer.parseInt(arrOfbp[1]);
            oxy_double = Double.parseDouble(oxy_levels);
            cret_double = Double.parseDouble(cret_levels);
            getAllradioValues(v);
            predictResult(v);


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
        oxy_levels = tie_oxygen_levels.getText().toString();
        cret_levels = tie_cretinine_levels.getText().toString();
        if (age.length() == 0 || temp.length() == 0 || bp.length() == 0 || oxy_levels.length() == 0 || cret_levels.length() == 0) {
            Toast.makeText(getApplicationContext(), "Please Fill all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void getAllradioValues(View v) {

        if (rb_chro_yes.isChecked()) {
            chron = "yes";
        } else {
            chron = "no";
        }
        if (rb_cp_yes.isChecked()) {
            contaced_person = "yes";
        } else {
            contaced_person = "no";
        }


    }

    public void predictResult(View v) {
        if (contaced_person.equalsIgnoreCase("yes") || cret_double > 5 || oxy_double <= 60 || chron.equalsIgnoreCase("yes")) {

            tv_predict.setText("Covid Probability: High");
        } else {
            //calculating count for age
            if (age_int > 60) {
                age_count = 100;
            } else if (age_int > 15 && age_int <= 60)
                age_count = 50;
            else
                age_count = 10;

            //calculating count for temperature
            if (temp_double > 102)
                temp_count = 100;
            else if (temp_double > 99 && temp_double <= 102)
                temp_count = 50;
            else
                temp_count = 10;

            //calculating count for bp
            calcBp();

            //calculating count for cough
            if (rb_cough_high.isChecked())
                cough_count = 100;
            else if (rb_cough_mod.isChecked())
                cough_count = 50;
            else
                cough_count = 10;

            //calculating count for head ache
            if (rb_headache_high.isChecked())
                headache_count = 100;
            else if (rb_headache_mod.isChecked())
                headache_count = 50;
            else
                headache_count = 10;
            //getting Final result
            tv_predict.setTextColor(this.getResources().getColor(R.color.design_default_color_primary_variant));
            tv_predict.setText(finalResult());


        }

    }

    public void calcBp() {
        //bp systolic
        if (systolic >= 140) {
            bp_sys_count = 100;
        } else if (systolic >= 130 && systolic <= 139) {
            bp_sys_count = 50;
        } else {
            bp_sys_count = 10;
        }
        //bp diastolic
        if (diastolic >= 90)
            bp_diastolic_count = 100;
        else if (diastolic > 80 && diastolic <= 89)
            bp_diastolic_count = 50;
        else
            bp_diastolic_count = 10;

        //Setting bp Count based on Systolic and Diastolic

        int bp_avg = (bp_diastolic_count + bp_sys_count) / 2;

        if (bp_avg >= 75)
            bp_count = 100;
        else if (bp_avg >= 50 && bp_avg < 75)
            bp_count = 50;
        else
            bp_count = 10;
    }

    public String finalResult() {
        String final_result;
        double result = (age_count + temp_count + bp_count + cough_count + headache_count) / 5.0;
        if (result >= 75)
            return "Covid Probability: High";
        else if (result >= 50 && result < 75)
            return "Covid Probability: Moderate";
        else
            return "Covid Probability: Low";

    }

}