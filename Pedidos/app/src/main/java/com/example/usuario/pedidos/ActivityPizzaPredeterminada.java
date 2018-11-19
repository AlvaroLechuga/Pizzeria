package com.example.usuario.pedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class ActivityPizzaPredeterminada extends AppCompatActivity {


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_predeterminada);

        Bundle extras = getIntent().getExtras();
        if(extras == null){
            return;
        }
        final String tipoPizza = extras.getString("seleccionPizza");
        final String dimensionPizza = extras.getString("dimensionPizza");
        final String masaPizza = extras.getString("masaPizza");

        listView = findViewById(R.id.lvPizzas);

        final ArrayList<String> pizzas = new ArrayList<>();
        pizzas.add("Barbacoa");
        pizzas.add("Carbonara");
        pizzas.add("Bacon");
        pizzas.add("Iberica");
        pizzas.add("Vegetal");
        pizzas.add("Atun");
        pizzas.add("Americana");
        pizzas.add("Margarita");
        pizzas.add("4 quesos");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pizzas);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), ActivityAdicional.class);
                intent.putExtra("seleccionPizza",tipoPizza);
                intent.putExtra("dimensionPizza", dimensionPizza);
                intent.putExtra("masaPizza", masaPizza);
                intent.putExtra("ingredientes", pizzas.get(position).toString());
                startActivity(intent);
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
