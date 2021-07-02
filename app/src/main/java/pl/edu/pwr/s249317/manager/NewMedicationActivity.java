package pl.edu.pwr.s249317.manager;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewMedicationActivity extends AppCompatActivity {

    private Button buttonConfirmAdding, buttonGoToYourMedication;
    private ImageButton buttonAddToCalendar;
    private EditText editTextName, editTextAmount, editTextAmountInOne, editTextExpiryDate, editTextComments;
    private CheckBox checkBoxReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medication);
        makeChannelNotifiaction();
        initialazeViews();

        buttonConfirmAdding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Medication medication;

                try {

                    SimpleDateFormat simpleExpiryDateFormat = new SimpleDateFormat("yyyy/MM", java.util.Locale.getDefault());
                    Date date = simpleExpiryDateFormat.parse(editTextExpiryDate.getText().toString());
                    assert date != null;
                    long dateInMillis = date.getTime();

                    medication = new Medication(-1, editTextName.getText().toString(), Integer.parseInt(editTextAmount.getText().toString()),
                            Integer.parseInt(editTextAmountInOne.getText().toString()), Integer.parseInt(editTextAmountInOne.getText().toString()),
                            dateInMillis, editTextComments.getText().toString());
                    Toast.makeText(NewMedicationActivity.this, medication.toString(), Toast.LENGTH_SHORT).show();

                    MedicationDataBase medicationDataBase = new MedicationDataBase(NewMedicationActivity.this);
                    medicationDataBase.addToDataBase(medication);

                    setNewMedAlarm();

                    if (checkBoxReminder.isChecked())
                        setNewReminder(dateInMillis);

                }
                catch (Exception exception) {
                    Toast.makeText(NewMedicationActivity.this, "Error, invalid data format", Toast.LENGTH_SHORT).show();
                }
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
                intent.putExtra(CalendarContract.Events.TITLE, "Your medication will expire soon (" + editTextName.getText().toString() + ")");
                intent.putExtra(CalendarContract.Events.ALL_DAY, true);
                intent.putExtra(CalendarContract.Events.DESCRIPTION, "Your medication will expire soon, if it is a first-aid medicine for you," +
                        " buy a new packaging and dispose of the old one, do not throw it directly into a common garbage can!\n");

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                else {
                    Toast.makeText(NewMedicationActivity.this, "No calendar detected!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    private void setNewMedAlarm() {
        Intent broadcastIntent = new Intent(NewMedicationActivity.this, Broadcaster.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(NewMedicationActivity.this,0,broadcastIntent,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long currentTime = System.currentTimeMillis();
        long twoSecondsInMs = 1000 * 2;
        alarmManager.set(AlarmManager.RTC_WAKEUP, currentTime + twoSecondsInMs, pendingIntent);
    }

    private void initialazeViews() {
        buttonConfirmAdding = findViewById(R.id.buttonConfirmAdding);
        buttonGoToYourMedication = findViewById(R.id.buttonGoToYourMedications);
        buttonAddToCalendar = findViewById(R.id.buttonAddToCalendar);
        editTextName = findViewById(R.id.editTextName);
        editTextAmount = findViewById(R.id.editTextAmount);
        editTextAmountInOne = findViewById(R.id.editTextAmountInOne);
        editTextExpiryDate = findViewById(R.id.editTextExpiryDate);
        editTextComments = findViewById(R.id.editTextComments);
        checkBoxReminder = findViewById(R.id.checkBoxReminder);
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

    private void setNewReminder(long date) {
        Intent broadcastIntent = new Intent(NewMedicationActivity.this, BroadcasterReminder.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(NewMedicationActivity.this,0,broadcastIntent,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, date, pendingIntent);
    }
}