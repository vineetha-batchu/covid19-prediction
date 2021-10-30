package com.example.group2_project;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class VaccinationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView displayTV, tvFirstDose, tvSecondDose, tvNextDosageHeader, tvNextDosageDate;
    private Dialog dialog;

    RadioGroup rgFirstDose, rgSecondDose;
    RadioButton rbModernaVaccine, rbJJVaccine;
    TextInputLayout tilFirstDose, tilSecondDose;
    TextInputEditText tieFirstDose, tieSecondDose;
    boolean jjSelected, rbSecondDoseSelectedYes, isFirstDoseDateSelected;
    String firstDoseTimeStamp;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);


        tvFirstDose = findViewById(R.id.tv_first_dose);
        tvSecondDose = findViewById(R.id.tv_seconddose);

        rgFirstDose = findViewById(R.id.rg_firstdose);
        rgSecondDose = findViewById(R.id.rg_seconddose);

        rbModernaVaccine = findViewById(R.id.rb_vaccine_moderna);
        rbJJVaccine = findViewById(R.id.rb_vaccine_jj);

        tilFirstDose = findViewById(R.id.til_firstdose);
        tilSecondDose = findViewById(R.id.til_seconddose);

        tvNextDosageHeader = findViewById(R.id.tv_next_dosage_header);
        tvNextDosageDate = findViewById(R.id.tv_next_dosage_date);

        tieFirstDose = findViewById(R.id.tie_firstdose);
        tieFirstDose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new MyDatePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            }
        });

        tieSecondDose = findViewById(R.id.tie_second_dose);
        tieSecondDose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new MyDatePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            }
        });

        initSharedPreferences();
    }

    private void initSharedPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        myEdit = sharedPreferences.edit();
        myEdit.clear().commit();
    }

    public void onRadioButtonClicked(View view) {
        // Is the view now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which RadioButton was clicked
        switch (view.getId()) {
            case R.id.rb_vaccine_moderna:
                Log.d("rb_vaccine_moderna", "rb_vaccine_moderna");
                if (checked) {
                    Log.d("rb_vaccine_moderna", "rb_vaccine_moderna");

                    jjSelected = false;

                    tvFirstDose.setVisibility(View.VISIBLE);
                    rgFirstDose.setVisibility(View.VISIBLE);
                    tvSecondDose.setVisibility(View.GONE);
                    rgSecondDose.setVisibility(View.GONE);
                    tilFirstDose.setVisibility(View.GONE);
                    tilSecondDose.setVisibility(View.GONE);

                    rgFirstDose.clearCheck();
                    rgSecondDose.clearCheck();
                    tieFirstDose.setText("");
                    tieSecondDose.setText("");
                    tvNextDosageDate.setText("");
                    rbSecondDoseSelectedYes = false;
                }
                break;

            case R.id.rb_vaccine_pfizer:
                Log.d("rb_vaccine_pfizer", "rb_vaccine_pfizer");
                if (checked) {
                    Log.d("rb_vaccine_pfizer", "rb_vaccine_pfizer");

                    jjSelected = false;

                    tvFirstDose.setVisibility(View.VISIBLE);
                    rgFirstDose.setVisibility(View.VISIBLE);
                    tvSecondDose.setVisibility(View.GONE);
                    rgSecondDose.setVisibility(View.GONE);
                    tilFirstDose.setVisibility(View.GONE);
                    tilSecondDose.setVisibility(View.GONE);

                    rgFirstDose.clearCheck();
                    rgSecondDose.clearCheck();
                    tieFirstDose.setText("");
                    tieSecondDose.setText("");
                    tvNextDosageDate.setText("");
                    rbSecondDoseSelectedYes = false;
                }
                break;

            case R.id.rb_vaccine_jj:
                if (checked) {
                    jjSelected = true;

                    tvFirstDose.setVisibility(View.VISIBLE);
                    rgFirstDose.setVisibility(View.VISIBLE);
                    tvSecondDose.setVisibility(View.GONE);
                    rgSecondDose.setVisibility(View.GONE);
                    tilFirstDose.setVisibility(View.VISIBLE);
                    tilSecondDose.setVisibility(View.GONE);

                    rgFirstDose.clearCheck();
                    rgSecondDose.clearCheck();
                    tieFirstDose.setText("");
                    tieSecondDose.setText("");
                    tvNextDosageDate.setText("");
                    rbSecondDoseSelectedYes = false;
                }
                break;

            case R.id.rb_firstdose_yes:
                if (checked && jjSelected) {
                    tvSecondDose.setVisibility(View.GONE);
                    rgSecondDose.setVisibility(View.GONE);
                    tilFirstDose.setVisibility(View.VISIBLE);
                    tilSecondDose.setVisibility(View.GONE);

                } else {
                    tvSecondDose.setVisibility(View.VISIBLE);
                    rgSecondDose.setVisibility(View.VISIBLE);
                    tilFirstDose.setVisibility(View.VISIBLE);
                    tilSecondDose.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.rb_firstdose_no:
                tvSecondDose.setVisibility(View.GONE);
                rgSecondDose.setVisibility(View.GONE);
                tilFirstDose.setVisibility(View.GONE);
                tilSecondDose.setVisibility(View.GONE);
                break;

            case R.id.rb_seconddose_yes:
//                rbSecondDoseSelectedYes = true;
                tilSecondDose.setVisibility(View.VISIBLE);
                break;

            case R.id.rb_seconddose_no:
                rbSecondDoseSelectedYes = false;
                tilSecondDose.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy");
        String timestamp = simpleDate.format(mCalendar.getTime());

        setSelectedDateToViews(timestamp);
        doNextDoseDateCalculation(mCalendar, simpleDate);
    }

    private void setSelectedDateToViews(String timestamp) {

        if (!sharedPreferences.getBoolean("isFirstDoseDateSelected", false)) {
            myEdit.putString("firstDoseDate", timestamp);
            tieFirstDose.setText(timestamp);
            myEdit.putBoolean("isFirstDoseDateSelected", true);

        } else {
            myEdit.putString("secondDoseDate", timestamp);
            tieSecondDose.setText(timestamp);
            rbSecondDoseSelectedYes = true;
        }

        myEdit.commit();
    }

    private void doNextDoseDateCalculation(Calendar mCalendar, SimpleDateFormat simpleDate) {
        if (rbSecondDoseSelectedYes) {
            mCalendar.add(Calendar.YEAR, 1);
            tvNextDosageDate.setText("You will have your boost dose in next year :" + simpleDate.format(mCalendar.getTime()));

        } else {
            String dtStart = sharedPreferences.getString("firstDoseDate", "");
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

            Date date = new Date();

            try {
                date = format.parse(dtStart);
                System.out.println(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            mCalendar.setTime(date);
            if (rbModernaVaccine.isChecked()) {
                mCalendar.add(Calendar.DAY_OF_MONTH, 28);
            } else {
                mCalendar.add(Calendar.DAY_OF_MONTH, 21);
            }

            String timestampSecondDose = simpleDate.format(mCalendar.getTime());
            tvNextDosageDate.setText(timestampSecondDose);
        }
    }
}
