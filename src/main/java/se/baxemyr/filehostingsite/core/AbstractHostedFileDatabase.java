package se.baxemyr.filehostingsite.core;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sam, Marco
 */
public class AbstractHostedFileDatabase extends AbstractDAO<UserHostedFile, Long> {

    private AbstractHostedFileDatabase(String puName) {
        super(UserHostedFile.class, puName);
    }
    
    public static AbstractHostedFileDatabase newInstance(String puName){
        return new AbstractHostedFileDatabase(puName);
    }

    public List<UserHostedFile> getFiles(String name) {
        EntityManager em = super.emf.createEntityManager();
        String file = "select f from UserHostedFile f where f.filename = :name";
        TypedQuery<UserHostedFile> tq = em.createQuery(file, UserHostedFile.class);
        tq.setParameter("name", name);
        return tq.getResultList();
    }
    
    public List<UserHostedFile> getFilesFromOwner(User owner) {
        EntityManager em = super.emf.createEntityManager();
        String file = "select f from UserHostedFile f where f.owner = :owner";
        TypedQuery<UserHostedFile> tq = em.createQuery(file, UserHostedFile.class);
        tq.setParameter("owner", owner);
        return tq.getResultList();
    }
}
