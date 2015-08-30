package com.smartpump.dao.sql;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.smartpump.dao.constants.Queries;
import com.smartpump.dao.interfaces.IUserDao;
import com.smartpump.model.User;
import com.smartpump.model.UserState;
import com.smartpump.model.VerificationToken;

/**
 * Clase que implementa la interfaz IUserDao y utiliza los servicios de JPA e
 * Hibernate para persistir.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class UserDao extends AbstractDao implements IUserDao {

    @Override
    @Transactional
    public List<User> getUsers() {
        List<User> users = entityManager.createNamedQuery(
                Queries.USER_GET_ALL_QUERY, User.class).getResultList();
        return users;
    }

    @Override
    @Transactional
    public boolean validateUser(String username) {
        TypedQuery<User> query = entityManager.createNamedQuery(
                Queries.USER_GET_BY_USERNAME_QUERY, User.class);
        query.setParameter("username", username);
        User user;
        try {
            user = query.getSingleResult();
        } catch (Exception ex) {
            user = null;
        }
        return (user != null);
    }

    @Transactional
    @Override
    public VerificationToken registerToken(User user, String tokenString) {
        VerificationToken token = new VerificationToken(tokenString, user);
        token.getUser().setId(user.getId());
        entityManager.persist(token);
        entityManager.flush();
        return token;
    }

    @Transactional
    @Override
    public boolean confirmUser(int id, String token) {
        TypedQuery<VerificationToken> query = entityManager.createNamedQuery(
                Queries.VERIFICATION_TOKEN_CONFIRM_USER_QUERY,
                VerificationToken.class);
        query.setParameter("id", id);
        query.setParameter("token", token);
        VerificationToken verificationToken = null;
        try {
            verificationToken = query.getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        Date today = new Date();
        if (today.after(verificationToken.getExpirationDate())) {
            return false;
        }
        User user = verificationToken.getUser();
        user.setState(new UserState(2, "Registered"));
        entityManager.persist(user);
        entityManager.flush();
        return true;
    }

    @Transactional
    @Override
    public User getUser(String username, String password) {
        TypedQuery<User> query = entityManager.createNamedQuery(
                Queries.USER_GET_BY_USERNAME_AND_PASSWORD_QUERY, User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        User user;
        try {
            user = query.getSingleResult();
        } catch (NoResultException ex) {
            user = null;
        }
        return user;
    }

    @Transactional
    @Override
    public User getUser(String username) {
        TypedQuery<User> query = entityManager.createNamedQuery(
                Queries.USER_GET_BY_USERNAME_QUERY, User.class);
        query.setParameter("username", username);
        User user;
        try {
            user = query.getSingleResult();
        } catch (Exception ex) {
            user = null;
        }
        return user;
    }

}
