package com.example.usuario.pedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class ActivityAdicional extends AppCompatActivity {

    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicional);

        btnNext = findViewById(R.id.btnNext);

        Bundle extras = getIntent().getExtras();
        if(extras == null){
            return;
        }
        final String tipoPizza = extras.getString("seleccionPizza");
        final String dimensionPizza = extras.getString("dimensionPizza");
        final String masaPizza = extras.getString("masaPizza");
        final String ingredientes = extras.getString("ingredientes");

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ingredientesMaximo = 0;
                String ingredientesExtras = "";
                CheckBox cbQueso = findViewById(R.id.cbQueso);
                CheckBox cbChorizo = findViewById(R.id.cbChorizo);
                CheckBox cbJamonYork = findViewById(R.id.cbJamonYork);
                CheckBox cbJamon = findViewById(R.id.cbJamon);
                CheckBox cbPollo = findViewById(R.id.cbPollo);
                CheckBox cbTernera = findViewById(R.id.cbTernera);
                CheckBox cbBarbacoa = findViewById(R.id.cbBarbacoa);
                CheckBox cbCarbonara = findViewById(R.id.cbCarbonara);

                if(cbQueso.isChecked()){
                    ingredientesMaximo++;
                    ingredientesExtras+="Queso, ";
                }

                if(cbCarbonara.isChecked()){
                    ingredientesMaximo++;
                    ingredientesExtras+="Carbonara, ";
                }

                if(cbBarbacoa.isChecked()){
                    ingredientesMaximo++;
                    ingredientesExtras+="Barbacoa, ";
                }

                if(cbChorizo.isChecked()){
                    ingredientesMaximo++;
                    ingredientesExtras+="Chorizo, ";
                }

                if(cbJamon.isChecked()){
                    ingredientesMaximo++;
                    ingredientesExtras+="Jamon, ";
                }

                if(cbJamonYork.isChecked()){
                    ingredientesMaximo++;
                    ingredientesExtras+="Jamon York, ";
                }

                if(cbPollo.isChecked()){
                    ingredientesMaximo++;
                    ingredientesExtras+="Pollo, ";
                }

                if(cbTernera.isChecked()){
                    ingredientesMaximo++;
                    ingredientesExtras+="Ternera, ";
                }

                if(ingredientesMaximo>2){
                    Toast.makeText(getApplicationContext(), "Elimina ingredientes", Toast.LENGTH_LONG).show();
                }else{
                    ingredientesExtras = ingredientesExtras.substring(0,ingredientesExtras.length()-2);
                    Intent intent = new Intent(getApplicationContext(), ActivityConfirmacion.class);
                    intent.putExtra("seleccionPizza",tipoPizza);
                    intent.putExtra("dimensionPizza", dimensionPizza);
                    intent.putExtra("masaPizza", masaPizza);
                    intent.putExtra("ingredientes", ingredientes);
                    intent.putExtra("ingredientesExtras", ingredientesExtras);
                    finish();
                    startActivity(intent);
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
