package se.baxemyr.filehostingsite.logic;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import se.baxemyr.filehostingsite.core.AppUser;

/**
 * Based on info from http://crackstation.net/hashing-security.htm
 * @author Marco Baxemyr
 */
public class UserAuthentication {
    
    public static boolean authenticate(AppUser user, String attemptedPassword) {
        String correctHash = user.getPasswordHash();
        return correctHash.equals(hash(attemptedPassword, user.getSalt()));
    }
    
    public static void changePassword(AppUser user, String newPassword) {
        byte[] salt = generateSalt();
        user.setSalt(salt);
        user.setPasswordHash(hash(newPassword, salt));
    }
    
    public static byte[] generateSalt() {
        byte[] salt = new byte[64]; //512 bits because salts should be at least as long as the hash
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            random.nextBytes(new byte[64]); //Force SecureRandom to generate a seed
            random.nextBytes(salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return salt;
    }
    
    public static String hash(String password, byte[] salt) {
        String SHA512 = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512", "SUN");
            byte[] passwordBytes = password.getBytes();
            //Append passwordBytes after salt
            byte[] saltAndPassword = new byte[salt.length + passwordBytes.length];
            System.arraycopy(salt, 0, saltAndPassword, 0, salt.length);
            System.arraycopy(passwordBytes, 0, saltAndPassword, salt.length, passwordBytes.length);
            digest.update(saltAndPassword, 0, saltAndPassword.length);
            SHA512 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return SHA512;
    }
    
    
}
