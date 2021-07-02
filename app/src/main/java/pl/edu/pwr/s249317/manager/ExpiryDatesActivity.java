package pl.edu.pwr.s249317.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

        expiryDatesList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Medication medication = (Medication) parent.getItemAtPosition(position);

                if (System.currentTimeMillis() >= medication.getExpiryDate()){
                    medicationDataBase.deleteFromDataBase(medication);
                    Toast.makeText(ExpiryDatesActivity.this, "Your medication has \n been removed!", Toast.LENGTH_SHORT).show();
                    showAllMedicationsByExpireDate(medicationDataBase);
                }
                return false;
            }
        });

    }

    private void showAllMedicationsByExpireDate(MedicationDataBase dataBase) {
        ArrayAdapter arrayAdapter = new ArrayAdapter<Medication>(ExpiryDatesActivity.this, android.R.layout.simple_list_item_1,
                dataBase.getAllMedications("EXPIRY_DATE"));
        expiryDatesList.setAdapter(arrayAdapter);
    }
}