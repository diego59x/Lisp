/**
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   @author DiegoAlvarez 19498
   @author CesarVinicio 16776
   @author Pablo 19
   Ultima modificacion 16/03/2020  
   Inteface que define las funciones de la calculadora
   Interprete de lisp
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */
import java.util.ArrayList;

public interface ICalculadora {
    
	public double operar(String oper, ArrayList<Double> numeros);
	
    /*public int sumar(ArrayList<Double> numeros);
    
    public int restar(ArrayList<Double> numeros);
    
    public int multiplicar(ArrayList<Double> numeros);
    
    public int dividir(ArrayList<Double> numeros);
    */
}
