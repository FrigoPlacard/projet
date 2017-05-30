package com.example.ensai.frigomalin;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.ensai.frigomalin.metier.Element;

import java.util.Calendar;

public class Ajout_element extends AppCompatActivity implements View.OnClickListener {
    Button bDate;
    EditText txtDate;
    int year_x,month_x,day_x;
    static final int DIALOG_ID=0;

    String[] mois={"janv","févr","mars","avr","mai","juin","juil","août","sept","oct","nov","dec"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final Calendar c = Calendar.getInstance();
        day_x = c.get(Calendar.DAY_OF_MONTH);
        month_x = c.get(Calendar.MONTH);
        year_x = c.get(Calendar.YEAR);

        setContentView(R.layout.activity_ajout_element);


        showDialogOnButtonClick();
    }



    public void showDialogOnButtonClick(){
        bDate = (Button)findViewById(R.id.bDate);
        txtDate = (EditText)findViewById(R.id.txtDate);
        txtDate.setText(day_x+" "+mois[month_x]+" "+year_x);


    }


    public static void ajouter(View v){
        Element element = new Element();
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x=year;
            month_x=month;
            day_x=dayOfMonth;
            txtDate.setText(day_x+" "+mois[month_x]+" "+year_x);


        }
    };
    @Override
    public void onClick(View v) {
        showDialog(DIALOG_ID);
    }

}
