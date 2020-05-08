/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author mihir
 */
public class PersistenceUnitConnec {
    public static EntityManager createEntityManager(String persistenceUnit) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
        EntityManager em = entityManagerFactory.createEntityManager();
        return em;
    }
}
