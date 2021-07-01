package pl.edu.pwr.s249317.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class YourMedicationActivity extends AppCompatActivity {

    private ListView medicationList;
    private MedicationDataBase medicationDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_medication);

        Button buttonGoToAdd = findViewById(R.id.buttonGoToAdd);
        medicationList = findViewById(R.id.medicationList);

        medicationDataBase = new MedicationDataBase(YourMedicationActivity.this);
        showAllMedicationsByAmount(medicationDataBase);


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
                showAllMedicationsByAmount(medicationDataBase);
                return false;
            }
        });
    }

    private void showAllMedicationsByAmount(MedicationDataBase dataBase) {
        ArrayAdapter arrayAdapter = new ArrayAdapter<Medication>(YourMedicationActivity.this, android.R.layout.simple_list_item_1,
                dataBase.getAllMedications("MEDICATION_PACKAGE_AMOUNT"));
        medicationList.setAdapter(arrayAdapter);
    }
}