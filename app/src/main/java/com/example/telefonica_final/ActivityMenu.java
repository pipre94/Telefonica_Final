package com.example.telefonica_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.telefonica_final.Data.DataTelefonica;
import com.example.telefonica_final.Models.Pago;

public class ActivityMenu extends AppCompatActivity {

    DataTelefonica dataTelefonica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        dataTelefonica = new DataTelefonica(this);
        dataTelefonica.open();

        if (dataTelefonica.findAll().size()==0){
            Log.i("LOGTAG", "Se creo una Deuda");
            createData();
        }
        Log.i("LOGTAG", "Se creo una Deuda: "+ dataTelefonica.findAll().get(0).getDeuda());
    }

    public void exit(){
        finish();
        System.exit(2);
    }

    public void pago(View view){
        Intent intent = new Intent(this, Mainpago.class);
        startActivity(intent);
    }

    public void saldo(View view){
        Intent intent = new Intent(this, Mainsaldo.class);
        startActivity(intent);
    }

    private void createData(){
        Pago pago = new Pago();
        pago.setDeuda(300000);

        dataTelefonica.createPago(pago);
    }


}
