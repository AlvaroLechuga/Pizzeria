package com.example.usuario.pedidos;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityConfirmacion extends AppCompatActivity {

    Button btnConfirmar_Pedido;
    Button btnRechazar_Pedido;
    TextView txTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);

        btnConfirmar_Pedido = findViewById(R.id.btnConfirmar_Pedido);
        btnRechazar_Pedido = findViewById(R.id.btnRechazar_Pedido);
        txTicket = findViewById(R.id.txTicket);

        Bundle extras = getIntent().getExtras();
        if(extras == null){
            return;
        }
        final String tipoPizza = extras.getString("seleccionPizza");
        final String dimensionPizza = extras.getString("dimensionPizza");
        final String masaPizza = extras.getString("masaPizza");
        final String ingredientes = extras.getString("ingredientes");
        final String ingredientesExtras = extras.getString("ingredientesExtras");

        final NotificaitonHelper notificationHelper = new NotificaitonHelper(this);

        double coste = calcularCoste(dimensionPizza);

        if(tipoPizza.equals("favorita")){
            SharedPreferences prefs = getSharedPreferences("pizzaFavorita", Context.MODE_PRIVATE);
            String ingredientes2 = prefs.getString("ingredientes", "");
            txTicket.setText("Tipo de pizza: "+tipoPizza +"\nTamaño de la pizza: "+dimensionPizza +"\nBase de la pizza: "+masaPizza +"\nIngredientes: "+ingredientes2 +"\nExtra: "+ingredientesExtras+"\nCoste: "+coste+"€");
        }else{
            txTicket.setText("Tipo de pizza: "+tipoPizza +"\nTamaño de la pizza: "+dimensionPizza +"\nBase de la pizza: "+masaPizza +"\nIngredientes: "+ingredientes +"\nExtra: "+ingredientesExtras+"\nCoste: "+coste+"€");
        }

        btnConfirmar_Pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationHelper.notify("Su pizza esta en proceso", "PEDIDO");

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnRechazar_Pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationHelper.notify("Se ha cancelado su pizza, intentalo mas tarde", "ALERTA");

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private double calcularCoste(String dimensionPizza) {

        double precio = 0;
        switch (dimensionPizza){

            case "Pequeña":
                precio = 8.99;
                break;
            case "Mediana":
                precio = 10.99;
                break;
            case "Familiar":
                precio = 12.99;
                break;
            case "Medio-Metro":
                precio = 15.99;
                break;

        }

        return  precio;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent= new Intent (getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
