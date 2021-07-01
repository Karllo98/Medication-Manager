package pl.edu.pwr.s249317.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TookMedicationActivity extends AppCompatActivity {

    private ListView actualMedicationList;
    private MedicationDataBase medicationDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_took_medication);

        actualMedicationList = findViewById(R.id.actualMedicationList);

        medicationDataBase = new MedicationDataBase(TookMedicationActivity.this);
        showAllMedicationsByAmount(medicationDataBase);

        actualMedicationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Medication medication = (Medication) parent.getItemAtPosition(position);
                medicationDataBase.modifyMedicationAmount(medication);
                showAllMedicationsByAmount(medicationDataBase);
            }
        });
    }
    private void showAllMedicationsByAmount(MedicationDataBase dataBase) {
        ArrayAdapter arrayAdapter = new ArrayAdapter<Medication>(TookMedicationActivity.this, android.R.layout.simple_list_item_1,
                dataBase.getAllMedications("EXPIRY_DATE"));
        actualMedicationList.setAdapter(arrayAdapter);
    }
}