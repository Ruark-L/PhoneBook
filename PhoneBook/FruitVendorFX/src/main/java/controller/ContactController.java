package controller;


import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Contact;
import model.ContactList;
import model.EmergencyContact;
import model.FriendContact;
import model.WorkContact;


import java.util.ArrayList;
import java.util.List;


public class ContactController {


    // Main Table & Columns
    @FXML private TableView<Contact> tblContacts;
    @FXML private TableColumn<Contact, String> colType;
    @FXML private TableColumn<Contact, String> colNames;
    @FXML private TableColumn<Contact, String> colAddress;
    @FXML private TableColumn<Contact, String> colNumber;
    @FXML private TableColumn<Contact, String> colExtra;


    // Search Table & Columns
    @FXML private TableView<Contact> tblSContacts;
    @FXML private TableColumn<Contact, String> colSType;
    @FXML private TableColumn<Contact, String> colSNames;
    @FXML private TableColumn<Contact, String> colSAddress;
    @FXML private TableColumn<Contact, String> colSNumber;
    @FXML private TableColumn<Contact, String> colSExtra;


    // Form Inputs
    @FXML private ComboBox<String> cmbType;
    @FXML private TextField txtNames, txtAddress, txtNumber, txtExtra, txtSearch;


    private final ContactList list = new ContactList("PhoneBook");


    @FXML
    public void initialize() {
        // Populate Contact Type Dropdown
        cmbType.getItems().addAll("Friend", "Work", "Emergency");
        cmbType.setValue("Friend");


        // Bind Main Table Columns
        colType.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCategory()));
        colNames.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getContactname()));
        colAddress.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAddress()));
        colNumber.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNumber()));
        colExtra.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getExtraDetail()));


        // Bind Search Table Columns
        colSType.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCategory()));
        colSNames.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getContactname()));
        colSAddress.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAddress()));
        colSNumber.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNumber()));
        colSExtra.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getExtraDetail()));


        // Load selected row into form fields for easy Updating
        tblContacts.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, selected) -> {
            if (selected != null) {
                cmbType.setValue(selected.getCategory());
                txtNames.setText(selected.getContactname());
                txtAddress.setText(selected.getAddress());
                txtNumber.setText(selected.getNumber());
                txtExtra.setText(selected.getExtraDetail());
            }
        });


        // Seed mixed subclass instances into ArrayList<Contact>
        list.addContact(new FriendContact("Bob", "New York", "123-456-7890", "Birthday: May 12"));
        list.addContact(new WorkContact("Tom", "Cat House", "555-019-2834", "Acme Corp"));
        list.addContact(new EmergencyContact("Jerry", "Hole in Wall", "911-000-1111", "24/7 Rescue"));


        refresh();
    }


    // CREATE
    @FXML
    private void handleAdd() {
        if (!validateInputs()) return;


        String type = cmbType.getValue();
        String name = txtNames.getText().trim();
        String address = txtAddress.getText().trim();
        String number = txtNumber.getText().trim();
        String extra = txtExtra.getText().trim();


        Contact newContact = createContactByType(type, name, address, number, extra);
        list.addContact(newContact);


        clearFields();
        refresh();
    }


    // UPDATE
    @FXML
    private void handleUpdate() {
        Contact selected = tblContacts.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No Contact Selected", "Click a contact in the table first to update it.");
            return;
        }


        if (!validateInputs()) return;


        String selectedType = cmbType.getValue();
        String name = txtNames.getText().trim();
        String address = txtAddress.getText().trim();
        String number = txtNumber.getText().trim();
        String extra = txtExtra.getText().trim();


        // If user kept the same subclass type, update fields in place; otherwise replace subclass instance
        if (selected.getCategory().equals(selectedType)) {
            selected.setContactname(name);
            selected.setAddress(address);
            selected.setNumber(number);
            selected.setExtraDetail(extra);
        } else {
            int index = list.getStorage().indexOf(selected);
            Contact replacement = createContactByType(selectedType, name, address, number, extra);
            list.getStorage().set(index, replacement);
        }


        clearFields();
        refresh();
        if (!txtSearch.getText().trim().isEmpty()) {
            handleSearch();
        }
        showAlert(Alert.AlertType.INFORMATION, "Updated", "Contact updated successfully.");
    }


    // DELETE
    @FXML
    private void handleDel() {
        Contact selected = tblContacts.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No Contact Selected", "Click a contact in the table first.");
            return;
        }


        list.removeContact(selected);
        tblSContacts.getItems().remove(selected);
        clearFields();
        refresh();
    }


    // READ (SEARCH)
    @FXML
    private void handleSearch() {
        String searchText = txtSearch.getText().trim().toLowerCase();


        if (searchText.isEmpty()) {
            tblSContacts.getItems().clear();
            showAlert(Alert.AlertType.WARNING, "Empty Search", "Please type a name, address, number, or category to search.");
            return;
        }


        List<Contact> results = new ArrayList<>();


        for (Contact contact : list.getStorage()) {
            boolean matchesType = contact.getCategory().toLowerCase().contains(searchText);
            boolean matchesName = contact.getContactname().toLowerCase().contains(searchText);
            boolean matchesAddress = contact.getAddress().toLowerCase().contains(searchText);
            boolean matchesNumber = contact.getNumber().toLowerCase().contains(searchText);
            boolean matchesExtra = contact.getExtraDetail().toLowerCase().contains(searchText);


            if (matchesType || matchesName || matchesAddress || matchesNumber || matchesExtra) {
                results.add(contact);
            }
        }


        tblSContacts.getItems().setAll(results);


        if (results.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "No Results", "No contacts matched \"" + txtSearch.getText().trim() + "\".");
        }
    }


    private Contact createContactByType(String type, String name, String address, String number, String extra) {
        return switch (type) {
            case "Work" -> new WorkContact(name, address, number, extra);
            case "Emergency" -> new EmergencyContact(name, address, number, extra);
            default -> new FriendContact(name, address, number, extra);
        };
    }


    private boolean validateInputs() {
        String name = txtNames.getText().trim();
        String address = txtAddress.getText().trim();
        String number = txtNumber.getText().trim();
        String extra = txtExtra.getText().trim();


        if (cmbType.getValue() == null || name.isBlank() || address.isBlank() || number.isBlank() || extra.isBlank()) {
            showAlert(Alert.AlertType.WARNING, "Missing Contact Info",
                    "Please fill in all fields (Type, Name, Address, Number, and Note/Company).");
            return false;
        }


        // Validate phone number allows digits, spaces, +, -, and parentheses (min 7 chars)
        if (!number.matches("[0-9+\\-()\\s]{7,20}")) {
            showAlert(Alert.AlertType.ERROR, "Invalid Phone Number",
                    "Phone number must be 7–20 characters and contain only digits, spaces, '+', '-', or '()'.");
            return false;
        }


        return true;
    }


    private void clearFields() {
        txtNames.clear();
        txtAddress.clear();
        txtNumber.clear();
        txtExtra.clear();
        cmbType.setValue("Friend");
        tblContacts.getSelectionModel().clearSelection();
    }


    private void refresh() {
        tblContacts.getItems().setAll(list.getStorage());
        tblContacts.refresh();
    }


    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}