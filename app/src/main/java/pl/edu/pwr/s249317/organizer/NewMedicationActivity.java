package pl.edu.pwr.s249317.organizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class NewMedicationActivity extends AppCompatActivity {

    private Button buttonConfirmAdding, buttonGoToYourMedication;
    private ImageButton buttonAddToCalendar;
    private EditText editTextName, editTextAmount, editTextExpiryDate, editTextComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medication);
        makeChannelNotifiaction();

        buttonConfirmAdding = findViewById(R.id.buttonConfirmAdding);
        buttonGoToYourMedication = findViewById(R.id.buttonGoToYourMedications);
        buttonAddToCalendar = findViewById(R.id.buttonAddToCalendar);
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


                Intent intent1 = new Intent(NewMedicationActivity.this, Broadcaster.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(NewMedicationActivity.this,0,intent1,0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                long currentTime = System.currentTimeMillis();

                long tensecondsinms = 1000 * 10;

                alarmManager.set(AlarmManager.RTC_WAKEUP, currentTime + tensecondsinms, pendingIntent);

            }
        });

        buttonGoToYourMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewMedicationActivity.this, YourMedicationActivity.class);
                startActivity(intent);
            }
        });

        buttonAddToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.Events.TITLE, "Take your medication: " + editTextName.getText().toString());
                intent.putExtra(CalendarContract.Events.DESCRIPTION, "It's time to take your scheduled medication");

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                else {
                    Toast.makeText(NewMedicationActivity.this, "No calendar detected!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void makeChannelNotifiaction(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "MedicationManagerChannel";
            String description = "Channel for notifaction";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyMedication", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }
}