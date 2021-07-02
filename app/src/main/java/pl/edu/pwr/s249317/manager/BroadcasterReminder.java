package pl.edu.pwr.s249317.manager;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class BroadcasterReminder extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyMedication")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Your medication will expire soon!")
                .setContentText("if it is a first-aid medicine for you, buy a new packaging and dispose of the old one," +
                        " do not throw it directly into a common garbage can!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(201, builder.build());
    }
}
