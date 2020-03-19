/**
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   @author DiegoAlvarez 19498
   @author CesarVinicio 16776
   @author Pablo Reyna 19822
   Ultima modificacion 16/03/2020  
   Inteface que define las funciones de la calculadora
   Interprete de lisp
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */
import java.util.ArrayList;

public interface ICalculadora {
    
	public double operar(String oper, ArrayList<Double> numeros);
  //pre: se recibe un string que indica la operacion y un arraylist con todos los numeros
  //post: se retorna el resultado
	public double sumar( ArrayList<Double> nums );
  //pre: recibe un arraylist con los numeros a operar
  //post: retorna el resultado
  public double restar( ArrayList<Double> nums );
  //pre: recibe un arraylist con los numeros a operar
  //post: retorna el resultado
  public double multiplicar( ArrayList<Double> nums );
  //pre: recibe un arraylist con los numeros a operar
  //post: retorna el resultado
  public double dividir( ArrayList<Double> nums );
  //pre: recibe un arraylist con los numeros a operar
  //post: retorna el resultado
}
