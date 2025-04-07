package com.example.lab2_20206438;

import android.os.Bundle;
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

        // Luego puedes agregar acciones aquí si quieres
        // Por ejemplo:
        /*
        btnSoftware.setOnClickListener(v -> {
            // Acción para Software
        });
        */
    }
}