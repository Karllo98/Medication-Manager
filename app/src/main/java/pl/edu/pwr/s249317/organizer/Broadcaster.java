package pl.edu.pwr.s249317.organizer;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Broadcaster extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyMedication")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Take your medication!")
                .setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. ")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200, builder.build());

    }
}
