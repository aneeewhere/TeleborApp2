package com.example.teleborapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }

            if (email.equals("abonent") && password.equals("abonent1")) {
                Toast.makeText(MainActivity.this, "Добро пожаловать!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("user_email", email);
                intent.putExtra("user_type", "user");
                startActivity(intent);
                finish();
            }
            else if (email.equals("support") && password.equals("support1")) {
                Toast.makeText(MainActivity.this, "Вход в техподдержку", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SupportActivity.class);
                startActivity(intent);
                finish();
            }
            else if (email.equals("admin") && password.equals("admin1")) {
                Toast.makeText(MainActivity.this, "Вход как администратор", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                intent.putExtra("admin_email", email);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(MainActivity.this, "Неверный email или пароль", Toast.LENGTH_SHORT).show();
                passwordEditText.setText("");
            }
        });
    }
}