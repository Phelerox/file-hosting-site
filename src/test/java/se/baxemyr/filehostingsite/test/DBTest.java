/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.test;

import java.util.List;
import junit.framework.Assert;
import org.junit.*;
import se.baxemyr.filehostingsite.core.AbstractHostedFile;
import se.baxemyr.filehostingsite.core.DBHandler;
import se.baxemyr.filehostingsite.core.UserHostedFile;

/**
 *
 * @author Sam
 */
public class DBTest {
    
    private AbstractHostedFile file;
    private DBHandler db;
    
    public void setUp(){
        this.file = new UserHostedFile();
        this.file.setName("filen");
        byte[] bytes = new byte[2];
        bytes[0] = (byte)2;
        bytes[1] = (byte)4;
        this.file.setBytes(bytes);
        
        this.db = new DBHandler();
        
    }
    @Test
    public void testAddFile()
    {
        setUp();
        this.db.addFile(this.file);
        Assert.assertTrue(true);
    }
    
    @Test
    public void testGetFileById(){
        AbstractHostedFile fetchedFile = this.db.getFile(this.file.getId());
        Assert.assertEquals(file, fetchedFile);
    }
    @Test
    public void testGetFilesByName(){
        List<AbstractHostedFile> fetchedFiles = this.db.getFile(this.file.getName());
        Assert.assertEquals(file, fetchedFiles.get(0));
    }
    
    
    
    
    
    
    
    
    
}
