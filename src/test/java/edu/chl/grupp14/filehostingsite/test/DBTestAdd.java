/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.grupp14.filehostingsite.test;

import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import edu.chl.grupp14.filehostingsite.core.entities.HostedFile;
import edu.chl.grupp14.filehostingsite.core.db.HostedFileDatabase;

/**
 *
 * @author Sam
 */
public class DBTestAdd {
    private static HostedFile file;
    private static HostedFileDatabase HostedFileDB;

    @BeforeClass
    public static void onlyOnce() {
        DBTestAdd.file = new HostedFile();
        DBTestAdd.file.setFilename("filen");
        byte[] bytes = new byte[2];
        bytes[0] = (byte) 2;
        bytes[1] = (byte) 4;
        DBTestAdd.file.setBytes(bytes);

        DBTestAdd.HostedFileDB = HostedFileDatabase.newInstance("filehosting_pu");
    }

    @Test
    public void testAddFile() {
        DBTestAdd.HostedFileDB.add(DBTestAdd.file);
        Assert.assertTrue(true);
    }
    
}
