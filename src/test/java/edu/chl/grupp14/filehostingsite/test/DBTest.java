/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.grupp14.filehostingsite.test;

import java.util.List;
import junit.framework.Assert;
import org.junit.*;
import edu.chl.grupp14.filehostingsite.core.entities.HostedFile;
import edu.chl.grupp14.filehostingsite.core.entities.HostedFile;
import edu.chl.grupp14.filehostingsite.core.db.HostedFileDatabase;

/**
 *
 * @author Sam
 */
public class DBTest {

    private static HostedFile file;
    private static HostedFileDatabase HostedFileDB;

    @BeforeClass
    public static void onlyOnce() {
        DBTest.file = new HostedFile();
        DBTest.file.setFilename("filen");
        byte[] bytes = new byte[2];
        bytes[0] = (byte) 2;
        bytes[1] = (byte) 4;
        DBTest.file.setBytes(bytes);

        DBTest.HostedFileDB = HostedFileDatabase.newInstance("filehosting_pu");
    }

    @Test
    public void testAddFile() {
        DBTest.HostedFileDB.add(DBTest.file);
        Assert.assertTrue(true);
    }

    @Test
    public void testGetFileById() {
        try {
            System.err.println("DBHandler kommer skriva ut att något gått fel, men det är så testet är utformat. Vi antar då att filen vi försöker hämta redan är tillagd");
            DBTest.HostedFileDB.add(DBTest.file);
        } catch (Exception e) {
            System.err.println("we assume the file was already added");
        }
        HostedFile fetchedFile = DBTest.HostedFileDB.find(DBTest.file.getId());
        Assert.assertEquals(file.getFilename(), fetchedFile.getFilename());
        Assert.assertEquals(file.getId(), fetchedFile.getId());
    }

    @Test
    public void testGetFilesByName() {
        try {
            System.err.println("DBHandler kommer skriva ut att något gått fel, men det är så testet är utformat. Vi antar då att filen vi försöker hämta redan är tillagd");
            DBTest.HostedFileDB.add(DBTest.file);
        } catch (Exception e) {
            System.err.println("we assume the file was already added");
        }
        List<HostedFile> fetchedFiles = DBTest.HostedFileDB.getFiles(DBTest.file.getFilename());
        Assert.assertEquals(file.getFilename(), fetchedFiles.get(0).getFilename());
        Assert.assertEquals(file.getId(), fetchedFiles.get(0).getId());
    }
}