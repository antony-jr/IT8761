// Caesar Cipher Implementation
import java.util.Scanner;

public class Ex1a {
   enum ComputeMode { 
      Encryption,
      Decryption
   }

   static String compute(String plaintext, int key, ComputeMode m) {
      StringBuffer cipher = new StringBuffer(plaintext);
      int offset = m == ComputeMode.Encryption ? key : 26 - key;
      for(int i = 0; i < plaintext.length(); ++i) {
	char c = plaintext.charAt(i);
	if(c > 'z' || c < 'A') {
	   continue;
	}
	char base = c > 'Z' ? 'a' : 'A';
	char e = (char)(base + ((c - base + offset) % 26));
	cipher.setCharAt(i, e);	
      }
      
      return new String(cipher);
   }

   public static void main(String [] _args) {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter Plaintext: ");
      String plaintext = sc.nextLine();

      System.out.print("Enter Key[Range: 1-26]: ");
      int key = 0;

      try{
	 key = Integer.parseInt(sc.nextLine());
      } catch(Exception e) {
	 System.out.println("Key should be an integer.");
	 return; 
      }
      if(key > 26) {
	 System.out.println("Key should be in range 1-26.");
	 return; 
      }

      String encrypted = compute(plaintext, key, ComputeMode.Encryption);
      String computedPlaintext = compute(encrypted, key, ComputeMode.Decryption);

      System.out.println("Cipher Text: " + encrypted);
      System.out.println("Computed Plaintext: " + computedPlaintext);
   }
}
