package pl.edu.pwr.s249317.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ExpiryDateActivity extends AppCompatActivity {

    private ListView expiryDateList;
    private ArrayAdapter arrayAdapter;
    private MedicationDataBase medicationDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expiry_date);

        expiryDateList = findViewById(R.id.expiryDateList);

        medicationDataBase = new MedicationDataBase(ExpiryDateActivity.this);
        sortMedications(medicationDataBase);
    }

    private void sortMedications(MedicationDataBase dataBase) {
        arrayAdapter = new ArrayAdapter<Medication>(ExpiryDateActivity.this, android.R.layout.simple_list_item_1, dataBase.sortAllMedications());
        expiryDateList.setAdapter(arrayAdapter);
    }
}