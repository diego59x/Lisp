
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Compiler c = new Compiler();
		
		String compilado;
		
		c.compilar("(- 9 4)");
		c.compilar("(* 3 (- 9 4))");
		c.compilar("(/ 2 1)");
		c.compilar("(+ (- 9 4) (* 3 (- 9 4)))");
		c.compilar("(equal 5 5)");
		c.compilar("(equal 5 (- 9 4))");
		c.compilar("(equal 5 (- 9 3))");
		
		//c.compilar("(defun test() (+ (- 9 4) (* 3 (- 9 4))))");
		//System.out.println(compilado);
	}

}
