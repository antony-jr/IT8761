import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;

class DES {
   public static SecretKey getKey() {
      SecretKey key = null;
      try {
      KeyGenerator keyGen = KeyGenerator.getInstance("DES");
      key = keyGen.generateKey();
      } catch(Exception e) {
	 e.printStackTrace();
      }
      return key; 
   }

   public static byte [] compute(byte [] input, SecretKey key, int mode) {
      byte [] r = null;      
      try {
	 Cipher ctx = Cipher.getInstance("DES/ECB/PKCS5Padding");
	 ctx.init(mode, key);
	 r = ctx.doFinal(input);
      } catch(Exception e) {
	 e.printStackTrace();
      }
      return r;
   }

   public static void main(String [] args) {
      SecretKey key = getKey();
      String plaintext = "Hello";

      byte [] encrypted = compute(plaintext.getBytes(), key, Cipher.ENCRYPT_MODE);
      byte [] decrypted = compute(encrypted, key, Cipher.DECRYPT_MODE);

      System.out.println("Encrypted [Bytes]: " + encrypted);
      System.out.println("Decrypted:  "  + new String(decrypted));
   }
}
