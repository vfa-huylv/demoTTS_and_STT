package com.example.demotss_stt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class TTSActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    EditText etContent;
    Button btnSpeak;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttsactivity);

        etContent = findViewById(R.id.etContent);
        btnSpeak = findViewById(R.id.btnSpeak);

        tts = new TextToSpeech(this, this);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speakOut();
            }
        });
    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This language is not supported!!!");
            } else {
                btnSpeak.setEnabled(true);
            }
        } else {
            Log.e("TTS", "Initialization Failed!");
        }
    }

    @Override
    public void onDestroy() {
        if (tts!=null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    public void speakOut() {
        String content = etContent.getText().toString();
        tts.speak(content, TextToSpeech.QUEUE_FLUSH, null, null);
    }
}