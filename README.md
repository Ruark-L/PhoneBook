# PhoneBook

A desktop contact management application built with **Java** and **JavaFX**, structured around the **Model–View–Controller (MVC)** architectural pattern and core **Object-Oriented Programming (OOP)** principles.

**Members:**
- Ruark Lagtapon
- Christopher Ariola
- Ezekiel Rapana

---

## Overview

**PhoneBook** allows users to organize, search, and maintain personal and professional contacts through an intuitive graphical interface. Under the hood, the application demonstrates multi-level inheritance, abstraction, and runtime polymorphism by storing diverse contact categories inside a single polymorphic collection.

---

## Features

- **Full CRUD Functionality:**
  - **Create:** Add new contacts categorized as *Friend*, *Work*, or *Emergency* with custom notes or organization details.
  - **Read & Search:** View all stored contacts in a live `TableView` and filter records case-insensitively by category, name, address, phone number, or extra details in a dedicated search results table.
  - **Update:** Click any contact in the main table to populate the editor form, modify its details or category, and save changes immediately.
  - **Delete:** Remove selected contacts permanently from both the UI tables and underlying storage.
- **Input Validation & Alerts:** Validates against empty fields and improperly formatted phone numbers, providing clear JavaFX `Alert` dialogs for user feedback.
- **OOP Architecture:**
  - **3-Level Inheritance Hierarchy** with **3 `abstract` classes** (`Contact`, `PersonalContact`, `ProfessionalContact`) and **3 concrete subclasses** (`FriendContact`, `WorkContact`, `EmergencyContact`).
  - **Polymorphic Collection:** Uses an `ArrayList<Contact>` in `ContactList` to store and manage mixed subclass instances uniformly.

---

## Project Structure

```text
src
├──main
│    ├── Java
│    │      ├── app
│    │      │    └── Main.java
│    │      ├── controller/
│    │      │    └── ContactController.java
│    │      ├── model
│    │      │    ├── Contact.java              (abstract - Level 1)
│    │      │    ├── PersonalContact.java      (abstract subclass - Level 2)
│    │      │    ├── ProfessionalContact.java  (abstract subclass - Level 2)
│    │      │    ├── FriendContact.java        (concrete subclass - Level 3)
│    │      │    ├── WorkContact.java          (concrete subclass - Level 3)
│    │      │    ├── EmergencyContact.java     (concrete subclass - Level 3)
│    │      │    └── ContactList.java
│    ├── Resources
│    │     ├── app
│    │     │    └── contact-view.fxml
```

---

## Tech Stack
- **Language:** Java (JDK 17+)
- **UI Framework:** JavaFX (FXML, Scene Builder)
- **IDE:** IntelliJ IDEA

---

**PhoneBook code is located at ***'Code'*** branch**
