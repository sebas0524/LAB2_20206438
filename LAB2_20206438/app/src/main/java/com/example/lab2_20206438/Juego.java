
package com.example.lab2_20206438;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
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
    long tiempoInicio;
    TextView txtMensaje;
    boolean juegoIniciado = false;

    public static List<String> historialResultados = new ArrayList<>();

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

        for (int i = 0; i < 12; i++) {
            String buttonID = "btn" + (i + 1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            palabraButtons[i] = findViewById(resID);
        }

        btnJugar = findViewById(R.id.btnJugar);
        btnBack = findViewById(R.id.btnBack);
        btnExpand = findViewById(R.id.btnExpand);
        txtMensaje = findViewById(R.id.txtMensaje);

        String tema = getIntent().getStringExtra("tema");
        Random random = new Random();

        // Aquí se selecciona la oración correcta, pero no la usamos hasta presionar el botón "Jugar"
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

        // Botones empiezan vacíos y deshabilitados
        for (Button btn : palabraButtons) {
            btn.setText("");
            btn.setTextColor(Color.BLACK);
            btn.setEnabled(false);
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
        }


        btnJugar.setOnClickListener(v -> {
            if (!juegoIniciado) {
                iniciarJuego();
                btnJugar.setText("Nuevo Juego");
                juegoIniciado = true;
            } else {
                if (intento < 3) {
                    historialResultados.add("Juego " + (historialResultados.size() + 1) + ": Canceló");
                    Toast.makeText(this, "Juego Cancelado", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(Juego.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });




        btnBack.setOnClickListener(v -> finish());
        btnExpand.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(this, btnExpand);
            popup.getMenuInflater().inflate(R.menu.estadisticas, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.menu_estadisticas) {
                    startActivity(new Intent(this, Estadisticas.class));
                    return true;
                }
                return false;
            });
            popup.show();
        });

    }

    private void iniciarJuego() {
        seleccionUsuario.clear();
        intento = 0;
        tiempoInicio = System.currentTimeMillis();

        List<String> palabras = Arrays.asList(oracionCorrecta.clone());
        Collections.shuffle(palabras);

        for (int i = 0; i < palabraButtons.length; i++) {
            Button btn = palabraButtons[i];
            String palabra = palabras.get(i);

            btn.setText("");
            btn.setEnabled(true);
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));

            int finalI = i;
            btn.setOnClickListener(v -> {
                btn.setText(palabra);
                btn.setEnabled(false);
                btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#90CAF9")));
                seleccionUsuario.add(palabra);

                int indexSeleccion = seleccionUsuario.size() - 1;
                if (!palabra.equals(oracionCorrecta[indexSeleccion])) {
                    intento++;
                    int intentosRestantes = 3 - intento;

                    if (intento >= 3) {
                        long tiempoFin = System.currentTimeMillis();
                        long duracionSegundos = (tiempoFin - tiempoInicio) / 1000;
                        String mensaje = "Perdió  / Terminó en " + duracionSegundos + " segundos.";
                        mostrarMensaje(mensaje);
                        historialResultados.add("Juego " + (historialResultados.size() + 1) + ": " + mensaje);
                        bloquearBotones();
                    } else {
                        mostrarMensaje("Te quedan " + intentosRestantes + " intento(s)");
                        new android.os.Handler().postDelayed(this::reiniciarIntento, 1000);
                    }

                } else {
                    if (seleccionUsuario.size() == oracionCorrecta.length) {
                        long tiempoFin = System.currentTimeMillis();
                        long duracionSegundos = (tiempoFin - tiempoInicio) / 1000;
                        String mensaje = "¡Ganó!  / Terminó en " + duracionSegundos + " segundos - Intentos: " + intento;
                        mostrarMensaje(mensaje);
                        historialResultados.add("Juego " + (historialResultados.size() + 1) + ": " + mensaje);
                        bloquearBotones();
                    }
                }
            });
        }
    }




    private void reiniciarIntento() {
        seleccionUsuario.clear();
        for (Button btn : palabraButtons) {
            btn.setText("");
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
        txtMensaje.setText(mensaje);
    }

}
