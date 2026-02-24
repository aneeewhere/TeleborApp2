package com.example.teleborapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AdminEditUserActivity extends AppCompatActivity {

    private TextView emailText;
    private EditText fioEdit, addressEdit, phoneEdit, tariffEdit;
    private Button saveButton, cancelButton;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_user);

        initViews();

        userEmail = getIntent().getStringExtra("user_email");
        emailText.setText("Редактирование: " + userEmail);

        UserStorage.UserData user = UserStorage.getUser(userEmail);
        if (user != null) {
            fioEdit.setText(user.getFio());
            addressEdit.setText(user.getAddress());
            phoneEdit.setText(user.getPhone());
            tariffEdit.setText(user.getTariff());
        }

        saveButton.setOnClickListener(v -> {
            String fio = fioEdit.getText().toString().trim();
            String address = addressEdit.getText().toString().trim();
            String phone = phoneEdit.getText().toString().trim();
            String tariff = tariffEdit.getText().toString().trim();

            if (fio.isEmpty() || address.isEmpty() || phone.isEmpty() || tariff.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }

            UserStorage.updateUser(userEmail, fio, address, phone);
            UserStorage.updateUserTariff(userEmail, tariff);

            Toast.makeText(this, "Данные пользователя обновлены", Toast.LENGTH_SHORT).show();
            finish();
        });

        cancelButton.setOnClickListener(v -> finish());
    }

    private void initViews() {
        emailText = findViewById(R.id.emailText);
        fioEdit = findViewById(R.id.fioEditText);
        addressEdit = findViewById(R.id.addressEditText);
        phoneEdit = findViewById(R.id.phoneEditText);
        tariffEdit = findViewById(R.id.tariffEditText);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);
    }
}