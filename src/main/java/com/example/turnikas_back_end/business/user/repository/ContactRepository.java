package com.example.turnikas_back_end.business.user.repository;

import com.example.turnikas_back_end.business.common.repository.TurnikasRepository;
import com.example.turnikas_back_end.business.user.model.Contact;
import com.example.turnikas_back_end.business.user.request.UserRegistration;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.jooq.generated.tables.Contact.CONTACT;

@Repository
public class ContactRepository implements TurnikasRepository {

    private final DSLContext jooq;

    @Autowired
    public ContactRepository(DSLContext jooq) {
        this.jooq = jooq;
    }


        @Override
    public Integer add(Object object) {
        UserRegistration userRegistration = (UserRegistration) object;
        return jooq
                .insertInto(CONTACT,
                        CONTACT.USER_ID,
                        CONTACT.FIRST_NAME,
                        CONTACT.LAST_NAME,
                        CONTACT.DATE_OF_BIRTH)
                .values(userRegistration.getId(),
                        userRegistration.getFirstName(),
                        userRegistration.getLastName(),
                        userRegistration.getDateOfBirth())
                .returning(CONTACT.ID)
                .execute();
    }


    @Override
    public Object delete(Integer id) {
        return null;
    }

    @Override
    public List<?> findAll() {
        return null;
    }

    @Override
    public Object findById(Integer id) {
        return jooq
                .select(CONTACT.FIRST_NAME,
                        CONTACT.LAST_NAME,
                        CONTACT.DATE_OF_BIRTH)
                .from(CONTACT)
                .where(CONTACT.USER_ID.eq(id))
                .fetchOneInto(Contact.class);
    }

    @Override
    public Object update(Object object) {
        return null;
    }


    public Contact getContactInformationByUserId(int userId){
        return jooq
                .selectFrom(CONTACT)
                .where(CONTACT.USER_ID.eq(userId))
                .fetchOneInto(Contact.class);
    }

    public Contact updateContactInformation(int userId, Contact updatedContact) {
        int updatedRows =  jooq
                .update(CONTACT)
                .set(CONTACT.FIRST_NAME, updatedContact.getFirstName())
                .set(CONTACT.LAST_NAME, updatedContact.getLastName())
                .set(CONTACT.DATE_OF_BIRTH, updatedContact.getDateOfBirth())
                .where(CONTACT.USER_ID.eq(userId))
                .execute();
        if (updatedRows > 0) {
            return updatedContact;
        } else {
            return null;
        }
    }
}

