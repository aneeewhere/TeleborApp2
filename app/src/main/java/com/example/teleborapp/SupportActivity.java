package com.example.teleborapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SupportActivity extends AppCompatActivity {

    private ListView listView;
    private Button backButton;
    private RequestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        listView = findViewById(R.id.listView);
        backButton = findViewById(R.id.backButton);

        adapter = new RequestAdapter();
        listView.setAdapter(adapter);

        backButton.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private class RequestAdapter extends BaseAdapter {
        private List<RequestStorage.Request> requests = RequestStorage.getRequests();
        private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());

        @Override
        public int getCount() {
            return requests.size();
        }

        @Override
        public Object getItem(int position) {
            return requests.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_request, parent, false);
            }

            RequestStorage.Request request = requests.get(requests.size() - 1 - position); // Показываем новые сверху

            TextView emailText = convertView.findViewById(R.id.emailText);

            TextView requestText = convertView.findViewById(R.id.requestText);

            emailText.setText("От: " + request.getUserEmail());
            requestText.setText(request.getText());

            return convertView;
        }
    }
}