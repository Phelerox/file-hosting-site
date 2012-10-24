/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.grupp14.filehostingsite.test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import edu.chl.grupp14.filehostingsite.core.HostedFile;
import edu.chl.grupp14.filehostingsite.core.HostedFileDatabase;

/**
 *
 * @author Sam
 */
public class DBTestGet {
     private static HostedFile file;
     private static HostedFile file2;
    private static HostedFileDatabase HostedFileDB;

    @BeforeClass
    public static void onlyOnce() {
        DBTestGet.file = new HostedFile();
        DBTestGet.file.setFilename("filen");
        byte[] bytes = new byte[2];
        bytes[0] = (byte) 2;
        bytes[1] = (byte) 4;
        DBTestGet.file.setBytes(bytes);

        DBTestGet.HostedFileDB = HostedFileDatabase.newInstance("filehosting_pu");
        
        DBTestGet.HostedFileDB.add(DBTestGet.file);
        
        DBTestGet.file2 = new HostedFile();
        DBTestGet.file2.setFilename("filen2");
        byte[] bytes2 = new byte[2];
        bytes2[0] = (byte) 2;
        bytes2[1] = (byte) 4;
        DBTestGet.file.setBytes(bytes2);
        
        DBTestGet.HostedFileDB.add(DBTestGet.file2);
    }


    @Test
    public void testGetFileById() {
        HostedFile fetchedFile = DBTestGet.HostedFileDB.find(DBTestGet.file.getId());
        Assert.assertEquals(file.getFilename(), fetchedFile.getFilename());
        Assert.assertEquals(file.getId(), fetchedFile.getId());
    }

    @Test
    public void testGetFilesByName() {
        List<HostedFile> fetchedFiles = DBTestGet.HostedFileDB.getFiles(DBTestGet.file.getFilename());
        Assert.assertEquals(file.getFilename(), fetchedFiles.get(0).getFilename());
    }
    @Test
    public void testGetLatestFiles() {
        List<HostedFile> fetchedFiles = DBTestGet.HostedFileDB.getFiles(DBTestGet.file.getFilename());
       //Assert.assertEquals(file.getUploadDate(), fetchedFiles.get(0).getUploadDate());
        //Assert.assertNotSame(file2.getUploadDate(), fetchedFiles.get(0).getUploadDate());
        Assert.assertEquals(file.getId(), fetchedFiles.get(0).getId());
    }
    
    @Test
    public void testGetFilesContaining() {
        List<HostedFile> fetchedFiles = DBTestGet.HostedFileDB.getFilesContaining(DBTestGet.file.getFilename());
        Assert.assertTrue(fetchedFiles.size()==2);
    }
    
}
