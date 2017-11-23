package com.example.telefonica_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.telefonica_final.Data.DataTelefonica;
import com.example.telefonica_final.Models.Pago;
import com.example.telefonica_final.Models.Recarga;

import java.util.List;

public class Mainpago extends AppCompatActivity {

    DataTelefonica dataTelefonica;
    List<Pago> pagos;
    List<Recarga> recargas;
    EditText recarga;
    Spinner spinner;
    Button btnrecarga;
    int valorRecarga, idPago, valorAnterior;
    final String[] tipoPago = new String[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpago);

        spinner = (Spinner) findViewById(R.id.spinner);
        String[] tipo = {"CREDITO","DEBITO"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipo));
        btnrecarga = (Button) findViewById(R.id.btn_realizar_recarga);
        recarga  = (EditText) findViewById(R.id.valor_recarga);
        dataTelefonica = new DataTelefonica(this);
        dataTelefonica.open();

        pagos = dataTelefonica.findAll();
        Log.i("LOGTAG", "Deuda: "+ dataTelefonica.findAll().get(0).getDeuda());
        idPago = (int) pagos.get(0).getId();
        valorAnterior = pagos.get(0).getDeuda();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                tipoPago[0] = (String) adapterView.getItemAtPosition(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });


        btnrecarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!recarga.getText().toString().equals("")){
                    valorRecarga  = Integer.parseInt(recarga.getText().toString());
                    Log.i("LOGTAG", "recarga: "+ valorRecarga);
                    Log.i("LOGTAG", "TIPO: "+ tipoPago[0]);
                    if (valorAnterior>0 && valorRecarga<=valorAnterior ){
                        crearRecagra();
                        recarga.setText(" ");
                        dataTelefonica.updateDeuda(valorRecarga,idPago);
                        Toast.makeText(getApplication(),"RECARGA REALIZADA", Toast.LENGTH_LONG).show();
                        pago();
                    }else if (valorRecarga>valorAnterior){
                        Toast.makeText(getApplication(),"VALOR DE RECAGRA SUPERA VALOR DE LA DEUDA ", Toast.LENGTH_LONG).show();
                    }else {Toast.makeText(getApplication(),"VALOR DE DEUDA ES $0 , DEUDA PAGADA", Toast.LENGTH_LONG).show();}



                }else   Toast.makeText(getApplication(),"VALOR DE RECARGA VACIO", Toast.LENGTH_LONG).show();

            }
        });

    }

    private void crearRecagra(){
        Recarga r = new Recarga();
        r.setValor(valorRecarga);
        r.setTipo(tipoPago[0]);
        r.setPago_id(idPago);
        dataTelefonica.createRecarga(r);
    }

    public void pago(){
        Intent intent = new Intent(this, Mainsaldo.class);
        startActivity(intent);
    }
}
