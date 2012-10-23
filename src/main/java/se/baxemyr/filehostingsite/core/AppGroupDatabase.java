/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.core;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sam
 */
public class AppGroupDatabase extends AbstractDAO<AppGroup, String> {
    public AppGroupDatabase(String puName){
        super(AppGroup.class, puName);
    }
    
    public static AppGroupDatabase newInstance(String puName){
        return new AppGroupDatabase(puName);
    }
}
