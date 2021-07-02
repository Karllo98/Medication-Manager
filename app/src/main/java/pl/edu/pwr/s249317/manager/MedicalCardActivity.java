package pl.edu.pwr.s249317.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MedicalCardActivity extends AppCompatActivity {

    private EditText editTextPersonName, editTextSurname, editTextBloodGroup;
    private TextView textMedicalData;
    private Button buttonConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_card);

        initialazaViews();

        textMedicalData.setText(readFromFile(MedicalCardActivity.this));

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Name: " + editTextPersonName.getText().toString() + "\n\n"
                        + "Surname: " + editTextSurname.getText().toString() + "\n\n"
                        + "Blood group: " + editTextBloodGroup.getText().toString();
                writeToFile(data,MedicalCardActivity.this);
                textMedicalData.setText(readFromFile(MedicalCardActivity.this));;
            }
        });



    }

    private void initialazaViews() {
        editTextPersonName = findViewById(R.id.editTextPersonName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextBloodGroup = findViewById(R.id.editTextBloodGroup);
        textMedicalData = findViewById(R.id.textMedicalData);
        buttonConfirm = findViewById(R.id.buttonConfirm);
    }

    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("medical_data.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context) {

        String returnStr = "";

        try {
            InputStream inputStream = context.openFileInput("medical_data.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                returnStr = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("activity login", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("activity login", "Cannot read file: " + e.toString());
        }

        return returnStr;
    }
}