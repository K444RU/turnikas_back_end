package com.example.turnikas_back_end.business.user.service;

import com.example.turnikas_back_end.business.user.model.Contact;
import com.example.turnikas_back_end.business.user.repository.ContactRepository;
import org.springframework.stereotype.Service;


@Service
public class ContactService {

    ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @SuppressWarnings("unchecked")
    public Contact getContactInformationByUserId(int userId){
        return contactRepository.getContactInformationByUserId(userId);
    }

    public Contact updateContactInformation(int userId, Contact updatedContact) {
        return contactRepository.updateContactInformation(userId, updatedContact);
    }
}
