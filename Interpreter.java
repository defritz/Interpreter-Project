import java.io.*;
import java.util.*;

// Derek Fritz
// Prints the syntactic validity and semantic evaluation
// of the user-provided expression (via console).

public class Interpreter {
	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the expression to be parsed: ");	// Prompts user for expression input
		String s = sc.nextLine();
		sc.close();
		s = s + " $";
		
		Lex l = new Lex(s);
		l.get();
		
		// Finds correct syntax function to call
		int i;
		for(i = 0; i < 9; i++){
			if(l.valid(i) == true){
				System.out.println("This expression is valid.");
				boolean evaluation = l.eval();
				System.out.println("The provided expression evaluates to: " + evaluation);
				break;
			}
			l = new Lex(s);
			l.get();
		}
		if(i == 9){
			System.out.println("This expression is not valid under the syntax rules provided.");
		}
	}
}
