package co.edu.udea.compumovil.gr07_20211.lab1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class PersonalDataActivity extends AppCompatActivity {

    Button btnSiguiente;
    Button btnSelectDate;
    DatePickerDialog datePickerDialog;

    TextView dateTextView;
    TextInputEditText nameEditText;
    TextInputEditText lastNameEditText;
    RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);

        nameEditText = findViewById(R.id.nameEditTextt);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnSelectDate = findViewById(R.id.datePickerButton);
        dateTextView = findViewById(R.id.selectedDateTextView);

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                createDatePicker(v);
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactDataActivity();
            }
        });

    }


    public void createDatePicker(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(PersonalDataActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        dateTextView.setText(day + "/" + month + "/" + year);
                    }
                }, 1, 1, 1);
        datePickerDialog.show();
    }

    public String getGender(View view) {
        if (genderRadioGroup.getCheckedRadioButtonId() == R.id.maleRadioButton) {
            return "Male";
        }
        if (genderRadioGroup.getCheckedRadioButtonId() == R.id.femaleRadioButton) {
            return "Female";
        }
        if (genderRadioGroup.getCheckedRadioButtonId() == R.id.maleRadioButton) {
            return "Combat Chopper";
        }
        return "Non selected";
    }

    private void contactDataActivity() {
        Intent startActivity = new Intent(PersonalDataActivity.this, ContactDataActivity.class);
        startActivity(startActivity);
    }
}