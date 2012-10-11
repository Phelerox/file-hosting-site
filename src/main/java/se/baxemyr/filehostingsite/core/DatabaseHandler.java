/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.core;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Gustav
 */
public class DatabaseHandler {
    private EntityManagerFactory emf;
    
    public DatabaseHandler(String puName) {
        emf = Persistence.createEntityManagerFactory(puName);
    }
    
    public void add(IDataObject t) {
       EntityManager em = emf.createEntityManager();
       
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();  
    }
}
