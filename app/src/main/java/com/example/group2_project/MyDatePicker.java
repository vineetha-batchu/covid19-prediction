package com.example.group2_project;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDatePicker extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar mCalendar = Calendar.getInstance();
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int dayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog mDatePickerDialogFragment=null;

        try {
             mDatePickerDialogFragment = new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener)
                    getActivity(), year, month, dayOfMonth);
            String myDate = "2021/03/01 18:10:45";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = sdf.parse(myDate);
            long millis = date.getTime();
            mDatePickerDialogFragment.getDatePicker().setMinDate(millis);
        }
        catch (Exception e){

        }
            return mDatePickerDialogFragment;


    }
}
