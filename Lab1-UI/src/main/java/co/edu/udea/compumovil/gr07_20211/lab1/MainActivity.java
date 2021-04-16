package co.edu.udea.compumovil.gr07_20211.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button startRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, PersonalDataActivity.class);
                startActivity(intent);
            }
        },2000);
        //startRegisterButton = findViewById(R.id.startRegisterButton);

       /* startRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personalDataActivity();
            }
        });*/

    }

   /* private void personalDataActivity() {
        Intent startActivity = new Intent(MainActivity.this, PersonalDataActivity.class);
        startActivity(startActivity);
    }*/
}