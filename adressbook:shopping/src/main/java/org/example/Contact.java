package org.example;
public class Contact {
        private String name;
        private String phoneNumber;
        private String id;

        public Contact(String name, String phoneNumber, String id) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void updateContact(String newName, String newPhoneNumber) {
            if (newName != null && !newName.isEmpty()) {
                this.name = newName;
            }
            if (newPhoneNumber != null && !newPhoneNumber.isEmpty()) {
                this.phoneNumber = newPhoneNumber;
            }
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "name='" + name + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }