package pl.edu.pwr.s249317.manager;

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
    public static final String MEDICATION_PACKAGE_AMOUNT = "MEDICATION_PACKAGE_AMOUNT";
    public static final String MEDICATION_IN_ONE_PACKAGE_AMOUNT = "MEDICATION_IN_ONE_PACKAGE_AMOUNT";
    public static final String MEDICATION_IN_NEW_ONE = "MEDICATION_IN_NEW_ONE";
    public static final String EXPIRY_DATE = "EXPIRY_DATE";
    public static final String MEDICATION_COMMENTS = "MEDICATION_COMMENTS";

    public MedicationDataBase(@Nullable Context context) {
        super(context, "medication.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + MEDICATION_TB + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MEDICATION_NAME + " TEXT, " + MEDICATION_PACKAGE_AMOUNT + " INT, " + MEDICATION_IN_ONE_PACKAGE_AMOUNT + " INT, " +
                MEDICATION_IN_NEW_ONE + " INT, " + EXPIRY_DATE + " LONG, " + MEDICATION_COMMENTS + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addToDataBase(Medication medication) {
        SQLiteDatabase dataBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(MEDICATION_NAME, medication.getName());
        contentValues.put(MEDICATION_PACKAGE_AMOUNT, medication.getPackagingAmount());
        contentValues.put(MEDICATION_IN_ONE_PACKAGE_AMOUNT, medication.getAmountInOnePackage());
        contentValues.put(MEDICATION_IN_NEW_ONE, medication.getAmountInNewOne());
        contentValues.put(EXPIRY_DATE, medication.getExpiryDate());
        contentValues.put(MEDICATION_COMMENTS, medication.getComments());

        long insertResult = dataBase.insert(MEDICATION_TB, null, contentValues);

        if (insertResult == -1)
            return false;
        else
            return true;

    }

    public List<Medication> getAllMedications(String orderedBy) {

        List<Medication> list = new ArrayList<>();
        String query = "SELECT * FROM " + MEDICATION_TB + " ORDER BY " + orderedBy;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int packagingAmount = cursor.getInt(2);
                int amountInOnePackage = cursor.getInt(3);
                int amountInNewOne = cursor.getInt(4);
                long expiryDate = cursor.getLong(5);
                String comments = cursor.getString(6);

                Medication medication = new Medication(id, name, packagingAmount, amountInOnePackage, amountInNewOne, expiryDate, comments);
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

    public void modifyMedicationAmount(Medication medication) {

        int packagingAmount = medication.getPackagingAmount();
        int amountInOnePackage = medication.getAmountInOnePackage();

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if (packagingAmount > 1 || amountInOnePackage > 1 ) {

            if (amountInOnePackage > 1)
                --amountInOnePackage;
            else {
                --packagingAmount;
                amountInOnePackage = medication.getAmountInNewOne();
            }

            contentValues.put(MEDICATION_PACKAGE_AMOUNT, packagingAmount);
            contentValues.put(MEDICATION_IN_ONE_PACKAGE_AMOUNT, amountInOnePackage);

            database.update(MEDICATION_TB, contentValues, "ID = ?", new String[]{Integer.toString(medication.getId())});

        } else if (packagingAmount == 1 && amountInOnePackage == 1)
            deleteFromDataBase(medication);
    }
}
