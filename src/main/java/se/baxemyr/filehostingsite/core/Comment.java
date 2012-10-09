package se.baxemyr.filehostingsite.core;

import java.util.Date;

/**
 *
 * @author Marco Baxemyr
 */
public class Comment {
    
    private User author;
    private String content;
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
    
    
    
}
