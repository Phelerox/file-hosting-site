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

    private static AbstractHostedFile file;
    private static DBHandler db;

    @BeforeClass
    public static void onlyOnce() {
        DBTest.file = new UserHostedFile();
        DBTest.file.setName("filen");
        byte[] bytes = new byte[2];
        bytes[0] = (byte) 2;
        bytes[1] = (byte) 4;
        DBTest.file.setBytes(bytes);

        DBTest.db = new DBHandler();
    }

    @Test
    public void testAddFile() {
        DBTest.db.addFile(DBTest.file);
        Assert.assertTrue(true);
    }

    @Test
    public void testGetFileById() {
        try {
            DBTest.db.addFile(DBTest.file);
        } catch (Exception e) {
            System.err.println("we assume the file was already added");
        }
        AbstractHostedFile fetchedFile = DBTest.db.getFile(DBTest.file.getId());
        Assert.assertEquals(file.getName(), fetchedFile.getName());
        Assert.assertEquals(file.getId(), fetchedFile.getId());
    }

    @Test
    public void testGetFilesByName() {
        try {
            DBTest.db.addFile(DBTest.file);
        } catch (Exception e) {
            System.err.println("we assume the file was already added");
        }
        List<AbstractHostedFile> fetchedFiles = DBTest.db.getFiles(DBTest.file.getName());
        Assert.assertEquals(file.getName(), fetchedFiles.get(0).getName());
        Assert.assertEquals(file.getId(), fetchedFiles.get(0).getId());
    }
}