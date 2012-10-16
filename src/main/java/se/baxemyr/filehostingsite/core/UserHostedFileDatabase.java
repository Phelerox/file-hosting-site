package se.baxemyr.filehostingsite.core;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sam, Marco
 */
public class UserHostedFileDatabase extends AbstractDAO<UserHostedFile, Long> {

    private UserHostedFileDatabase(String puName) {
        super(UserHostedFile.class, puName);
    }
    
    public static UserHostedFileDatabase newInstance(String puName){
        return new UserHostedFileDatabase(puName);
    }

    public UserHostedFile getFile(Long id) {
        EntityManager em = super.emf.createEntityManager();
        String file = "select f from UserHostedFile f where f.id = :id";
        TypedQuery<UserHostedFile> tq = em.createQuery(file, UserHostedFile.class);
        tq.setParameter("id", id);
        return tq.getSingleResult();
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
