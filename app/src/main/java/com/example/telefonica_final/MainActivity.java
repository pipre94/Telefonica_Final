package com.example.telefonica_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onShowAccount(View view){
        Intent intent = new Intent(this, MainRegistro.class);
        startActivity(intent);
    }

    public void onShowAccount2(View view){
        Intent intent = new Intent(this, Mainpago.class);
        startActivity(intent);
    }




}
