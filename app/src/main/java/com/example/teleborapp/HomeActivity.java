package com.example.teleborapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView fioText, addressText, phoneText, tariffText, supportText;
    private Button editButton, changeTariffButton, createRequestButton, logoutButton;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();

        userEmail = getIntent().getStringExtra("user_email");
        if (userEmail != null) {
        }

        editButton.setOnClickListener(v ->
                startActivityForResult(new Intent(this, EditAbonentActivity.class)
                        .putExtra("fio", fioText.getText())
                        .putExtra("address", addressText.getText())
                        .putExtra("phone", phoneText.getText()), 1));

        changeTariffButton.setOnClickListener(v ->
                startActivityForResult(new Intent(this, ChangeTariffActivity.class)
                        .putExtra("current_tariff", tariffText.getText()), 2));

        createRequestButton.setOnClickListener(v ->
                startActivityForResult(new Intent(this, CreateRequestActivity.class)
                        .putExtra("user_email", userEmail), 3));

        logoutButton.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    private void initViews() {
        fioText = findViewById(R.id.fioText);
        addressText = findViewById(R.id.addressText);
        phoneText = findViewById(R.id.phoneText);
        editButton = findViewById(R.id.editButton);
        tariffText = findViewById(R.id.tariffNameText);
        changeTariffButton = findViewById(R.id.changeTariffButton);
        supportText = findViewById(R.id.supportStatusText);
        createRequestButton = findViewById(R.id.createRequestButton);
        logoutButton = findViewById(R.id.logoutButton);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case 1:
                    fioText.setText(data.getStringExtra("fio"));
                    addressText.setText(data.getStringExtra("address"));
                    phoneText.setText(data.getStringExtra("phone"));
                    Toast.makeText(this, "Данные обновлены", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    String newTariff = data.getStringExtra("tariff_name");
                    tariffText.setText(newTariff);
                    Toast.makeText(this, "Тариф изменен", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    String text = data.getStringExtra("request_text");
                    supportText.setText(text.length() > 30 ?
                            "Заявка: " + text.substring(0, 27) + "..." : "Заявка: " + text);
                    Toast.makeText(this, "Заявка отправлена", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}