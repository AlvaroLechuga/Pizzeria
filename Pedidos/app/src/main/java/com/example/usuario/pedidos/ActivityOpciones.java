package com.example.usuario.pedidos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ActivityOpciones extends AppCompatActivity {

    RelativeLayout fondoOpciones;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        fondoOpciones = findViewById(R.id.fondoOpciones);
        listView = findViewById(R.id.lvOpciones);

        final ArrayList<String> opciones = new ArrayList<>();

        opciones.add("Cambiar Color Fondo");
        opciones.add("Configurar Pizza Favorita");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, opciones);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        openDialog(true);
                        break;
                    case 1:
                        Intent intent= new Intent (getApplicationContext(), ActivityRealizarFavorita.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        SharedPreferences prefs = getSharedPreferences("Opciones",Context.MODE_PRIVATE);
        int color = prefs.getInt("colorFondo", 0);
        fondoOpciones.setBackgroundColor(color);
    }

    private void openDialog(boolean b) {

        AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, Color.RED, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                SharedPreferences prefs = getSharedPreferences("Opciones",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("colorFondo", color);
                fondoOpciones.setBackgroundColor(color);
                editor.commit();
            }

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                Toast.makeText(getApplicationContext(), "Accion Cancelada!", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

}

