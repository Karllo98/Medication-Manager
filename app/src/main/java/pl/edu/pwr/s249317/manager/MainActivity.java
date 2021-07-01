package pl.edu.pwr.s249317.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonNext, buttonAdd, buttonYourMedications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialazeViews();

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TookMedicationActivity.class);
                startActivity(intent);
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewMedicationActivity.class);
                startActivity(intent);
            }
        });

        buttonYourMedications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, YourMedicationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initialazeViews(){
        buttonNext = findViewById(R.id.buttonNext);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonYourMedications = findViewById(R.id.buttonYourMedications);
    }
}