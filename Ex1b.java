// Playfair Cipher Implementation
import java.util.Scanner;

public class Ex1b {
   enum ComputeMode {
      Encryption,
      Decryption
   }

   static int [] getRowColumnOf(int [][] mat, char c) {
      int r[] = new int[2];
      r[0] = -1;
      r[1] = -1; 
      for(int i = 0; i < 5; ++i) {
	 for(int j = 0; j < 5; ++j) {
	   if((char)mat[i][j] == c) {
	      r[0] = i;
	      r[1] = j;
	      return r;
	   } 
	 }
      }

      return r;
   }

   static int [][] getMatrix(String key) {
      int [][] mat = new int[5][5];
      key = key.replaceAll("J", "I"); 
      StringBuilder builder = new StringBuilder();
      String fill = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";
      for(int i = 0; i < fill.length(); ++i) {
	 if(builder.indexOf("" + fill.charAt(i)) != -1) {
	    continue;
	 }

	 builder.append(fill.charAt(i));
      }

      String values = builder.toString();
      for(int i = 0,k = 0; i < 5; ++i) {
	 for(int j = 0; j < 5; ++j) {
	    mat[i][j] = values.charAt(k);
	    ++k;
	 }
      }

      return mat;
   }

   static String compute(int [][] mat, String input, ComputeMode m) {
      int offset = m == ComputeMode.Encryption ? 1 : -1;
      StringBuilder output = new StringBuilder();

      // Remove Spaces and other non alphabetic characters.
      input = input.replaceAll("[^A-Z]", "");
      input = input.replaceAll("J", "I");

      StringBuilder inputbuf = new StringBuilder();
      // Rule 1: Add 'x' in between repeating
      // characters.
      for(int i = 0; i < input.length(); ++i) {
 	char c1 = input.charAt(i);
	if(i + 1 == input.length()) {
	   inputbuf.append(c1);
	   break;
	}	   
	char c2 = input.charAt(i + 1);

	inputbuf.append(c1);
	if(c1 == c2) {	
     	   inputbuf.append('X');
	}
      }

      input = inputbuf.toString();

      // Rule 0: If the length of the message is
      // odd then add 'x' to end of the message
      if(input.length() % 2 == 1) {
      	input = input + 'X';
      }

      // Compute in 2 pairs of chars.
      for(int i = 0; i < input.length(); i += 2) {
	 char c1 = input.charAt(i);
	 char c2 = input.charAt(i + 1);

	 int r1[] = getRowColumnOf(mat, c1);
	 int r2[] = getRowColumnOf(mat, c2);

	 char e1 = 'x';
	 char e2 = 'x';
	 
	 if(r1[0] == r2[0]) {
	    // Rule 2: c1 and c2 in same row so 
	    // get the next char next to c1 and 
	    // c2 in the matrix in circular fashion.
	    e1 = (char)mat[r1[0]][Math.floorMod((r1[1] + offset),5)];
	    e2 = (char)mat[r2[0]][Math.floorMod((r2[1] + offset),5)];
	 }else if(r1[1] == r2[1]) {
	    // Rule 3: c1 and c2 in same column so 
	    // get the down char of c1 and c2 in 
	    // circular fashion from the matrix.
	    e1 = (char)mat[Math.floorMod((r1[0] + offset),5)][r1[1]];
	    e2 = (char)mat[Math.floorMod((r2[0] + offset),5)][r2[1]];
	 }else { 
	    // Rule 4: c1 and c2 in different 
	    // column and row then get char
	    // in it's row and the other char's
	    // column
	    e1 = (char)mat[r1[0]][r2[1]];
	    e2 = (char)mat[r2[0]][r1[1]];
	 } 
 
	 output.append(e1);
	 output.append(e2); 
      }

      return output.toString();
   } 

   public static void main(String [] _args) {
      Scanner sc = new Scanner(System.in);
      
      System.out.print("Enter Secret Key: ");
      String key = sc.nextLine().toUpperCase();
      int [][] mat = getMatrix(key);

      System.out.print("Enter Message: ");
      String message = sc.nextLine().toUpperCase();
      String cipher = compute(mat, message, ComputeMode.Encryption);
      String computedPlaintext = compute(mat, cipher, ComputeMode.Decryption);

      System.out.println("Cipher Text: " + cipher.toString());
      System.out.println("Computed Plaintext: " + computedPlaintext.toString());
   }
}
