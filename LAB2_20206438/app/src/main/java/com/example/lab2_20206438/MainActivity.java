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

        // Referenciar botones
        btnSoftware = findViewById(R.id.btnSoftware);
        btnCiberseguridad = findViewById(R.id.btnCiberseguridad);
        btnOpticas = findViewById(R.id.btnOpticas);

        // Acción para Software
        btnSoftware.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Juego.class);
            intent.putExtra("tema", "software");  // Pasar el tema seleccionado
            startActivity(intent);
        });

        // Acción para Ciberseguridad
        btnCiberseguridad.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Juego.class);
            intent.putExtra("tema", "ciberseguridad");  // Pasar el tema seleccionado
            startActivity(intent);
        });

        // Acción para Opticas
        btnOpticas.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Juego.class);
            intent.putExtra("tema", "opticas");  // Pasar el tema seleccionado
            startActivity(intent);
        });
    }
}