package com.example.turnikas_back_end.business.user.repository;

import com.example.turnikas_back_end.business.common.repository.TurnikasRepository;
import com.example.turnikas_back_end.business.user.dto.UserDTO;
import com.example.turnikas_back_end.business.user.request.UserRegistration;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static org.jooq.generated.tables.User.USER;

@Repository
public class UserRepository implements TurnikasRepository {


    private final DSLContext jooq;

    @Autowired
    public UserRepository(DSLContext jooq) {
        this.jooq = jooq;
    }

    @Transactional
    @Override
    public Integer add(Object object) {
        UserRegistration userRegistration = (UserRegistration) object;
        return Objects.requireNonNull(jooq
                        .insertInto(USER,
                                USER.EMAIL,
                                USER.PASSWORD)
                        .values(userRegistration.getEmail(),
                                userRegistration.getPassword())
                        .returning(USER.ID)
                        .fetchOne())
                .getValue(USER.ID);
    }

    @Transactional
    @Override
    public Object delete(Integer id) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<?> findAll() {
        return jooq
                .select(USER.ID,
                        USER.EMAIL)
                .from(USER)
                .fetchInto(UserDTO.class);
    }
    @Transactional(readOnly = true)
    @Override
    public Object findById(Integer id) {
        return jooq
                .select(USER.ID,
                        USER.EMAIL)
                .from(USER)
                .where(USER.ID.eq(id))
                .fetchOneInto(UserDTO.class);
    }

    @Transactional
    public UserDTO findUserByLoginInformation(UserDTO user) {
        return jooq
                .select(USER.ID)
                .from(USER)
                .where(USER.EMAIL.eq(user.getEmail()))
                .and(USER.PASSWORD.eq(user.getPassword()))
                .fetchOneInto(UserDTO.class);
    }

    @Transactional(readOnly = true)
    public UserDTO findByEmail(String email) {
        return jooq
                .select(USER.ID, USER.EMAIL, USER.PASSWORD)
                .from(USER)
                .where(USER.EMAIL.eq(email))
                .fetchOneInto(UserDTO.class);
    }

    public boolean emailExists(String email) {
        return jooq
                .fetchExists(jooq.selectFrom(USER).where(USER.EMAIL.eq(email)));
    }

    @Transactional
    @Override
    public Object update(Object object) {
        return null;
    }
}
