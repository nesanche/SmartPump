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

/**
 * Clase que implementa la interfaz IUserDao y utiliza los servicios de JPA e
 * Hibernate para persistir.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class UserDao implements IUserDao {

    /** Atributo encargado del manejo de persistencia. */
    @PersistenceContext(unitName = Units.USER_UNIT)
    private EntityManager entityManager;

    /**
     * Establece el EntityManager a utilizar por el controlador DAO.
     * 
     * @param entityManager
     *            el EntityManager a utilizar.
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
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

}
