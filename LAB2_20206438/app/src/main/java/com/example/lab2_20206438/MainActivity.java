package com.example.lab2_20206438;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnSoftware, btnCiberseguridad, btnOpticas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_inicio);

        // Botones de la vista de inicio
        btnSoftware = findViewById(R.id.btnSoftware);
        btnCiberseguridad = findViewById(R.id.btnCiberseguridad);
        btnOpticas = findViewById(R.id.btnOpticas);

        // Software
        btnSoftware.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Juego.class);
            intent.putExtra("tema", "software");
            startActivity(intent);
        });

        //  Ciberseguridad
        btnCiberseguridad.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Juego.class);
            intent.putExtra("tema", "ciberseguridad");
            startActivity(intent);
        });

        // Opticas
        btnOpticas.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Juego.class);
            intent.putExtra("tema", "opticas");
            startActivity(intent);
        });
    }
}