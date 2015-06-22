package com.smartpump.dao.sql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.smartpump.dao.constants.Queries;
import com.smartpump.dao.constants.Units;
import com.smartpump.dao.interfaces.IUserDao;
import com.smartpump.model.User;
import com.smartpump.model.UserState;

public class UserDao implements IUserDao {

    @PersistenceContext(unitName = Units.USER_UNIT)
    private EntityManager entityManager;

    @Transactional
    public UserState getUserState(int id) {
        UserState userState = entityManager.find(UserState.class, id);
        return userState;
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        List<User> users = entityManager.createNamedQuery(
                Queries.USER_GET_ALL_QUERY, User.class).getResultList();
        return users;
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        User user = entityManager.find(User.class, id);
        return (user != null) ? user : null;
    }

    @Override
    @Transactional
    public boolean validateUser(String username) {
        Query query = entityManager.createNamedQuery(
                Queries.USER_GET_BY_USERNAME_QUERY, User.class);
        query.setParameter("username", username);
        User user;
        try {
            user = (User) query.getSingleResult();
        } catch (Exception ex) {
            user = null;
        }
        return (user != null);
    }

    @Override
    @Transactional
    public User validateUser(String username, String password) {
        Query query = entityManager.createNamedQuery(
                Queries.USER_GET_BY_USERNAME_AND_PASSWORD_QUERY, User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        User user = (User) query.getSingleResult();
        return user;
    }

    @Override
    @Transactional
    public void registerUser(User u) {
        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.getTransaction().commit();
    }
}
