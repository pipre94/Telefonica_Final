package com.example.telefonica_final.Helper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class HelperTelefonica extends SQLiteOpenHelper {

    private static final String LOGTAG = "LOGTAG";
    public static final String DATABASE_NAME = "telefonica";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PAGO = "pago";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DEUDA = "deuda";

    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_PAGO + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DEUDA + " INTEGER " +
                    ")";

    public static final String TABLE_RECARGA = "recarga";
    public static final String COLUMN_VALOR_RECARGA = "valor";
    public static final String COLUMN_TIPO = "tipo";
    public static final String COLUMN_PAGO = "pago_id";

    public static final String TABLE_CREATE_RECARGA =
            "CREATE TABLE " + TABLE_RECARGA + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_VALOR_RECARGA + " INTEGER, " +
                    COLUMN_TIPO + " TEXT," +
                    COLUMN_PAGO + " INTEGER " +
                    ")";


    public HelperTelefonica(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_CREATE_RECARGA);
        Log.i(LOGTAG, "Tabla de usuarios creada correctamente.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PAGO);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_RECARGA);
        onCreate(db);
    }
}
