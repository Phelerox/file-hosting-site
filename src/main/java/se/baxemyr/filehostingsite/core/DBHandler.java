/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.core;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sam
 */
public class DBHandler {

    //Static refrence to our persistance, is there another better way to do this?
    private final static String puName = "filehosting_pu";
    private EntityManagerFactory emf;

    public DBHandler() {
        emf = Persistence.createEntityManagerFactory(puName);
    }

    public void addFile(AbstractHostedFile file) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(file);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.err.println("DBHandler: AddFile: Något gick fel, exception genererades");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public AbstractHostedFile getFile(Long id) {
        EntityManager em = emf.createEntityManager();
        String file = "select f from AbstractHostedFile f where f.id = :id";
        TypedQuery<AbstractHostedFile> tq = em.createQuery(file, AbstractHostedFile.class);
        tq.setParameter("id", id);
        return tq.getSingleResult();
    }

    public List<AbstractHostedFile> getFiles(String name) {
        EntityManager em = emf.createEntityManager();
        String file = "select f from AbstractHostedFile f where f.fileName = :name";
        TypedQuery<AbstractHostedFile> tq = em.createQuery(file, AbstractHostedFile.class);
        tq.setParameter("name", name);
        return tq.getResultList();
    }
}
