package pl.edu.pwr.s249317.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewMedicationActivity extends AppCompatActivity {

    Button buttonConfirmAdding;
    EditText editTextName, editTextAmount, editTextExpiryDate, editTextComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medication);

        buttonConfirmAdding = findViewById(R.id.buttonConfirmAdding);
        editTextName = findViewById(R.id.editTextName);
        editTextAmount = findViewById(R.id.editTextAmount);
        editTextExpiryDate = findViewById(R.id.editTextExpiryDate);
        editTextComments = findViewById(R.id.editTextComments);

        buttonConfirmAdding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Medication medication;

                try {
                    medication = new Medication(-1, editTextName.getText().toString(), Integer.parseInt(editTextAmount.getText().toString()),
                            editTextExpiryDate.getText().toString(), editTextComments.getText().toString());
                    Toast.makeText(NewMedicationActivity.this, medication.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception exception) {
                    Toast.makeText(NewMedicationActivity.this, "Error, invalid data format", Toast.LENGTH_SHORT).show();
                    medication = new Medication(-1, "error", 0, "error", "error");
                }

                MedicationDataBase medicationDataBase = new MedicationDataBase(NewMedicationActivity.this);
                boolean success = medicationDataBase.addToDataBase(medication);

                Toast.makeText(NewMedicationActivity.this, "" + success, Toast.LENGTH_SHORT).show();

            }
        });
    }
}