package com.example.bookunion;

public class DatasetFireUser {

    String user_address;
    String user_email;
    String user_number;
    String user_name;
    String user_dummy;

    public String getUser_number() {
        return user_number;
    }

    public String getUser_dummy() {
        return user_dummy;
    }

    public void setUser_dummy(String user_dummy) {
        this.user_dummy = user_dummy;
    }

    public DatasetFireUser(String user_address, String user_e_mail, String user_mobile_number, String user_name, String user_dummy) {
        this.user_address = user_address;
        this.user_email = user_e_mail;
        this.user_number = user_mobile_number;
        this.user_name = user_name;
        this. user_dummy = user_dummy;
    }

    public DatasetFireUser() {
    }

    public String getUser_address() {
        return user_address;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public void setUser_e_mail(String user_e_mail) {
        this.user_email = user_e_mail;
    }

    public void setUser_mobile_number(String user_mobile_number) {
        this.user_number = user_mobile_number;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
