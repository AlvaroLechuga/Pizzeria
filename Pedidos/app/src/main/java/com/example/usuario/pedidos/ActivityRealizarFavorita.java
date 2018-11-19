package com.example.usuario.pedidos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ActivityRealizarFavorita extends AppCompatActivity {

    Button btnRealizarFavorita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_favorita);

        btnRealizarFavorita = findViewById(R.id.btnRealizarFavorita);
        btnRealizarFavorita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ingredientes = "";

                CheckBox cbQueso = findViewById(R.id.cbQueso);
                CheckBox cbTomate = findViewById(R.id.cbTomate);
                CheckBox cbCebolla = findViewById(R.id.cbCebolla);
                CheckBox cbPollo = findViewById(R.id.cbPollo);
                CheckBox cbTernera = findViewById(R.id.cbTernera);
                CheckBox cbMaiz = findViewById(R.id.cbMaiz);
                CheckBox cbPimiento = findViewById(R.id.cbPimiento);
                CheckBox cbAtun = findViewById(R.id.cbAtun);
                CheckBox cbBacon = findViewById(R.id.cbBacon);

                if(cbQueso.isChecked()){
                    ingredientes += "queso, ";
                }

                if(cbTomate.isChecked()){
                    ingredientes +="tomate, ";
                }

                if(cbCebolla.isChecked()){
                    ingredientes +="cebolla, ";
                }

                if(cbPollo.isChecked()){
                    ingredientes +="pollo, ";
                }

                if(cbTernera.isChecked()){
                    ingredientes +=" ternera, ";
                }

                if(cbMaiz.isChecked()){
                    ingredientes += "maiz, ";
                }

                if(cbPimiento.isChecked()){
                    ingredientes += "pimiento, ";
                }

                if(cbAtun.isChecked()){
                    ingredientes += "atun, ";
                }

                if(cbBacon.isChecked()){
                    ingredientes += "bacon, ";
                }

                ingredientes = ingredientes.substring(0,ingredientes.length()-2);
                SharedPreferences prefs = getSharedPreferences("pizzaFavorita",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("ingredientes", ingredientes);
                editor.putBoolean("insertado", true);
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), ActivityOpciones.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
