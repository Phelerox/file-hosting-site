package edu.chl.grupp14.filehostingsite.core.db;

import edu.chl.grupp14.filehostingsite.core.entities.Comment;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CommentDatabase extends AbstractDAO<Comment , Long>{
    
    public CommentDatabase(String puName){      
        super(Comment.class, puName);
    }
    
    public static CommentDatabase newInstance(String puName){
        return new CommentDatabase(puName);
    }
    
    public List<Comment> getCommentsIdbyFileId(Long fileId) {
        EntityManager em = super.emf.createEntityManager();
        String file = "select f from Comment f where f.HOSTEDFILE_ID = :id";
        TypedQuery<Comment> tq = em.createQuery(file, Comment.class);
        tq.setParameter("id", fileId);
        return tq.getResultList();
    }
}
