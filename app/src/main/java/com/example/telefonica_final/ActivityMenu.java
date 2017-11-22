package com.example.telefonica_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void exit(View view){
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


}
