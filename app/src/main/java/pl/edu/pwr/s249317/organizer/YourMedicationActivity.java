package pl.edu.pwr.s249317.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class YourMedicationActivity extends AppCompatActivity {

    Button buttonGoToAdd, buttonDelete;
    ListView medicationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_medication);

        buttonGoToAdd = findViewById(R.id.buttonGoToAdd);
        buttonDelete = findViewById(R.id.buttonDelete);
        medicationList = findViewById(R.id.medicationList);

        buttonGoToAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YourMedicationActivity.this, NewMedicationActivity.class);
                startActivity(intent);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("delete");
            }
        });
    }
}