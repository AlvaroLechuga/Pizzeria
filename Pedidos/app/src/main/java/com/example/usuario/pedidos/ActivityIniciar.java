package com.example.usuario.pedidos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityIniciar extends AppCompatActivity {

    Button btnIniciar;
    EditText txtNombre;
    EditText txtPasswd;
    CheckBox cbRecordar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

        btnIniciar = findViewById(R.id.btnIniciar);
        txtNombre = findViewById(R.id.txtNombre);
        txtPasswd = findViewById(R.id.txtPasswd);
        cbRecordar = findViewById(R.id.cbRecordar);

        SharedPreferences prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        Boolean check = prefs.getBoolean("marcado", false);
        String correo = prefs.getString("email", "");
        String passw = prefs.getString("passwd", "");

        if(check){
            txtNombre.setText(correo);
            txtPasswd.setText(passw);
        }

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbRecordar.isChecked() == true){
                    SharedPreferences prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("email", txtNombre.getText().toString());
                    editor.putString("passwd", txtPasswd.getText().toString());
                    editor.putBoolean("marcado", true);
                    editor.commit();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    startActivity(intent);
                }else{
                    SharedPreferences prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("email", "");
                    editor.putString("passwd", "");
                    editor.putBoolean("marcado", false);
                    editor.commit();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    startActivity(intent);
                }

            }
        });
    }
}
