package com.example.demotss_stt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnTTS, btnSTT, btnParse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTTS = findViewById(R.id.btnTTS);
        btnSTT = findViewById(R.id.btnSTT);
        btnParse = findViewById(R.id.btnParse);

        btnTTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TTSActivity.class);
                startActivity(i);
            }
        });

        btnSTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, STTActivity.class);
                startActivity(i);
            }
        });

        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ParseJsonHtmlToObject.class);
                startActivity(i);
            }
        });
    }
}