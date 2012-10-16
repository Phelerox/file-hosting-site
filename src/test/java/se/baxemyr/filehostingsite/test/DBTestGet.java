/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.test;

import java.util.List;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.baxemyr.filehostingsite.core.HostedFile;
import se.baxemyr.filehostingsite.core.HostedFileDatabase;

/**
 *
 * @author Sam
 */
public class DBTestGet {
     private static HostedFile file;
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
        Assert.assertEquals(file.getId(), fetchedFiles.get(0).getId());
    }
}
