
package edu.chl.grupp14.filehostingsite.core.db;


import java.util.List;

/**
 * Basic contract for containers of Files, Users, Groups etc
 * 
 * K is type of id (primary key)
 * 
 * @author hajo
 */
public interface IDAO<T, K> {

    public void add(T t);

    public void remove(K id);

    public void update(T t);

    public T find(K id);

    public List<T> getAll();

    public List<T> getRange(int maxResults, int firstResult);

    public int getCount();
}
