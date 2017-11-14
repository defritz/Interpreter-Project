import java.util.Stack;

public class Lex {
	
	String s, lex;
	String[] tokens;
	int i = 0;
	boolean op1, op2;
	Stack<Boolean> st = new Stack<Boolean>();
	
	public Lex(String exp){
		s = exp;
		tokens = s.split("");
	}
	
	// Retrieves next term of the parsed expression
	public void get() {
		lex = tokens[i];
		i++;
		if(lex.equals("-")){
			lex = tokens[i];
			i++;
			if(lex.equals(">")){
				lex = "->";
			}
			else{
				lex = "@";		// Trash value that will not trigger any selection set checks, enforces that - must always be followed by an >
			}
		}
		if(lex.equals(" "))		// Skips whitespaces in input expression (other than previous -> case)
			get();
	}
	
	// Tests the given expression with each known syntax structure to determine its validity
	// If the current value of lex is not a period signaling the end of the expression
	// but the function returns true, then only a fragment of the expression is valid.
	public boolean valid(int i){
		if(i == 0){
			if(B() == true && lex.equals("."))
				return true;
			return false;
		}
		if(i == 1){
			if(IT() == true && lex.equals("."))
				return true;
			return false;
		}
		if(i == 2){
			if(IT_Tail() == true && lex.equals("."))
				return true;
			return false;
		}
		if(i == 3){
			if(OT() == true && lex.equals("."))
				return true;
			return false;
		}
		if(i == 4){
			if(OT_Tail() == true && lex.equals("."))
				return true;
			return false;
		}
		if(i == 5){
			if(AT() == true && lex.equals("."))
				return true;
			return false;
		}
		if(i == 6){
			if(AT_Tail() == true && lex.equals("."))
				return true;
			return false;
		}
		if(i == 7){
			if(L() == true && lex.equals("."))
				return true;
			return false;
		}
		if(i == 8){
			if(A() == true && lex.equals("."))
				return true;
			return false;
		}
		return false;
	}
	
	// Returns final evaluated value of the expression
	public boolean eval(){
		return st.pop();
	}
	
	// ---	Syntactic Analysis ---	//
	
	// Boolean Statement
	public boolean B() {
		if ("~ T F (".contains(lex)) {
			if (IT()) {
				return true;
			}
		}
		return false;
	}
	
	// Implication Head
	public boolean IT() {
		if ("~ T F (".contains(lex)) {
			if (OT()) {
				if (IT_Tail()){
					return true;
				}
			}
		}
		return false;
	}
	
	// Implication Tail
	public boolean IT_Tail() {
		if(lex.equals("->")){
			this.get();
			if(OT()){
				op2 = st.pop();
				op1 = st.pop();
				st.push((!op1) || op2);
				if(IT_Tail()){
					return true;
				}
			}
		}
		else if (". )".contains(lex)) { // Empty Case
			return true;
		}
		return false;
	}
	
	// Or Head
	public boolean OT() {
		if ("~ T F (".contains(lex)) {
			if(AT()){
				if(OT_Tail()){
					return true;
				}
			}
		}
		return false;
	}
	
	// Or Tail
	public boolean OT_Tail() {
		if(lex.equals("v")){
			this.get();
			if(AT()){
				op2 = st.pop();
				op1 = st.pop();
				st.push(op1 || op2);
				if(OT_Tail()){
					return true;
				}
			}
		}
		else if ("-> . )".contains(lex)) { // Empty Case
			return true;
		}
		return false;
	}
	
	// And Head
	public boolean AT() {
		if ("~ T F (".contains(lex)) {
			if(L()){
				if(AT_Tail()){
					return true;
				}
			}
		}
		return false;
	}
	
	// And Tail
	public boolean AT_Tail() {
		if(lex.equals("^")){
			this.get();
			if(L()){
				op2 = st.pop();
				op1 = st.pop();
				st.push(op1 && op2);
				if(AT_Tail()){
					return true;
				}
			}
		}
		else if ("v -> . )".contains(lex)) { // Empty Case
			return true;
		}
		return false;
	}
	
	// Literal
	public boolean L() {
		if ("T F (".contains(lex)) {
			if(A()){
				return true;
			}
		}
		else if(lex.equals("~")){
			this.get();
			if(L()){
				op1 = st.pop();
				st.push(!op1);
				return true;
			}
		}
		return false;
	}
	
	// Atom
	public boolean A() {
		if(lex.equals("T")){
			st.push(true);
			this.get();
			return true;
		}
		else if(lex.equals("F")){
			st.push(false);
			this.get();
			return true;
		}
		else if(lex.equals("(")){
			this.get();
			if(IT()){
				if(lex.equals(")")){
					this.get();
					return true;
				}
			}
		}
		return false;
	}
}
