package com.example.lab2_20206438;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Estadisticas extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_estadisticas);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
        Button btnNuevoJuego = findViewById(R.id.btnNuevoJuego);
        btnNuevoJuego.setOnClickListener(v -> {
            Intent intent = new Intent(Estadisticas.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        LinearLayout layoutHistorial = findViewById(R.id.layoutHistorial);

        for (String resultado : Juego.historialResultados) {
            TextView txt = new TextView(this);
            txt.setText(resultado);
            txt.setTextSize(16);
            txt.setTextColor(Color.BLACK);
            txt.setPadding(8, 8, 8, 8);
            layoutHistorial.addView(txt);
        }
    }
}