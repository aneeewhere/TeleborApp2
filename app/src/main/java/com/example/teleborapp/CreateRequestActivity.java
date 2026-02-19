package com.example.teleborapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CreateRequestActivity extends AppCompatActivity {

    private EditText requestText;
    private Button sendButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);

        requestText = findViewById(R.id.requestText);
        sendButton = findViewById(R.id.sendButton);
        cancelButton = findViewById(R.id.cancelButton);

        sendButton.setOnClickListener(v -> {
            String text = requestText.getText().toString().trim();
            if (text.isEmpty()) {
                Toast.makeText(this, "Введите текст заявки", Toast.LENGTH_SHORT).show();
                return;
            }

            String userEmail = getIntent().getStringExtra("user_email");
            if (userEmail == null) userEmail = "unknown@test.com";

            RequestStorage.addRequest(text, userEmail);

            Intent data = new Intent();
            data.putExtra("request_text", text);
            setResult(RESULT_OK, data);

            Toast.makeText(this, "Заявка отправлена", Toast.LENGTH_SHORT).show();
            finish();
        });

        cancelButton.setOnClickListener(v -> finish());
    }
}