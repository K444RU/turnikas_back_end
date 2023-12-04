package com.example.turnikas_back_end.business.user.service;

import com.example.turnikas_back_end.business.user.model.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    public List<Contact> getContactsByUserId(int userId) {
        List<Contact> contacts = Contact.generateContacts();

        List<Contact> userContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getUserId() == userId) {
                userContacts.add(contact);
            }
        }
        return userContacts;
    }
}
