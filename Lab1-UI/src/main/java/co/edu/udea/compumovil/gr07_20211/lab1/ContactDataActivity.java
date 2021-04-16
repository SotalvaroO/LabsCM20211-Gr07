package co.edu.udea.compumovil.gr07_20211.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.function.IntToDoubleFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactDataActivity extends AppCompatActivity {

    AutoCompleteTextView acPais, acCiudad;
    EditText txtTelefono, txtCorreo, txtDireccion;
    Button btnSiguiente, btnAtras;
    String pais;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_data);

        txtTelefono = findViewById(R.id.editText_Telefono);
        txtCorreo = findViewById(R.id.editText_Email);
        txtDireccion = findViewById(R.id.editText_Address);


        ArrayAdapter<String> adapterPais = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, countries);
        acPais = findViewById(R.id.autoComplete_Pais);
        acPais.setAdapter(adapterPais);

        ArrayAdapter<String> adapterCiudad = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, cities);
        acCiudad = findViewById(R.id.autoComplete_Ciudad);

        acCiudad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (acPais.getText().toString().toLowerCase().equals("colombia")) {
                    acCiudad.setAdapter(adapterCiudad);
                } else {
                    acCiudad.setAdapter(null);
                }

            }
        });


        btnSiguiente = findViewById(R.id.button_Next);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactDataActivity();
            }
        });

        btnAtras = findViewById(R.id.button_Return);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personalDataActivity();
            }
        });

    }


    // Funcion Para Volver Atras
    private void personalDataActivity() {
        Intent startActivity = new Intent(ContactDataActivity.this, PersonalDataActivity.class);
        startActivity(startActivity);
    }

    private static final String[] countries = new String[]{
            "Colombia", "Ecuador", "Venezuela", "Peru", "Bolivia", "Paraguay", "Brasil", "Chile", "Uruguay", "Argentina"
    };

    private static final String[] cities = new String[] {
            "Medellin", "Bogota", "Cali", "Barranquilla"
    };


    private void contactDataActivity() {
        String telefono = txtTelefono.getText().toString();
        String correo = txtCorreo.getText().toString();
        String direccion = txtDireccion.getText().toString();
        String pais = acPais.getText().toString();
        String ciudad = acCiudad.getText().toString();
        boolean isEmailValid = isEmailValid(correo);
        Intent endActivity = new Intent(ContactDataActivity.this, EndActivity.class);
        if (!telefono.isEmpty() && !correo.isEmpty()) {
            if (!isEmailValid){
                Toast.makeText(this, "Email ingresado no es correcto", Toast.LENGTH_SHORT).show();
            }else {
                Log.d("informacion", "\n");
                Log.d("informacion", "==========================================");
                Log.d("informacion", "\n");
                Log.d("informacion", "Información del contacto");
                Log.d("informacion", "\n");
                Log.d("informacion", "==========================================");
                Log.d("informacion", "\n");
                Log.d("informacion", "Teléfono de contacto: " + telefono + "\n");
                if (direccion.isEmpty()){
                    Log.d("informacion", "No ingresa direccion " +  "\n");
                }else {
                    Log.d("informacion", "Direccion: " + direccion + "\n");
                }
                Log.d("informacion", "Email: " + correo + "\n");
                Log.d("informacion", "Pais: " + pais + "\n");
                if (ciudad.isEmpty()) {
                    Log.d("informacion", "No elige Ciudad" + "\n");
                } else {
                    Log.d("informacion", "Ciudad: " + ciudad + "\n");
                }
                startActivity(endActivity);
                Toast.makeText(this, "Datos ingresados correctamente", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Ingrese válidamente los datos", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}