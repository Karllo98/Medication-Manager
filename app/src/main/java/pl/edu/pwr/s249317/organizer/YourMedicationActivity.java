package pl.edu.pwr.s249317.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class YourMedicationActivity extends AppCompatActivity {

    private Button buttonGoToAdd;
    private ListView medicationList;
    private ArrayAdapter arrayAdapter;
    private MedicationDataBase medicationDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_medication);

        buttonGoToAdd = findViewById(R.id.buttonGoToAdd);
        medicationList = findViewById(R.id.medicationList);

        medicationDataBase = new MedicationDataBase(YourMedicationActivity.this);
        showAllMedications(medicationDataBase);


        buttonGoToAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YourMedicationActivity.this, NewMedicationActivity.class);
                startActivity(intent);
            }
        });

        medicationList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Medication medication = (Medication) parent.getItemAtPosition(position);
                medicationDataBase.deleteFromDataBase(medication);
                showAllMedications(medicationDataBase);
                return false;
            }
        });

        //List<Medication> allMedications = medicationDataBase.getAllMedications();

    }

    private void showAllMedications(MedicationDataBase dataBase) {
        arrayAdapter = new ArrayAdapter<Medication>(YourMedicationActivity.this, android.R.layout.simple_list_item_1, dataBase.getAllMedications());
        medicationList.setAdapter(arrayAdapter);
    }
}