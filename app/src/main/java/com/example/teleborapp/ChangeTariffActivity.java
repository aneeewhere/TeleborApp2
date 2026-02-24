package com.example.teleborapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ChangeTariffActivity extends AppCompatActivity {

    private TextView currentTariffText;
    private Spinner tariffSpinner;
    private Button confirmButton, cancelButton;
    private String selectedTariff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_tariff);

        currentTariffText = findViewById(R.id.currentTariffText);
        tariffSpinner = findViewById(R.id.tariffSpinner);
        confirmButton = findViewById(R.id.confirmButton);
        cancelButton = findViewById(R.id.cancelButton);

        currentTariffText.setText(getIntent().getStringExtra("current_tariff"));

        ArrayList<String> tariffs = new ArrayList<>();
        tariffs.add("Базовый");
        tariffs.add("Оптимальный");
        tariffs.add("Максимальный");
        tariffs.add("Премиум");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, tariffs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tariffSpinner.setAdapter(adapter);

        tariffSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent,
                                       android.view.View view, int position, long id) {
                selectedTariff = tariffs.get(position);
            }
            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });

        confirmButton.setOnClickListener(v -> {
            if (selectedTariff == null) {
                Toast.makeText(this, "Выберите тариф", Toast.LENGTH_SHORT).show();
                return;
            }
            if (selectedTariff.equals(currentTariffText.getText().toString())) {
                Toast.makeText(this, "Это ваш текущий тариф", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent data = new Intent();
            data.putExtra("tariff_name", selectedTariff);
            setResult(RESULT_OK, data);
            finish();
        });

        cancelButton.setOnClickListener(v -> finish());
    }
}