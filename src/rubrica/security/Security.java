package rubrica.security;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

/**
 * Singleton class that manages password management rules
 * @author cirod
 *
 */
public class Security {
	
	public ArrayList<String> commonPsw;
	private static Security single_instance = null;
	
	/**
	 * Private constructor to load the common password
	 * @throws IOException
	 */
	private Security() throws IOException {	
		
		this.commonPsw = new ArrayList<String>();

		Scanner s = new Scanner(new File("resources/MostCommonPsw.txt"));		
			while (s.hasNext()){
				commonPsw.add((String) s.next());
			}
	}	
	
	/**
	 * Get the instance of the class
	 * @return
	 * @throws IOException
	 */
	public static Security getInstance() throws IOException {
		
		if (single_instance == null) {
			single_instance = new Security();
		}
		return single_instance;
	}
	
	
	/**
	 * Calculate the strength of the chosen password
	 * It meets the following rules::
	 * Only accept passwords whose 'strength score' is greater than or equal to 6;
 	 * If the password length is less than 7, the score is always 0;
 	 * If the password length is greater than 10, score +2;
 	 * If the password contains at least 1 digit, score +2;
 	 * If the password contains at least 1 lower-case character, score +2:
  	 * If the password contains at least 1 upper-case character, score +2:
 	 * If the password contains at least 1 character between "~! @ # $% ^; 
	 * @param password chosen
	 * @return true if 'strength score' is greater than or equal to 6
	 */
    public boolean isStrongPass(String password){
        
        int strongCont = 0;
        
        // rule 1
        if (password.length() < 7)
            return false;
        
        // rule 2
        if (password.length() >= 10 )
        	strongCont += 2;
        
        // rule 3
        if (password.matches("(?=.*\\d).*"))
        	strongCont += 2;
        
        // rule 4
        if (password.matches("(?=.*[a-z]).*")) 
        	strongCont += 2;
        
        // rule 5
        if (password.matches("(?=.*[A-Z]).*"))
        	strongCont += 2;    
        
        // rule 6
        if (password.matches("(?=.*[~!@#$%^&*()_-]).*"))
        	strongCont += 2;
        
        return strongCont >= 6;        
    }
    
    /**
     * Check if password is too common
     * @param password
     * @return true if the chosen password is not among the most common 
     */
	public boolean isCommonPsw(String password) {
		return commonPsw.contains(password);
	}	
	
	/**
	 * Generate a salt for the chosen password
	 * @return
	 */
	public String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[16];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }
	
	/**
	 * Generate hash- password for the chosen password and salt
	 * @param StringToHash
	 * @param salt
	 * @return hash-password generate as string
	 */
	public String getHashSHA512(String StringToHash, String salt) throws NoSuchAlgorithmException {
        String generatedPassword = null;

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = md.digest(StringToHash.getBytes(StandardCharsets.UTF_8));
        generatedPassword = Base64.getEncoder().encodeToString(bytes);

        return generatedPassword;
    }
}
