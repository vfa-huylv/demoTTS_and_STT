package com.example.demotss_stt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.Locale;

public class TTSActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    EditText etContent;
    Button btnSpeak, btnSelectLanguage, btnBack;
    TextToSpeech tts;
    RadioGroup rdLanguageGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttsactivity);

        etContent = findViewById(R.id.etContent);
        btnSpeak = findViewById(R.id.btnSpeak);
        rdLanguageGroup = findViewById(R.id.radioGroup);
        btnSelectLanguage = findViewById(R.id.btnSelect);
        btnBack = findViewById(R.id.btnBack);

        tts = new TextToSpeech(this, this);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speakOut();
            }
        });

        btnSelectLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rdLanguageGroup.getCheckedRadioButtonId();
                RadioButton selectedButton = (RadioButton) findViewById(selectedId);

                String lang = selectedButton.getText().toString();

                switch (lang) {
                    case "US":
                        tts.setLanguage(Locale.US);
                        break;
                    case "JP":
                        tts.setLanguage(Locale.JAPANESE);
                        break;
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TTSActivity.this, MainActivity.class);
                startActivity(intent);
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
        Bundle params = new Bundle();
        params.putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, 1.0f);
        tts.speak(content, TextToSpeech.QUEUE_FLUSH, params, null);
    }
}