package com.example.usuario.pedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class ActivityPizzaPersonalizada extends AppCompatActivity {

    Button btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_personalizada);

        btnConfirmar = findViewById(R.id.btnConfirmar_Pedido);

        Bundle extras = getIntent().getExtras();
        if(extras == null){
            return;
        }
        final String tipoPizza = extras.getString("seleccionPizza");
        final String dimensionPizza = extras.getString("dimensionPizza");
        final String masaPizza = extras.getString("masaPizza");

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                    ingredientes += "Queso, ";
                }

                if(cbTomate.isChecked()){
                    ingredientes +="Tomate, ";
                }

                if(cbCebolla.isChecked()){
                    ingredientes +="Cebolla, ";
                }

                if(cbPollo.isChecked()){
                    ingredientes +="Pollo, ";
                }

                if(cbTernera.isChecked()){
                    ingredientes +=" Ternera, ";
                }

                if(cbMaiz.isChecked()){
                    ingredientes += "Maiz, ";
                }

                if(cbPimiento.isChecked()){
                    ingredientes += "Pimiento, ";
                }

                if(cbAtun.isChecked()){
                    ingredientes += "Atun, ";
                }

                if(cbBacon.isChecked()){
                    ingredientes += "Bacon, ";
                }

                ingredientes = ingredientes.substring(0,ingredientes.length()-2);

                Intent intent = new Intent(getApplicationContext(), ActivityAdicional.class);
                intent.putExtra("seleccionPizza",tipoPizza);
                intent.putExtra("dimensionPizza", dimensionPizza);
                intent.putExtra("masaPizza", masaPizza);
                intent.putExtra("ingredientes", ingredientes);
                startActivity(intent);
                finish();
                finish();

            }
        });
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
