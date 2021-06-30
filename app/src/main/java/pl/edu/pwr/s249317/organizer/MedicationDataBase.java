package pl.edu.pwr.s249317.organizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MedicationDataBase extends SQLiteOpenHelper {

    public static final String MEDICATION_TB = "MEDICATION_TB";
    public static final String ID = "ID";
    public static final String MEDICATION_NAME = "MEDICATION_NAME";
    public static final String MEDICATION_AMOUNT = "MEDICATION_AMOUNT";
    public static final String EXPIRY_DATE = "EXPIRY_DATE";
    public static final String MEDICATION_COMMENTS = "MEDICATION_COMMENTS";

    public MedicationDataBase(@Nullable Context context) {
        super(context, "medication.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + MEDICATION_TB + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MEDICATION_NAME + " TEXT, " + MEDICATION_AMOUNT + " INT, " + EXPIRY_DATE + " TEXT, " + MEDICATION_COMMENTS + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addToDataBase(Medication medication) {
        SQLiteDatabase dataBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(MEDICATION_NAME, medication.getName());
        contentValues.put(MEDICATION_AMOUNT, medication.getAmount());
        contentValues.put(EXPIRY_DATE, medication.getExpiryDate());
        contentValues.put(MEDICATION_COMMENTS, medication.getComments());

        long insertResult = dataBase.insert(MEDICATION_TB, null, contentValues);

        if (insertResult == -1)
            return false;
        else
            return true;

    }

    public List<Medication> getAllMedications() {

        List<Medication> list = new ArrayList<>();
        String query = "SELECT * FROM " + MEDICATION_TB;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int amount = cursor.getInt(2);
                String expiryDate = cursor.getString(3);
                String comments = cursor.getString(4);

                Medication medication = new Medication(id, name, amount, expiryDate, comments);
                list.add(medication);

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return list;
    }

    public List<Medication> sortAllMedications() {

        List<Medication> list = new ArrayList<>();
        String query = "SELECT * FROM " + MEDICATION_TB + " ORDER BY " + EXPIRY_DATE;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int amount = cursor.getInt(2);
                String expiryDate = cursor.getString(3);
                String comments = cursor.getString(4);

                Medication medication = new Medication(id, name, amount, expiryDate, comments);
                list.add(medication);

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return list;
    }

    public boolean deleteFromDataBase(Medication medication) {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "DELETE FROM " + MEDICATION_TB + " WHERE " + ID + " = " + medication.getId();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst())
            return true;
        else return false;
    }
}
