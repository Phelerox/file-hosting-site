package edu.chl.grupp14.filehostingsite.core;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
    
    public List<HostedFile> getFilesFromOwner(AppUser owner) {
        EntityManager em = super.emf.createEntityManager();
        String file = "select f from HostedFile f where f.owner = :owner";
        TypedQuery<HostedFile> tq = em.createQuery(file, HostedFile.class);
        tq.setParameter("owner", owner);
        return tq.getResultList();
    }
    public List<HostedFile> getFilesFromGroup(AppGroup group) {
        EntityManager em = super.emf.createEntityManager();
        String file = "select f from HostedFile f where f.ggroup = :ggroup";
        TypedQuery<HostedFile> tq = em.createQuery(file, HostedFile.class);
        tq.setParameter("ggroup", group);
        return tq.getResultList();
    }
    
    //Returns a list of the latest PUBLIC files
    public List<HostedFile> getLatestPublicFiles() {
        EntityManager em = super.emf.createEntityManager();
        String query = "select f from HostedFile f where (f.isPublic='true' AND f.ggroup IS NULL) order by f.uploadDate desc";
        TypedQuery<HostedFile> tq = em.createQuery(query, HostedFile.class);
        return tq.getResultList();
    }
    
    //Returns a list of the most downloaded PUBLIC files
    public List<HostedFile> getMostPublicDownloaded(){
        EntityManager em = super.emf.createEntityManager();
        String query = "select f from HostedFile f where (f.isPublic='true' AND f.ggroup IS NULL) order by f.downloads desc";
        TypedQuery<HostedFile> tq = em.createQuery(query, HostedFile.class);
        return tq.getResultList();
    }
    
    public List<HostedFile> getFilesContaining(String name) {
        EntityManager em = super.emf.createEntityManager();
        String file = "select f from HostedFile f where LOWER(f.filename) LIKE LOWER(:name)";
        TypedQuery<HostedFile> tq = em.createQuery(file, HostedFile.class);
        tq.setParameter("name", "%" + name + "%");
        return tq.getResultList();
    }
}
