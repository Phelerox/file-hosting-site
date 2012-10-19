package se.baxemyr.filehostingsite.core;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sam, Marco
 */
public class HostedFileDatabase extends AbstractDAO<HostedFile, Long> {

    private HostedFileDatabase(String puName) {
        super(HostedFile.class, puName);
    }
    
    public static HostedFileDatabase newInstance(String puName){
        return new HostedFileDatabase(puName);
    }

    public List<HostedFile> getFiles(String name) {
        EntityManager em = super.emf.createEntityManager();
        String file = "select f from HostedFile f where f.filename = :name";
        TypedQuery<HostedFile> tq = em.createQuery(file, HostedFile.class);
        tq.setParameter("name", name);
        return tq.getResultList();
    }
    
    public List<HostedFile> getFilesFromOwner(User owner) {
        EntityManager em = super.emf.createEntityManager();
        String file = "select f from HostedFile f where f.owner = :owner";
        TypedQuery<HostedFile> tq = em.createQuery(file, HostedFile.class);
        tq.setParameter("owner", owner);
        return tq.getResultList();
    }
    
   
    public List<HostedFile> getLatestFiles() {
        EntityManager em = super.emf.createEntityManager();
        String query = "select f from HostedFile f order by f.uploadDate desc";
        TypedQuery<HostedFile> tq = em.createQuery(query, HostedFile.class);
        return tq.getResultList();
    }
    
    public List<HostedFile> getMostDownloaded(){
        EntityManager em = super.emf.createEntityManager();
        String query = "select f from HostedFile f order by f.downloads desc";
        TypedQuery<HostedFile> tq = em.createQuery(query, HostedFile.class);
        return tq.getResultList();
    }
}
