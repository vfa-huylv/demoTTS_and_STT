package com.example.demotss_stt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ParseJsonHtmlToObject extends AppCompatActivity {

    protected TextView tvTest;
    protected LinearLayout lnChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_json_html_to_object);

        tvTest = findViewById(R.id.tvTest);
        lnChat = findViewById(R.id.lnChat);

        handleString();
        handleComponent();
    }

    public void handleString() {
        String getString1 = getResources().getString(R.string.string_test_1);
        String getString2 = getResources().getString(R.string.string_test_2);
        String getString4 = getResources().getString(R.string.string_test_4);
        String getStringFromAPI3 = getResources().getString(R.string.string_test_3);

        String response = getString1 + getString2 + getStringFromAPI3 + getString4;

        String getString5 = getResources().getString(R.string.string_test_5);
        String getString6 = getResources().getString(R.string.string_test_6);
        String getString7 = getResources().getString(R.string.string_test_7);
        String getString8 = getResources().getString(R.string.string_test_8);
        String getString9 = getResources().getString(R.string.string_test_9);
        String getString10 = getResources().getString(R.string.string_test_10);


        String response2 = getString5 + getString6 + getString7 + getString8 + getString9 + getString10;

        String getButton8 = getResources().getString(R.string.string_button_8);
        String getButton9 = getResources().getString(R.string.string_button_9);


        String checkbox = getResources().getString(R.string.checkbox);
        //tvTest.setText(Html.fromHtml(checkbox));

        Log.d("stringAPI", getStringFromAPI3);
        Log.d("stringAPI", getString1);
        Log.d("stringAPI", getString2);
        Log.d("stringAPI", getString4);
        Log.d("stringAPI", response);
        Log.d("stringAPI", response2);
        Log.d("stringAPI_button", getButton9);
        Log.d("checkbox", checkbox);
    }

    private void handleComponent() {
        String exampleCheckbox = getResources().getString(R.string.checkbox);
        String exampleButton = getResources().getString(R.string.button);
        String input = "input";
        String checkbox = "checkbox";
        String button = "button";
        String label = "label";
        if(exampleCheckbox.contains(input)) {
            if(exampleCheckbox.contains(checkbox)) {
                String content = exampleCheckbox.substring(exampleCheckbox.indexOf(">") + 1);
                Log.d("Content", "checkbox: " + content);
                CheckBox cb = new CheckBox(this);
                cb.setText(content);
                cb.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                // Add Checkbox to LinearLayout
                if (lnChat != null) {
                    lnChat.addView(cb);
                }

            }
            // button
            if (exampleButton.contains(button)) {
                int index = 0;
                int index1 = 0;
                String content ="";
                for (int i = 0; i < countMatches(exampleButton, label); i++) {
                    index = exampleButton.indexOf(label, index1);
                    if (index != -1) {
                        Log.d("string_length", String.valueOf(exampleButton.length()));
                        content = exampleButton.substring(index + label.length() + 3,  exampleButton.indexOf("text", index) - 4);
                        index1 = index + label.length();
                    }
                    Button btn = new Button(this);
                    btn.setText(content);
                    btn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                    // Add Checkbox to LinearLayout
                    if (lnChat != null) {
                        lnChat.addView(btn);
                    }
                }
            }
            String exampleImageButton = getResources().getString(R.string.image_button);
            String a = "a";
            String href = "href";
            String img = "img";
            String src = "src";
            // image button
            if (exampleImageButton.contains(a) & exampleImageButton.contains(href)) {
                if (exampleImageButton.contains(img) & exampleImageButton.contains(src)) {
                    String hrefUrl, srcImg = "";
                    int indexUrl = exampleImageButton.indexOf(href, 0);
                    int indexImg = exampleImageButton.indexOf(src, 0);
                    hrefUrl = exampleImageButton.substring(indexUrl + href.length() + 2,  exampleImageButton.indexOf("target") - 2);
                    Log.d("Url_href", hrefUrl);
                    srcImg = exampleImageButton.substring(indexImg + src.length() + 2,  exampleImageButton.indexOf("style") - 2);
                    Log.d("Src img", srcImg);

                    ImageButton imageButton = new ImageButton(this);
                    imageButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT ));
                    new DownloadImageTask(imageButton).execute(srcImg);
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(hrefUrl)));
                        }
                    });

                    // Add Checkbox to LinearLayout
                    if (lnChat != null) {
                        lnChat.addView(imageButton);
                    }
                }
            }
        }
    }

    public static int countMatches(String text, String str)
    {
        int index = 0, count = 0;
        while (true) {
            index = text.indexOf(str, index);
            if (index != -1) {
                count ++;
                index += str.length();
            } else {
                break;
            }
        }
        return count;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}