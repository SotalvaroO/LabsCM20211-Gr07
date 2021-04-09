package co.edu.udea.compumovil.gr07_20211.lab1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersonalDataActivity extends AppCompatActivity {

    Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactDataActivity();
            }
        });

    }

    private void contactDataActivity() {
        Intent startActivity = new Intent(PersonalDataActivity.this, ContactDataActivity.class);
        startActivity(startActivity);
    }
}