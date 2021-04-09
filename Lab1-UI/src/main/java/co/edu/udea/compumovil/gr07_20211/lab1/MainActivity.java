package co.edu.udea.compumovil.gr07_20211.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button startRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startRegisterButton = findViewById(R.id.startRegisterButton);

        startRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personalDataActivity();
            }
        });

    }

    private void personalDataActivity() {
        Intent startActivity = new Intent(MainActivity.this, PersonalDataActivity.class);
        startActivity(startActivity);
    }
}