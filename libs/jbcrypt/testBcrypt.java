import java.util.Random;

import org.mindrot.jbcrypt.BCrypt;

/**
 * testBcrypt.java
 * Created by Attawit Kittikrairit
 * Please import jbcrypt to its original package "org.mindrot.jbcrypt" before using this example
 */
public class testBcrypt {
	
	public static void main(String[] args) {
		String password, candidate, hash;
		
		password = "1234";
		System.out.println("----------------------");
		System.out.println("password:  "+ password);
		System.out.println("----------------------");
		// Hash a password for the first time
		hash = BCrypt.hashpw(password, BCrypt.gensalt());
		System.out.println("hash1:     "+ hash);
		

		candidate = password;
		System.out.println("candidate: "+ candidate);
		System.out.println("Match: "+ (BCrypt.checkpw(candidate, hash)?"TRUE":"FALSE"));
		candidate = String.valueOf(new Random().nextInt(10000));
		System.out.println("candidate: "+ candidate);
		System.out.println("Match: "+ (BCrypt.checkpw(candidate, hash)?"TRUE":"FALSE"));
		

		System.out.println("----------------------");
		// gensalt's log_rounds parameter determines the complexity
		// the work factor is 2**log_rounds, and the default is 10
		candidate = password;
		hash = BCrypt.hashpw(password, BCrypt.gensalt());
		System.out.println("hash2:     "+ hash);
		
		System.out.println("candidate: "+ candidate);
		System.out.println("Match: "+ (BCrypt.checkpw(candidate, hash)?"TRUE":"FALSE"));
		candidate = String.valueOf(new Random().nextInt(10000));
		System.out.println("candidate: "+ candidate);
		System.out.println("Match: "+ (BCrypt.checkpw(candidate, hash)?"TRUE":"FALSE"));
	}

}
