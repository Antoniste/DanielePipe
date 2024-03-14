import org.example.AddressBook;
import org.example.Contact;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.*;

public class AddressBookTest {

    @Test
    public void addNewUserTest() {

        Contact contact = new Contact("Lucia", "123456789", "1");

        AddressBook addressBook = new AddressBook();
        addressBook.addContact(contact.getName(), contact.getPhoneNumber(), contact.getId());
        addressBook.printAddressBook();

        assertTrue(contact.getName().contains("Lucia"));
    }

    @Test
    public void addAndUpdateUserTest() {
        Contact contact = new Contact("Matteo", "123456789", "2");
        AddressBook addressBook = new AddressBook();
        addressBook.addContact(contact.getName(), contact.getPhoneNumber(), contact.getId());
        String newPhoneNumber = "987654321";
        addressBook.updateContact(contact.getName(), newPhoneNumber);
        addressBook.printAddressBook();
    }

    @Test
    public void addAndUpdateAndRemoveUserTest() {

        Contact contact = new Contact("Sara", "123456789", "3");
        AddressBook addressBook = new AddressBook();
        addressBook.addContact(contact.getName(), contact.getPhoneNumber(), contact.getId());
        addressBook.deleteContact(contact.getName());
        Contact removedContact = addressBook.findContact(contact.getName());
        assertNull(removedContact);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNonExistentContact() {
        AddressBook addressBook = new AddressBook();
        addressBook.updateContact("Maria", "111111111");
    }



    @Test
    public void testDeleteNonExistentContact() {
        AddressBook addressBook = new AddressBook();
        boolean deleteSuccess = addressBook.deleteContact("Giuseppe");
        assertFalse("Expected deleteContact to return false for a non-existent contact", deleteSuccess);
    }
}

