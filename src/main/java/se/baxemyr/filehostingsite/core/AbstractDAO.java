package se.baxemyr.filehostingsite.core;

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
public abstract class AbstractDAO<T extends IEntity<K>, K> implements IDAO<T, K> {

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
    public void remove(K T) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.remove(em.find(clazz, T));
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
        
    }

    @Override
    public T find(K id) {    
        EntityManager em = emf.createEntityManager();
        //Testa det här sedan
        //clazz.getSimpleName();
        String file = "select f from "+this.clazz.getSimpleName()+" f where f.id = :id";
        TypedQuery<T> tq = em.createQuery(file, clazz);
        tq.setParameter("id", id);
        return tq.getSingleResult();
    }

    @Override
    public List<T> getAll() {
        return null;
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