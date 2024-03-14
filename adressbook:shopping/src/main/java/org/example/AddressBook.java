package org.example;

import java.util.HashMap;
import java.util.Map;

public class AddressBook {

    private final Map<String, Contact> contacts;

    public AddressBook() {
        this.contacts = new HashMap<>();
    }

    public void updateContact(String name, String newPhoneNumber) {
        Contact contact = contacts.get(name.toLowerCase());
        if (contact != null) {
            contact.setPhoneNumber(newPhoneNumber);
        } else {
            System.out.println("Contact not found.");
            throw new IllegalArgumentException("Contact not found.");
        }
    }

    public Contact findContact(String name) {
        return contacts.get(name.toLowerCase());
    }


    public boolean deleteContact(String name) {
        Contact removedContact = contacts.remove(name.toLowerCase());
        if (removedContact != null) {
            System.out.println("Contact removed: " + removedContact);
        } else {
            System.out.println("Contact not found.");
        }
        return false;
    }

    public void printAddressBook() {
        System.out.println("Address Book contents:");
        for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }


    public void addContact(String name, String phoneNumber, String id) {
        Contact contact = new Contact(name, phoneNumber, id);
        contacts.put(name.toLowerCase(), contact);
    }
}