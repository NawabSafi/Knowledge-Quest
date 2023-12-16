package com.example.semesterproject.Retrofit;

import android.provider.ContactsContract;

import java.util.ArrayList;

public class Model {
String page;
String per_page;
String total;
String total_pages;
ArrayList<Data> data;

public class Data{
    String id;
    String email;
    String first_name;
    String last_name;
    String avatar;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAvatar() {
        return avatar;
    }
}

    public String getPage() {
        return page;
    }

    public String getPer_page() {
        return per_page;
    }

    public String getTotal() {
        return total;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public ArrayList<Data> getData() {
        return data;
    }
}
