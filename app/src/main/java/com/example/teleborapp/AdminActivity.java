package com.example.teleborapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminActivity extends AppCompatActivity {

    private ListView listView;
    private Button backButton;
    private UserAdapter adapter;
    private List<String> userEmails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listView = findViewById(R.id.listView);
        backButton = findViewById(R.id.backButton);

        Map<String, UserStorage.UserData> users = UserStorage.getAllUsers();
        userEmails = new ArrayList<>(users.keySet());

        adapter = new UserAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String email = userEmails.get(position);
            openUserEdit(email);
        });

        backButton.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    private void openUserEdit(String email) {
        Intent intent = new Intent(this, AdminEditUserActivity.class);
        intent.putExtra("user_email", email);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userEmails = new ArrayList<>(UserStorage.getAllUsers().keySet());
        adapter.notifyDataSetChanged();
    }

    private class UserAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return userEmails.size();
        }

        @Override
        public Object getItem(int position) {
            return userEmails.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_user, parent, false);
            }

            String email = userEmails.get(position);
            UserStorage.UserData user = UserStorage.getUser(email);

            TextView emailText = convertView.findViewById(R.id.emailText);
            TextView nameText = convertView.findViewById(R.id.nameText);
            TextView phoneText = convertView.findViewById(R.id.phoneText);
            TextView tariffText = convertView.findViewById(R.id.tariffText);
            Button editButton = convertView.findViewById(R.id.editButton);

            emailText.setText(email);
            nameText.setText("ФИО: " + user.getFio());
            phoneText.setText("Тел: " + user.getPhone());
            tariffText.setText("Тариф: " + user.getTariff());

            editButton.setOnClickListener(v -> openUserEdit(email));

            return convertView;
        }
    }
}