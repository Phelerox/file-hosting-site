/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import se.baxemyr.filehostingsite.core.User;

/**
 *
 * @author phelerox
 */
public class UserTests {
    
    private static User user;
    private static String password;
    
    public UserTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        password = "password";
        user = new User(1, "Phelerox", "Marco Baxemyr", "baxemyr@gmail.com", password);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void checkHash() {
        System.out.println("Password: " + password);
        System.out.println("Salt: " + user.getSalt());
        System.out.println("Salt length in bytes: " + user.getSalt().length);
        System.out.println("Hash: " + user.getHash());
        System.out.println("Hash length (base 16): " + user.getHash().length());
        
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] passwordBytes = password.getBytes();
            //Append passwordBytes after salt
            byte[] saltAndPassword = new byte[user.getSalt().length + passwordBytes.length];
            System.arraycopy(user.getSalt(), 0, saltAndPassword, 0, user.getSalt().length);
            System.arraycopy(passwordBytes, 0, saltAndPassword, user.getSalt().length, passwordBytes.length);
            digest.update(saltAndPassword, 0, saltAndPassword.length);
            String hash = new BigInteger(1, digest.digest()).toString(16);
            Assert.assertTrue(user.getHash().equals(hash));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
