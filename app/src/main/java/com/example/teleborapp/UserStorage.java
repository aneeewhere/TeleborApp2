package com.example.teleborapp;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private static Map<String, UserData> users = new HashMap<>();

    public static class UserData {
        private String fio;
        private String address;
        private String phone;
        private String tariff;

        public UserData(String fio, String address, String phone, String tariff) {
            this.fio = fio;
            this.address = address;
            this.phone = phone;
            this.tariff = tariff;
        }

        public String getFio() { return fio; }
        public String getAddress() { return address; }
        public String getPhone() { return phone; }
        public String getTariff() { return tariff; }

        public void setFio(String fio) { this.fio = fio; }
        public void setAddress(String address) { this.address = address; }
        public void setPhone(String phone) { this.phone = phone; }
        public void setTariff(String tariff) { this.tariff = tariff; }
    }

    static {
        users.put("user@test.com", new UserData(
                "Иванов Иван Иванович",
                "г. Москва, ул. Ленина, д. 1, кв. 1",
                "+7 (999) 123-45-67",
                "Оптимальный"
        ));

        users.put("petr@test.com", new UserData(
                "Петров Петр Петрович",
                "г. Москва, ул. Гагарина, д. 5, кв. 10",
                "+7 (999) 234-56-78",
                "Базовый"
        ));

        users.put("sidorov@test.com", new UserData(
                "Сидоров Сидор Сидорович",
                "г. Москва, ул. Пушкина, д. 10, кв. 15",
                "+7 (999) 345-67-89",
                "Максимальный"
        ));
    }

    public static Map<String, UserData> getAllUsers() {
        return new HashMap<>(users);
    }

    public static UserData getUser(String email) {
        return users.get(email);
    }

    public static void updateUser(String email, String fio, String address, String phone) {
        UserData user = users.get(email);
        if (user != null) {
            user.setFio(fio);
            user.setAddress(address);
            user.setPhone(phone);
        }
    }

    public static void updateUserTariff(String email, String tariff) {
        UserData user = users.get(email);
        if (user != null) {
            user.setTariff(tariff);
        }
    }
}