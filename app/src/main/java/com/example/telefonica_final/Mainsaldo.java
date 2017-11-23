package com.example.telefonica_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.telefonica_final.Data.DataTelefonica;
import com.example.telefonica_final.Models.Pago;

import java.util.List;

public class Mainsaldo extends AppCompatActivity {

    DataTelefonica dataTelefonica;
    List<Pago> pagos;
    TextView deuda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainsaldo);

        deuda  = (TextView) findViewById(R.id.valor_deuda);
        dataTelefonica = new DataTelefonica(this);
        dataTelefonica.open();

        pagos = dataTelefonica.findAll();
        Log.i("LOGTAG", "Deuda: "+ dataTelefonica.findAll().get(0).getDeuda());
        deuda.setText(Integer.toString(pagos.get(0).getDeuda()));

    }

    public void pago(View view){
        Intent intent = new Intent(this, Mainpago.class);
        startActivity(intent);
    }
}
