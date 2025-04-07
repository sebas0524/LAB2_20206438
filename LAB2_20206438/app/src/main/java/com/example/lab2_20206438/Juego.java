package com.example.lab2_20206438;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class Juego extends AppCompatActivity {

    Button[] palabraButtons = new Button[12];
    Button btnJugar;
    ImageButton btnBack, btnExpand;

    String[] oracionCorrecta;
    List<String> seleccionUsuario = new ArrayList<>();
    int intento = 0;

    // Oraciones por temática
    String[][] oracionesSoftware = {
            {"La", "fibra", "óptica", "envía", "datos", "a", "gran", "velocidad", "evitando", "cualquier", "interferencia", "eléctrica"},
            {"Los", "amplificadores", "EDFA", "mejoran", "la", "señal", "óptica", "en", "redes", "de", "larga", "distancia"}
    };

    String[][] oracionesCiberseguridad = {
            {"Una", "VPN", "encripta", "tu", "conexión", "para", "navegar", "de", "forma", "anónima", "y", "segura"},
            {"El", "ataque", "DDoS", "satura", "servidores", "con", "tráfico", "falso", "y", "causa", "caídas", "masivas"}
    };

    String[][] oracionesOpticas = {
            {"Los", "fragments", "reutilizan", "partes", "de", "pantalla", "en", "distintas", "actividades", "de", "la", "app"},
            {"Los", "intents", "permiten", "acceder", "a", "apps", "como", "la", "cámara", "o", "WhatsApp", "directamente"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_juego);

        // Asignación de botones
        for (int i = 0; i < 12; i++) {
            String buttonID = "btn" + (i + 1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            palabraButtons[i] = findViewById(resID);
        }

        btnJugar = findViewById(R.id.btnJugar);
        btnBack = findViewById(R.id.btnBack);
        btnExpand = findViewById(R.id.btnExpand);

        // Obtener tema
        String tema = getIntent().getStringExtra("tema");
        Random random = new Random();

        switch (tema) {
            case "software":
                oracionCorrecta = oracionesSoftware[random.nextInt(oracionesSoftware.length)];
                break;
            case "ciberseguridad":
                oracionCorrecta = oracionesCiberseguridad[random.nextInt(oracionesCiberseguridad.length)];
                break;
            case "opticas":
                oracionCorrecta = oracionesOpticas[random.nextInt(oracionesOpticas.length)];
                break;
            default:
                oracionCorrecta = new String[12];
        }

        // Desordenar y mostrar en botones
        List<String> palabras = Arrays.asList(oracionCorrecta.clone());
        Collections.shuffle(palabras);

        // Inicializar los botones con palabras ocultas
        for (int i = 0; i < palabraButtons.length; i++) {
            Button btn = palabraButtons[i];
            btn.setText("");  // Palabra oculta inicialmente
            btn.setEnabled(true);
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));

            int finalI = i;
            btn.setOnClickListener(v -> {
                String palabra = palabras.get(finalI);
                btn.setText(palabra);  // Mostrar la palabra al hacer clic
                seleccionUsuario.add(palabra);
                btn.setEnabled(false);
                btn.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));

                // Verificar si la secuencia es correcta
                if (seleccionUsuario.size() == oracionCorrecta.length) {
                    if (validarSecuencia(seleccionUsuario, oracionCorrecta)) {
                        mostrarMensaje("¡Ganaste! 🎉");
                        bloquearBotones();
                    } else {
                        intento++;
                        if (intento >= 3) {
                            mostrarMensaje("Sin intentos 😞. Intenta de nuevo.");
                            bloquearBotones();
                        } else {
                            mostrarMensaje("Incorrecto. Intento " + intento + "/3");
                            reiniciarIntento();
                        }
                    }
                }
            });
        }

        btnJugar.setOnClickListener(v -> reiniciarIntento()); // Reiniciar desde botón si quieres

        btnBack.setOnClickListener(v -> finish()); // volver atrás
    }

    private boolean validarSecuencia(List<String> seleccion, String[] correcta) {
        for (int i = 0; i < correcta.length; i++) {
            if (!seleccion.get(i).equals(correcta[i])) return false;
        }
        return true;
    }

    private void reiniciarIntento() {
        seleccionUsuario.clear();
        for (Button btn : palabraButtons) {
            btn.setText("");  // Volver a ocultar las palabras
            btn.setEnabled(true);
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
        }
    }

    private void bloquearBotones() {
        for (Button btn : palabraButtons) {
            btn.setEnabled(false);
        }
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
