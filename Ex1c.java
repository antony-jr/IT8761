import java.util.Scanner;

public class Ex1c {
   static String compute(int [][] mat, String input) {
      StringBuilder inputbuf = new StringBuilder(input.toUpperCase());
      StringBuilder output = new StringBuilder();
      // PAD Input
      while(inputbuf.length() % 3 != 0) {
	inputbuf.append('X'); 
      }
      input = inputbuf.toString();

      for(int i = 0; i < input.length(); i += 3) {
	 int first = input.charAt(i) - 'A';
	 int second = input.charAt(i + 1) - 'A';
	 int third = input.charAt(i + 2) - 'A';
	 
	 for(int k = 0; k < 3; ++k) {
	    int o = Math.floorMod((first * mat[k][0]) + (second * mat[k][1]) + (third * mat[k][2]), 26);
	    output.append((char)(o + 'A')); 
	 }
      }
      return output.toString();
   }

   public static void main(String [] _args) {
      int [][] keymat = { {1,2,1}, {2,3,2}, {2,2,1}};
      int [][] invkeymat = { {-1,0,1}, {2,-1,0}, {-2,2,-1}};
      
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter Plaintext: "); 
      String plaintext = sc.nextLine().toUpperCase();
      plaintext.replaceAll("[^A-B]", "");

      String cipher = compute(keymat, plaintext);
      String computedPlaintext = compute(invkeymat, cipher);

      System.out.println("Cipher Text: " + cipher);
      System.out.println("Computed Plaintext: " + computedPlaintext);

   }
}
