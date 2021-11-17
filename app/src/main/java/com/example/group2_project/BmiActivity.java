package com.example.group2_project;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BmiActivity extends AppCompatActivity {

    EditText height,weight;
    Button calculateBtn, dietChartBtn;
    TextView bmiTV,linkTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        height = findViewById(R.id.etHeight);
        weight = findViewById(R.id.etWeight);
        calculateBtn = findViewById(R.id.btnCalculate);
        bmiTV = findViewById(R.id.tvResult);
        linkTV = findViewById(R.id.textViewYouLink);
        linkTV.setMovementMethod(LinkMovementMethod.getInstance());
       // dietChartBtn = findViewById(R.id.btn_diet_chart);
        calculateBtn.setOnClickListener(v -> {
            if (!height.getText().toString().isEmpty() && !weight.getText().toString().isEmpty())
            {
                hideKeyboard(this);
                calculateBMI();
            }
            else
            {
                hideKeyboard(this);
                Toast.makeText(BmiActivity.this, "Please Enter All Info..", Toast.LENGTH_SHORT).show();
            }
        });

//        dietChartBtn.setOnClickListener(v -> {
//            openDietChart();
//        });
    }
    private void calculateBMI()
    {
        double weight1 = Double.parseDouble(weight.getText().toString());
        weight1 *= 2.205;
        double height1 = Double.parseDouble(height.getText().toString());
        double result = (703*weight1/(height1*height1));
        bmiTV.setText("your BMI: "+String.format("%.2f", result));



    }
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
     
        View view = activity.getCurrentFocus();
       
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void openDietChart() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.euro.who.int/en/health-topics/health-emergencies/coronavirus-covid-19/publications-and-technical-guidance/food-and-nutrition-tips-during-self-quarantine"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.android.chrome");
        startActivity(intent);
    }
}

