package com.example.ejemaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

public class Video extends AppCompatActivity {

    VideoView superficie;
    TextView tiempo;
    Button reproducir, detener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        superficie = (VideoView)findViewById(R.id.superficie);
        tiempo = (TextView)findViewById(R.id.tiempo);
        reproducir = (Button)findViewById(R.id.vireproducir);
        detener = (Button)findViewById(R.id.videtener);
        tiempo.setText("Duración 0");

        reproducir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Habitlitar y deshabilitar botones
                reproducir.setEnabled(false);
                detener.setEnabled(true);
                // Inicializar la reproducción de video
                superficie.setVideoURI(Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.nocontado));
            }
        });

        detener.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Habilitar y deshabilitar botones
                reproducir.setEnabled(true);
                detener.setEnabled(false);

                // Detener la ejecución del video
                superficie.stopPlayback();

                tiempo.setText("Duración 0");
            }
        });

        // Manejador del evento OnPreparedListener para comenzar
        // la reproducción y poder mostrar la duración del video
        superficie.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                superficie.start();

                int duracion = superficie.getDuration()/1000;
                int minutos = duracion/60;
                int segundos = duracion - minutos*60;
                tiempo.setText("Duración " + minutos + ":" + segundos);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}