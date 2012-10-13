/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.test;

import java.util.List;
import junit.framework.Assert;
import org.junit.*;
import se.baxemyr.filehostingsite.core.AbstractHostedFile;
import se.baxemyr.filehostingsite.core.UserHostedFile;
import se.baxemyr.filehostingsite.core.UserHostedFileDatabase;

/**
 *
 * @author Sam
 */
public class DBTest {

    private static UserHostedFile file;
    private static UserHostedFileDatabase userHostedFileDB;

    @BeforeClass
    public static void onlyOnce() {
        DBTest.file = new UserHostedFile();
        DBTest.file.setName("filen");
        byte[] bytes = new byte[2];
        bytes[0] = (byte) 2;
        bytes[1] = (byte) 4;
        DBTest.file.setBytes(bytes);

        DBTest.userHostedFileDB = UserHostedFileDatabase.newInstance("filehosting_pu");
    }

    @Test
    public void testAddFile() {
        DBTest.userHostedFileDB.add(DBTest.file);
        Assert.assertTrue(true);
    }

    @Test
    public void testGetFileById() {
        try {
            System.err.println("DBHandler kommer skriva ut att något gått fel, men det är så testet är utformat. Vi antar då att filen vi försöker hämta redan är tillagd");
            DBTest.userHostedFileDB.add(DBTest.file);
        } catch (Exception e) {
            System.err.println("we assume the file was already added");
        }
        AbstractHostedFile fetchedFile = DBTest.userHostedFileDB.getFile(DBTest.file.getId());
        Assert.assertEquals(file.getName(), fetchedFile.getName());
        Assert.assertEquals(file.getId(), fetchedFile.getId());
    }

    @Test
    public void testGetFilesByName() {
        try {
            System.err.println("DBHandler kommer skriva ut att något gått fel, men det är så testet är utformat. Vi antar då att filen vi försöker hämta redan är tillagd");
            DBTest.userHostedFileDB.add(DBTest.file);
        } catch (Exception e) {
            System.err.println("we assume the file was already added");
        }
        List<UserHostedFile> fetchedFiles = DBTest.userHostedFileDB.getFiles(DBTest.file.getName());
        Assert.assertEquals(file.getName(), fetchedFiles.get(0).getName());
        Assert.assertEquals(file.getId(), fetchedFiles.get(0).getId());
    }
}