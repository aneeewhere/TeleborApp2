package com.example.teleborapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditAbonentActivity extends AppCompatActivity {

    private EditText fioEdit, addressEdit, phoneEdit;
    private Button saveButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_abonent);

        fioEdit = findViewById(R.id.fioEditText);
        addressEdit = findViewById(R.id.addressEditText);
        phoneEdit = findViewById(R.id.phoneEditText);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);

        fioEdit.setText(getIntent().getStringExtra("fio"));
        addressEdit.setText(getIntent().getStringExtra("address"));
        phoneEdit.setText(getIntent().getStringExtra("phone"));

        saveButton.setOnClickListener(v -> {
            String fio = fioEdit.getText().toString();
            String address = addressEdit.getText().toString();
            String phone = phoneEdit.getText().toString();

            if (fio.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent data = new Intent();
            data.putExtra("fio", fio);
            data.putExtra("address", address);
            data.putExtra("phone", phone);
            setResult(RESULT_OK, data);
            finish();
        });

        cancelButton.setOnClickListener(v -> finish());
    }
}