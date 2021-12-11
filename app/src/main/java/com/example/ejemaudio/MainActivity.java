package com.example.ejemaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button reproducir, pausa, detener;
    MediaPlayer mp;
    boolean pausado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inicializar el objeto MediaPlayer
        mp = MediaPlayer.create(this, R.raw.track1);

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                reproducir.setEnabled(true);
                pausa.setEnabled(false); pausa.setText("Pausa");
                detener.setEnabled(false);
                mp.stop();
            }

        });

        inicializarBotones();
    }

    private void inicializarBotones() {

        reproducir = findViewById(R.id.reproducir);
        pausa = findViewById(R.id.pausa);
        detener = findViewById(R.id.detener);

//        reproducir = (Button)findViewById(R.id.reproducir);
//        pausa = (Button)findViewById(R.id.pausa);
//        detener = (Button)findViewById(R.id.detener);

        reproducir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // habilitar los botones Pausa y Detener, deshabilitar el botón Reproducir
                reproducir.setEnabled(false);
                pausa.setEnabled(true);
                detener.setEnabled(true);

                // iniciar la reproducción del clip de audio
                mp.seekTo(0);
                mp.start();
            }
        });

        pausa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // pausar o reanudar la reproducción del audio
                // cambiar el texto del botón a Reanudar o Pausa según corresponda
                if (pausado) {
                    pausa.setText("Pausa");
                    mp.start();
                    pausado = false;
                } else {
                    pausa.setText("Reanudar");
                    mp.pause();
                    pausado = true;
                }

            }
        });

        detener.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // habilitar el botón Reproducir, deshabilitar los botones Pausa y Detener
                // volver a poner como texto del botón de pausa la cadena Pausa
                try {
                    reproducir.setEnabled(true);
                    pausa.setEnabled(false);
                    pausa.setText("Pausa");
                    detener.setEnabled(false);
                    mp.stop();
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // liberar los recursos asociados al objeto MediaPlayer
        mp.release();
    }

}