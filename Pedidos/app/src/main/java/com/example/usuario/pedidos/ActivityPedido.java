package com.example.usuario.pedidos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityPedido extends AppCompatActivity {

    Button btnPersonalizada;
    Button btnFavorita;
    Button btnPredeterminada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        btnPersonalizada = findViewById(R.id.btnPersonalizada);
        btnPredeterminada = findViewById(R.id.btnPredeterminada);
        btnFavorita = findViewById(R.id.btnFavorita);

        btnPersonalizada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cambioActivity = new Intent(getApplicationContext(), ActivityDimensionesPizza.class);
                cambioActivity.putExtra("seleccionPizza", "personalizada");
                finish();
                startActivity(cambioActivity);
            }
        });

        btnPredeterminada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cambioActivity = new Intent(getApplicationContext(), ActivityDimensionesPizza.class);
                cambioActivity.putExtra("seleccionPizza", "predeterminada");
                finish();
                startActivity(cambioActivity);
            }
        });

        btnFavorita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("pizzaFavorita",Context.MODE_PRIVATE);
                String ingredientes = prefs.getString("ingredientes", "");
                Boolean detectar = prefs.getBoolean("insertado", false);

                if(!detectar){
                    Toast.makeText(getApplicationContext(), "No tienes acceso configura tu pizza favorita", Toast.LENGTH_LONG).show();
                }else{
                    Intent cambioActivity = new Intent(getApplicationContext(), ActivityDimensionesPizza.class);
                    cambioActivity.putExtra("seleccionPizza", "favorita");
                    cambioActivity.putExtra("ingredientes", ingredientes);
                    finish();
                    startActivity(cambioActivity);
                }


            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent= new Intent (getApplicationContext(), MainActivity.class);
            finish();
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
