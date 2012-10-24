package edu.chl.grupp14.filehostingsite.core;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Marco Baxemyr
 */
@Entity
public class Comment implements IEntity<Long>{
    
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    private AppUser author;
    private String content;
    
 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePosted;

    public Comment() {
        this.datePosted = new Date();
    }
    public Comment(String content, AppUser author){
        this.content = content;
        this.author = author;
        this.datePosted = new Date();
    }
    
    public AppUser getAuthor() {
        return author;
    }

    public void setAuthor(AppUser author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    @Override
    public Long getId() {
        return id;
    }
}
