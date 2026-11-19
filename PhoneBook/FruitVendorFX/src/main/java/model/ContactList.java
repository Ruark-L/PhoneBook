package model;


import java.util.ArrayList;


public class ContactList {
    private String phoneBookName;
    // ParentType ArrayList storing mixed subclass objects
    private ArrayList<Contact> storage = new ArrayList<>();


    public ContactList(String phoneBookName) {
        this.phoneBookName = phoneBookName;
    }


    public ArrayList<Contact> getStorage() {
        return storage;
    }


    public void addContact(Contact contact) {
        storage.add(contact);
    }


    public void removeContact(Contact contact) {
        storage.remove(contact);
    }


    public Contact findContact(String contactName) {
        for (Contact c : storage) {
            if (c.getContactname().equalsIgnoreCase(contactName)) {
                return c;
            }
        }
        return null;
    }


    public String getStorageReport() {
        if (storage.isEmpty()) return "No contacts in list.";
        StringBuilder sb = new StringBuilder(phoneBookName + "\n\n");
        for (Contact c : storage) {
            sb.append(c).append("\n");
        }
        return sb.toString();
    }
}
