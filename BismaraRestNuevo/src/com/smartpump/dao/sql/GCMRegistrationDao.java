package com.smartpump.dao.sql;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.smartpump.dao.constants.Queries;
import com.smartpump.dao.interfaces.IGCMRegistrationDao;
import com.smartpump.model.notifications.GCMRegistration;

/**
 * Clase que implementa la interfaz IGCMRegistrationDAO y utiliza los servicios
 * de JPA e Hibernate para persistir.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class GCMRegistrationDao extends AbstractDao implements
        IGCMRegistrationDao {

    @Override
    @Transactional
    public GCMRegistration getRegistrationByUserId(int userId) {
        TypedQuery<GCMRegistration> query = entityManager.createNamedQuery(
                Queries.GCM_REGISTRATION_GET_BY_USER_ID, GCMRegistration.class);
        query.setParameter("userId", userId);
        GCMRegistration registration = null;
        try {
            registration = query.getSingleResult();
        } catch (NoResultException ex) {
            registration = null;
        }
        return registration;
    }

}
