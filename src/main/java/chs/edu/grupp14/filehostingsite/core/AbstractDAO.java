package chs.edu.grupp14.filehostingsite.core;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * A container for entities
 *   
 * K is type of id (primary key)
 * 
 * @author hajo
 */
public abstract class AbstractDAO<T, K> implements IDAO<T, K> {

    protected EntityManagerFactory emf;
    private final Class<T> clazz;
    protected AbstractDAO(Class<T> clazz, String puName) {
        this.clazz = clazz;
        emf = Persistence.createEntityManagerFactory(puName);
    }

    @Override
    //Ta emot subklass till T?
    public void add(T t) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close(); 
            }
        }
    }
  
    @Override
    public void remove(K id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.remove(em.find(clazz, id));
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void update(T t) {
            EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close(); 
            }
        }
    }

    @Override
    public T find(K id) {    
        EntityManager em = emf.createEntityManager();
        //Testa det här sedan
        //clazz.getSimpleName();
        String file = "select f from "+this.clazz.getSimpleName()+" f where f.id = :id";
        TypedQuery<T> tq = em.createQuery(file, clazz);
        tq.setParameter("id", id);
        List<T> results = tq.getResultList();
        if (results.size() == 1) {
            return results.get(0);
        }
        return null;
    }
    
    //Need to test this one, maybe useful for admins?
    @Override
    public List<T> getAll() {
        EntityManager em = emf.createEntityManager();
        String query = "select f from " + this.clazz.getSimpleName()+" f";
        TypedQuery<T> tq = em.createQuery(query, clazz);
        return tq.getResultList();
    }

    @Override
    public List<T> getRange( int firstResult, int maxResults) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}