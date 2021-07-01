package pl.edu.pwr.s249317.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ExpiryDatesActivity extends AppCompatActivity {

    private ListView expiryDatesList;
    private MedicationDataBase medicationDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expiry_dates);

        expiryDatesList = findViewById(R.id.expiryDatesList);

        medicationDataBase = new MedicationDataBase(ExpiryDatesActivity.this);
        showAllMedicationsByExpireDate(medicationDataBase);

    }

    private void showAllMedicationsByExpireDate(MedicationDataBase dataBase) {
        ArrayAdapter arrayAdapter = new ArrayAdapter<Medication>(ExpiryDatesActivity.this, android.R.layout.simple_list_item_1,
                dataBase.getAllMedications("EXPIRY_DATE"));
        expiryDatesList.setAdapter(arrayAdapter);
    }
}