package com.example.turnikas_back_end.business.user.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Contact {
    private int userId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    public Contact(int userId, String firstName, String lastName, LocalDate dateOfBirth) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public static List<Contact> generateContacts() {
        List<Contact> contactList = new ArrayList<>();
        contactList.add(new Contact(1, "John", "Doe", LocalDate.of(1990, 2, 1)));
        contactList.add(new Contact(2, "Jane", "Smith", LocalDate.of(1991, 5, 10)));
        contactList.add(new Contact(3, "Alice", "Johnson", LocalDate.of(1988, 12, 5)));
        contactList.add(new Contact(4, "Bob", "Williams", LocalDate.of(1992, 8, 20)));
        contactList.add(new Contact(5, "Eva", "Brown", LocalDate.of(1994, 4, 15)));
        contactList.add(new Contact(6, "Michael", "Davis", LocalDate.of(1993, 10, 30)));
        contactList.add(new Contact(7, "Sophia", "Garcia", LocalDate.of(1985, 7, 3)));
        contactList.add(new Contact(8, "David", "Martinez", LocalDate.of(1989, 9, 25)));
        contactList.add(new Contact(9, "Olivia", "Lopez", LocalDate.of(1996, 6, 12)));
        contactList.add(new Contact(10, "William", "Lee", LocalDate.of(1987, 3, 28)));
        return contactList;
    }
}
