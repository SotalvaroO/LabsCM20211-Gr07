package co.edu.udea.compumovil.gr07_20211.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        txtDireccion = findViewById(R.id.editText_Ciudad);


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
                if (acPais.getText().toString().toLowerCase().equals("colombia")) {
                    acCiudad.setAdapter(adapterCiudad);
                } else {
                    acCiudad.setAdapter(null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

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

        if (!telefono.isEmpty() && !correo.isEmpty() && !direccion.isEmpty()) {
            Toast.makeText(this, "Datos ingresados correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Ingrese válidamente los datos", Toast.LENGTH_SHORT).show();
        }
    }

}