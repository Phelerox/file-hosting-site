package edu.chl.grupp14.filehostingsite.core;

import java.io.Serializable;

/**
 * All entity classes must have a unique id
 * Also they all must be serializable
 * 
 * K is type of id (primary key)
 * 
 * @author hajo
 */
public interface IEntity<K> extends Serializable {
    public K getId();
}
