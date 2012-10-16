package se.baxemyr.filehostingsite.core;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Marco Baxemyr
 */
@Entity
public class Comment implements IEntity{
    
    @Id
    @GeneratedValue
    private Long id;
    
    private User author;
    private String content;
 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePosted;

    public Comment() {
        
    }
    
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
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
    public Object getId() {
        return id;
    }
    
    
    
}
