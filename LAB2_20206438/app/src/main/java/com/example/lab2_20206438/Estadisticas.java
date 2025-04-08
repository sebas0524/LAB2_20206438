package com.example.lab2_20206438;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Estadisticas extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_estadisticas);

        // Aquí muestras estadísticas con TextViews o lo que quieras
        LinearLayout layoutHistorial = findViewById(R.id.layoutHistorial);

        for (String resultado : Juego.historialResultados) {
            TextView txt = new TextView(this);
            txt.setText(resultado);
            txt.setTextSize(16);
            txt.setPadding(8, 8, 8, 8);
            layoutHistorial.addView(txt);
        }
    }
}