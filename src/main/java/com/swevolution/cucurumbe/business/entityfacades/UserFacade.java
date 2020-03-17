/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.business.entityfacades;

import com.swevolution.cucurumbe.model.entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rxkx
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "CUCURUMBE_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public List<User> getUsers(String keywords, int limit, int offset) {
        return em.createQuery("SELECT u FROM User u WHERE u.role LIKE :keywords "
                + "OR u.name LIKE :keywords OR u.company LIKE :keywords "
                + "OR u.operation LIKE :keywords ORDER BY u.name ASC")
                .setParameter("keywords", "%" + keywords + "%")
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public int resetPassword(String login, String newPassword) {
        em.createNamedQuery("User.resetPasswordStatus")
                .setParameter("login", login)
                .executeUpdate();

        return em.createNamedQuery("User.resetPassword")
                .setParameter("login", login)
                .setParameter("password", newPassword)
                .executeUpdate();
    }

    public int resetAllPasswords(String newPassword) {
        return em.createNamedQuery("User.resetAllPasswords")
                .setParameter("password", newPassword)
                .executeUpdate();
    }

    public List<String> getOperations(String company) {
        List<String> results = em.createNativeQuery("SELECT DISTINCT(u.USER_OPERATION) "
                + "FROM USERS u WHERE u.COMPANY = :company ORDER BY u.USER_OPERATION ASC")
                .setParameter("company", company)
                .getResultList();
        results.remove(null);
        return results;
    }

    public List<String> getCompanies() {
        List<String> results = em.createNativeQuery("SELECT DISTINCT(u.COMPANY) "
                + "FROM USERS u ORDER BY u.COMPANY ASC")
                .getResultList();
        results.remove(null);
        return results;
    }

    public List<User> findGuides(String name) {
        return em.createQuery("select u from User u where u.name like :name and u.role = 'GUIA' order by u.name asc")
                .setParameter("name", "%" + name + "%")
                .setMaxResults(10)
                .getResultList();
    }

}
