package pl.edu.pwr.s249317.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ExpiryDateActivity extends AppCompatActivity {

    private ListView expiryDateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expiry_date);
    }
}