import java.util.Scanner;

public class Ex1d {
   enum ComputeMode {
      Encryption,
      Decryption
   }

   static String compute(String input, String key, ComputeMode m) {
      input = input.toUpperCase();
      key = key.toUpperCase();
      StringBuilder output = new StringBuilder();
      for(int i = 0; i < input.length(); ++i) {
	 char c = input.charAt(i);
	 if(c > 'Z' || c < 'A') {
	    output.append(c);
	    continue;
	 }
	 int base = key.charAt(i % key.length()) - 'A';
	 base = m == ComputeMode.Encryption ? +(base) : -(base);
	 int result = (input.charAt(i) - 'A') + base;
	 output.append((char)(Math.floorMod(result, 26) + 'A')); 
      }
      return output.toString();
   }

   public static void main(String [] _args) {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter Key: ");
      String key = sc.nextLine().toUpperCase();
      System.out.print("Enter Plaintext: ");
      String plaintext = sc.nextLine().toUpperCase();

      String cipher = compute(plaintext, key, ComputeMode.Encryption);
      String computed = compute(cipher, key, ComputeMode.Decryption);

      System.out.println("Cipher Text: " + cipher);
      System.out.println("Computed Plaintext: " + computed);
   }
}
