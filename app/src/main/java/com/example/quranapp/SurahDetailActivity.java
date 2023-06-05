package com.example.quranapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class SurahDetailActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView contentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.li);

        titleTextView = findViewById(R.id.title_text_view);
        contentTextView = findViewById(R.id.content_text_view);

        // Retrieve Surah title and content from the intent
        Intent intent = getIntent();
        if (intent != null) {
            String surahTitle = intent.getStringExtra("surahTitle");
            String surahContent = intent.getStringExtra("surahContent");

            if (surahTitle != null && surahContent != null) {
                titleTextView.setText(surahTitle);
                contentTextView.setText(surahContent);
            }
        }
    }
}
