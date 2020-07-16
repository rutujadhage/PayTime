package com.example.bills2;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



public class AddTask extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private int notificationid=1;
    private ImageButton button_delete;
    EditText namefinal1;
    EditText datefinal1;
    Uri taskUri;



    TaskDBHelper mydb;
    DatePickerDialog dpd;
    int startYear = 0, startMonth = 0, startDay = 0;
    String dateFinal;
    String nameFinal;


    Intent intent;
    Boolean isUpdate;
    Boolean isDelete;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_add_new);
        button_delete= (ImageButton) findViewById(R.id.delete_task);
        String valid_until = "10/10/2019";


        mydb = new TaskDBHelper(getApplicationContext());
        intent = getIntent();
        isUpdate = intent.getBooleanExtra("isUpdate", false);
        isDelete=intent.getBooleanExtra("isDelete", false);



        dateFinal = todayDateString();
        Date your_date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(your_date);
        startYear = cal.get(Calendar.YEAR);
        startMonth = cal.get(Calendar.MONTH);
        startDay = cal.get(Calendar.DAY_OF_MONTH);

        if (isUpdate) {
            init_update();
        }
        DeleteData();


    }

    public void DeleteData(){
        EditText task_name = (EditText) findViewById(R.id.task_name);
        id = intent.getStringExtra("id");
        EditText task_date = (EditText) findViewById(R.id.task_date);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows=mydb.deleteData(id);
                if(deletedRows>0) {
                    Toast.makeText(AddTask.this, "Reminder deleted", Toast.LENGTH_LONG).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(AddTask.this, TaskHome.class);
                            startActivity(intent);
                            finish();

                        }
                    }, 0000);
                }

                else
                    Toast.makeText(AddTask.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }



    public void init_update() {
        id = intent.getStringExtra("id");
        TextView toolbar_task_add_title = (TextView) findViewById(R.id.toolbar_task_add_title);
        EditText task_name = (EditText) findViewById(R.id.task_name);
        EditText task_date = (EditText) findViewById(R.id.task_date);
        toolbar_task_add_title.setText("Update");
        Cursor task = mydb.getDataSpecific(id);
        if (task != null) {
            task.moveToFirst();

            task_name.setText(task.getString(1).toString());
            Calendar cal = Function.Epoch2Calender(task.getString(2).toString());
            startYear = cal.get(Calendar.YEAR);
            startMonth = cal.get(Calendar.MONTH);
            startDay = cal.get(Calendar.DAY_OF_MONTH);
            task_date.setText(Function.Epoch2DateString(task.getString(2).toString(), "dd/MM/yyyy"));

        }

    }

    public String todayDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy", Locale.getDefault());

        return dateFormat.toString();


    }


    public void closeAddTask(View v) {
        finish();
    }


    public void doneAddTask(View v) throws ParseException {
        int errorStep = 0;
        EditText task_name = (EditText) findViewById(R.id.task_name);
        EditText task_date = (EditText) findViewById(R.id.task_date);
        nameFinal = task_name.getText().toString();
        dateFinal = task_date.getText().toString();
        String invalid = "20/10/2019";
        SimpleDateFormat sdf= new SimpleDateFormat("dd/mm/yyyy");
        Date today_date=sdf.parse("20/10/2019");
        Date enter_date=sdf.parse(dateFinal);
        System.out.println(today_date);
        if(today_date.after(enter_date))
        {
            errorStep++;
            task_date.setError("Error");
            //System.out.println(today_date);
        }




        if (nameFinal.trim().length() < 1) {
            errorStep++;
            task_name.setError("Enter a reminder.");
        }

        if (dateFinal.trim().length() < 4) {
            errorStep++;
            task_date.setError("Provide a specific date");
        }

        if(dateFinal.trim().equals(invalid))
        {
            errorStep++;
            task_date.setError("Error");

        }





        if (errorStep == 0) {
            if (isUpdate) {
                mydb.updateContact(id, nameFinal, dateFinal);
                Toast.makeText(getApplicationContext(), "Task Updated.", Toast.LENGTH_SHORT).show();
            } else {
                // new AlarmScheduler().scheduleAlarm(getApplicationContext(),dateFinal,taskUri);
                mydb.insertContact(nameFinal, dateFinal);
                Toast.makeText(getApplicationContext(), "Task Added.", Toast.LENGTH_SHORT).show();

            }

            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }

    }

    public void delete_reminder(View v)
    {
        namefinal1= (EditText) findViewById(R.id.task_name);
        datefinal1=(EditText) findViewById(R.id.task_date);
        namefinal1.setText("");
        datefinal1=null;
        Toast.makeText(getApplicationContext(), "Reminder deleted", Toast.LENGTH_SHORT).show();
        if (taskUri != null) {

            int rowsDeleted = getContentResolver().delete(taskUri, null, null);

            if (rowsDeleted == 0) {

                Toast.makeText(this, getString(R.string.deleting_task_failed),
                        Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, getString(R.string.deleting_task_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }

        // finish();
        // datefinal1.setText(Function.Epoch2DateString(datefinal1.getText().toString(), "dd/MM/yyyy"));



    }

    @Override
    public void onResume() {
        super.onResume();
        dpd = (DatePickerDialog) getFragmentManager().findFragmentByTag("startDatepickerdialog");
        if (dpd != null) dpd.setOnDateSetListener(this);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        startYear = year;
        startMonth = monthOfYear;
        startDay = dayOfMonth;
        int monthAddOne = startMonth + 1;
        String date = (startDay < 10 ? "0" + startDay : "" + startDay) + "/" +
                (monthAddOne < 10 ? "0" + monthAddOne : "" + monthAddOne) + "/" +
                startYear;
        EditText task_date = (EditText) findViewById(R.id.task_date);
        task_date.setText(date);
    }



    public void showStartDatePicker(View v) {
        dpd = DatePickerDialog.newInstance(AddTask.this, startYear, startMonth, startDay);
        dpd.setOnDateSetListener(this);
        dpd.show(getFragmentManager(), "startDatepickerdialog");
    }

}