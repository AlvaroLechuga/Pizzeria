package com.example.usuario.pedidos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActivityDimensionesPizza extends AppCompatActivity {

    RadioGroup rgTamayo;
    RadioGroup rgMasa;
    Button btnNext;
    String medida = "";
    String masa = "";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimensiones_pizza);

        btnNext = findViewById(R.id.btnNext);
        rgTamayo = findViewById(R.id.rgDimensionesPizza);
        rgMasa = findViewById(R.id.rgMasa);

        Bundle extras = getIntent().getExtras();
        if(extras == null){
            return;
        }

        final String valor1 = extras.getString("seleccionPizza");

        rgMasa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbFina:
                        masa = "Fina";
                        break;
                    case R.id.rbGorda:
                        masa = "Gorda";
                        break;
                    case R.id.rbTradicional:
                        masa = "Tradicional";
                        break;
                    case R.id.rbTradicionalRellena:
                        masa = "Tradicional Rellena";
                        break;
                }
            }
        });

        rgTamayo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId) {
                    case R.id.rdPeque:
                        medida = "Pequeña";
                        break;
                    case R.id.rdMediana:
                        medida = "Mediana";
                        break;
                    case R.id.rdFamiliar:
                        medida = "Familiar";
                        break;
                    case R.id.rdMedio:
                        medida = "Medio-Metro";
                        break;
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (valor1){
                    case "personalizada":
                        Intent cambioActivity = new Intent(getApplicationContext(), ActivityPizzaPersonalizada.class);
                        cambioActivity.putExtra("seleccionPizza", valor1);
                        cambioActivity.putExtra("dimensionPizza", medida);
                        cambioActivity.putExtra("masaPizza", masa);
                        startActivity(cambioActivity);
                        finish();
                        break;
                    case "predeterminada":
                        Intent cambioActivit = new Intent(getApplicationContext(), ActivityPizzaPredeterminada.class);
                        cambioActivit.putExtra("seleccionPizza", valor1);
                        cambioActivit.putExtra("dimensionPizza", medida);
                        cambioActivit.putExtra("masaPizza", masa);
                        finish();
                        startActivity(cambioActivit);
                        break;
                    case "favorita":
                        Intent cambioActivi = new Intent(getApplicationContext(),ActivityAdicional.class);
                        cambioActivi.putExtra("seleccionPizza", valor1);
                        cambioActivi.putExtra("dimensionPizza", medida);
                        cambioActivi.putExtra("masaPizza", masa);
                        finish();
                        startActivity(cambioActivi);
                        break;
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
