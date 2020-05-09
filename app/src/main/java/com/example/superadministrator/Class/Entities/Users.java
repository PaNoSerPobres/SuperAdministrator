package com.example.superadministrator.Class.Entities;

import java.util.List;

    public class Users {
        private String Name;
        private String Email;
        private String Image;
        private List<String> Friends;

        public Users() {
        }

        public Users(String name, String email, String image, List<String> friends) {
            Name = name;
            Email = email;
            Image = image;
            Friends = friends;
        }


        public String getName() {
            return Name;
        }

        public String getImage() {
            return Image;
        }

        public String getEmail() {
            return Email;
        }

        public List<String> getFriends() {
            return Friends;
        }
    }

