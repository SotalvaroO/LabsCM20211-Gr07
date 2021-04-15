package co.edu.udea.compumovil.gr07_20211.lab1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PersonalDataActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button btnSiguiente;
    Button btnSelectDate;

    TextView dateTextView;
    TextInputEditText nameEditText;
    TextInputEditText lastNameEditText;
    RadioGroup genderRadioGroup;
    Spinner schoolarshipSpinner;
    int age = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);

        nameEditText = findViewById(R.id.nameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        btnSiguiente = findViewById(R.id.nextButton);
        btnSelectDate = findViewById(R.id.datePickerButton);
        dateTextView = findViewById(R.id.selectedDateTextView);
        schoolarshipSpinner = findViewById(R.id.spinnerSchoolarship);

        String nombre = nameEditText.getText().toString();
        Log.d("nombre", "hola" + nombre);
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String nombre = nameEditText.getText().toString();
                Log.d("nombre", "hola" + nombre);
            }
        });

        List<String> categories = new ArrayList<>();
        categories.add(0, getResources().getString(R.string.schoolarship));
        categories.add(getResources().getString(R.string.elementary_school));
        categories.add(getResources().getString(R.string.basic_school));
        categories.add(getResources().getString(R.string.undergraduate));
        categories.add(getResources().getString(R.string.other_education));

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }

            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schoolarshipSpinner.setAdapter(dataAdapter);

        schoolarshipSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals(getResources().getString(R.string.schoolarship))) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(PersonalDataActivity.this, item, Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        Calendar currentDate = Calendar.getInstance();
        int currentYear = currentDate.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH);
        int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
        age = (int) currentYear - year - 1;

        if (currentMonth == month) {
            if (currentDay >= dayOfMonth) {
                age = age + 1;
            }
        } else if (currentMonth > month) {
            age = age + 1;
        }
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        dateTextView.setText(currentDateString);

    }

    public void createDatePicker(View view) {
        DialogFragment datePicker = new DialogFragment();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }

    public String getGender() {

        if (genderRadioGroup.getCheckedRadioButtonId() == R.id.maleRadioButton) {
            return "Male";
        }
        if (genderRadioGroup.getCheckedRadioButtonId() == R.id.femaleRadioButton) {
            return "Female";
        }
        if (genderRadioGroup.getCheckedRadioButtonId() == R.id.combatChopperRadioButton) {
            return "Combat Chopper";
        }
        return "Non selected";
    }


    private void contactDataActivity() {

        String name = nameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String gender = getGender();
        String birthdate = dateTextView.getText().toString();
        String schoolarship = schoolarshipSpinner.getSelectedItem().toString();
        Intent startActivity = new Intent(PersonalDataActivity.this, ContactDataActivity.class);


        if (!name.isEmpty() && !lastName.isEmpty() && !gender.isEmpty()) {
            if (age < 10) {
                Toast.makeText(this, "Usted debe ser mayor de 10 años", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("fff", "\n");
                Log.d("fff", name + " " + lastName + "\n");
                Log.d("fff", gender + "\n");
                Log.d("fff", "Nació el " + birthdate + "\n");
                Log.d("fff", "Nació el " + birthdate + "\n");
                if (schoolarship.equals(getResources().getString(R.string.schoolarship))) {
                    Log.d("fff", "No elige escolaridad" + "\n");
                } else {
                    Log.d("fff", schoolarship + "\n");
                }
                startActivity(startActivity);
            }

        } else {
            Toast.makeText(this, "Ingrese válidamente los datos", Toast.LENGTH_SHORT).show();
        }
    }
}