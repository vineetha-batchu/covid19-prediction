package com.example.group2_project;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class RemindersActivity extends AppCompatActivity {

    private FloatingActionButton add;
    private Dialog dialog;
    private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private AdapterReminders adapter;
    private List<Reminders> temp;
    private TextView empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        appDatabase = AppDatabase.geAppdatabase(RemindersActivity.this);

        add = findViewById(R.id.floatingButton);
        empty = findViewById(R.id.empty);

        add.setOnClickListener(v -> addReminder());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RemindersActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        setItemsInRecyclerView();

    }

    public void addReminder(){

        dialog = new Dialog(RemindersActivity.this);
        dialog.setContentView(R.layout.floating_popup);

        final TextView textView = dialog.findViewById(R.id.date);
        Button select,add;
        select = dialog.findViewById(R.id.selectDate);
        add = dialog.findViewById(R.id.addButton);
        final EditText message = dialog.findViewById(R.id.message);


        final Calendar newCalender = Calendar.getInstance();
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(RemindersActivity.this, (view, year, month, dayOfMonth) -> {

                    final Calendar newDate = Calendar.getInstance();
                    Calendar newTime = Calendar.getInstance();
                    TimePickerDialog time = new TimePickerDialog(RemindersActivity.this, (view1, hourOfDay, minute) -> {

                        newDate.set(year,month,dayOfMonth,hourOfDay,minute,0);
                        Calendar tem = Calendar.getInstance();
                        Log.w("TIME", System.currentTimeMillis()+"");
                        if(newDate.getTimeInMillis()-tem.getTimeInMillis()>0)
                            textView.setText(newDate.getTime().toString());
                        else
                            Toast.makeText(RemindersActivity.this,"Invalid time", Toast.LENGTH_SHORT).show();

                    },newTime.get(Calendar.HOUR_OF_DAY),newTime.get(Calendar.MINUTE),true);
                    time.show();

                },newCalender.get(Calendar.YEAR),newCalender.get(Calendar.MONTH),newCalender.get(Calendar.DAY_OF_MONTH));

                dialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dialog.show();

            }
        });


        add.setOnClickListener(v -> {

            RoomDAO roomDAO = appDatabase.getRoomDAO();
            Reminders reminders = new Reminders();
            reminders.setMessage(message.getText().toString().trim());
            Date remind = new Date(textView.getText().toString().trim());
            reminders.setRemindDate(remind);
            roomDAO.Insert(reminders);
            List<Reminders> l = roomDAO.getAll();
            reminders = l.get(l.size()-1);
            Log.e("ID",reminders.getId()+"");

            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:30"));
            calendar.setTime(remind);
            calendar.set(Calendar.SECOND,0);
            Intent intent = new Intent(RemindersActivity.this,NotifierAlarm.class);
            intent.putExtra("Message",reminders.getMessage());
            intent.putExtra("RemindDate",reminders.getRemindDate().toString());
            intent.putExtra("id",reminders.getId());
            PendingIntent intent1 = PendingIntent.getBroadcast(RemindersActivity.this,reminders.getId(),intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),intent1);

            Toast.makeText(RemindersActivity.this,"Inserted Successfully", Toast.LENGTH_SHORT).show();
            setItemsInRecyclerView();
            AppDatabase.destroyInstance();
            dialog.dismiss();

        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    public void setItemsInRecyclerView(){

        RoomDAO dao = appDatabase.getRoomDAO();
        temp = dao.orderThetable();
        if(temp.size()>0) {
            empty.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        adapter = new AdapterReminders(temp);
        recyclerView.setAdapter(adapter);

    }
}
