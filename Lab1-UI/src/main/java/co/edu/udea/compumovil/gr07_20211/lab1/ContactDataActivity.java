package co.edu.udea.compumovil.gr07_20211.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class ContactDataActivity extends AppCompatActivity {

    AutoCompleteTextView txtPais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_data);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        txtPais = (AutoCompleteTextView) findViewById(R.id.acPais);
        txtPais.setAdapter(adapter);

    }

    private static final String[] COUNTRIES = new String[] {
            "Colombia", "Ecuador", "Venezuela", "Peru", "Bolivia", "Paraguay", "Brasil", "Chile", "Uruguay", "Argentina"
    };
}