package com.example.teleborapp;

import java.util.ArrayList;
import java.util.List;

public class RequestStorage {
    private static List<Request> requests = new ArrayList<>();

    public static class Request {
        private String text;
        private String userEmail;
        private long timestamp;

        public Request(String text, String userEmail) {
            this.text = text;
            this.userEmail = userEmail;
            this.timestamp = System.currentTimeMillis();
        }

        public String getText() { return text; }
        public String getUserEmail() { return userEmail; }
        public long getTimestamp() { return timestamp; }
    }

    public static void addRequest(String text, String userEmail) {
        requests.add(new Request(text, userEmail));
    }

    public static List<Request> getRequests() {
        return new ArrayList<>(requests);
    }
}