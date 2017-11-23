package com.example.telefonica_final.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.telefonica_final.Helper.HelperTelefonica;
import com.example.telefonica_final.Models.Pago;
import com.example.telefonica_final.Models.Recarga;

import java.util.ArrayList;
import java.util.List;



public class DataTelefonica {

    SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;

    public DataTelefonica(Context context){
        dbHelper = new HelperTelefonica(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();

    }


    //PAGO Crear un registro de pago

    public Pago createPago(Pago pago){
        ContentValues values = new ContentValues();
        values.put(HelperTelefonica.COLUMN_DEUDA, pago.getDeuda());

        long insertId = database.insert(HelperTelefonica.TABLE_PAGO, null, values);

        pago.setId(insertId);
        return pago;
    }

    public List<Pago> cursorToList(Cursor cursor){
        List<Pago> pagos = new ArrayList<>();
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                Pago pago = new Pago();
                pago.setId(cursor.getLong(cursor.getColumnIndex(HelperTelefonica.COLUMN_ID)));
                pago.setDeuda(cursor.getInt(cursor.getColumnIndex(HelperTelefonica.COLUMN_DEUDA)));

                pagos.add(pago);
            }
        }
        return pagos;
    }

    public List<Pago> findAll(){
        Cursor cursor = database.rawQuery("select * from pago", null);
        List<Pago> pagos = cursorToList(cursor);
        return pagos;
    }

    //up date a la dueda cuando se realize una recarga
    public void updateDeuda (int valor_recarga, int idPago){
        Cursor cursor = database.rawQuery("select * from pago where id = '"+idPago+"'", null);
        List<Pago> pagos = cursorToList(cursor);
        int oldDeuda = pagos.get(0).getDeuda();
        int newDeuda = oldDeuda - valor_recarga;
        database.execSQL("update pago set deuda = '"+newDeuda+"' where id = '"+idPago+"'");
    }

//***********************************************//
    //RECARGAS


    public Recarga createRecarga(Recarga recarga){
        ContentValues values = new ContentValues();
        values.put(HelperTelefonica.COLUMN_VALOR_RECARGA, recarga.getValor());
        values.put(HelperTelefonica.COLUMN_TIPO, recarga.getTipo());
        values.put(HelperTelefonica.COLUMN_PAGO, recarga.getPago_id());

        long insertId = database.insert(HelperTelefonica.TABLE_RECARGA, null, values);

        recarga.setId(insertId);
        return recarga;
    }


    public List<Recarga> cursorToListRecarga(Cursor cursor){
        List<Recarga> recargas = new ArrayList<>();
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                Recarga recarga = new Recarga();
                recarga.setId(cursor.getLong(cursor.getColumnIndex(HelperTelefonica.COLUMN_ID)));
                recarga.setValor(cursor.getInt(cursor.getColumnIndex(HelperTelefonica.COLUMN_VALOR_RECARGA)));
                recarga.setTipo(cursor.getString(cursor.getColumnIndex(HelperTelefonica.COLUMN_TIPO)));
                recarga.setPago_id(cursor.getInt(cursor.getColumnIndex(HelperTelefonica.COLUMN_PAGO)));

                recargas.add(recarga);
            }
        }
        return recargas;
    }

    public List<Recarga> findAllRecargas(){
        Cursor cursor = database.rawQuery("select * from recarga", null);
        List<Recarga> recargas = cursorToListRecarga(cursor);
        return recargas;
    }



}
