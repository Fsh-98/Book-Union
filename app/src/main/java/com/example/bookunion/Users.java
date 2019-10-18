package com.example.bookunion;

public class Users {

        String user_name;
        String user_email;
        String user_password;
        String user_address;
        String user_number;
        String user_ID;


        public String getUser_name() {
            return user_name;
        }

        public String getUser_email() {
            return user_email;
        }

        public String getUser_password() {
            return user_password;
        }

        public String getUser_address() {
            return user_address;
        }

        public String getUser_number() {
            return user_number;
        }
        public String getUser_ID() {
            return user_ID;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public void setUser_password(String user_password) {
            this.user_password = user_password;
        }

        public void setUser_address(String user_address) {
            this.user_address = user_address;
        }

        public void setUser_number(String user_number) {
            this.user_number = user_number;
        }
        public void setUser_ID(String user_ID) {
            this.user_ID = user_ID;
    }

    public Users(String name_user, String email_user, String password_user, String address_user, String number_user, String user_ID) {
        this.user_name = name_user;
        this.user_email = email_user;
        this.user_password = password_user;
        this.user_address = address_user;
        this.user_number = number_user;
        this.user_ID = user_ID;
    }

    public Users() {
    }
}

