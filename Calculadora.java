/**
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   @author DiegoAlvarez 19498
   @author CesarVinicio 19
   @author Pablo 19
   Ultima modificacion 16/03/2020  
   Clase Main
   Interprete de lisp
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */

public class Calculadora implements ICalculadora{
    int resultado;
    
    public int sumar(int operando1, int operando2){
        resultado = operando1 + operando2;  
        return resultado;
    }
    
    public int restar(int operando1, int operando2){
        resultado = operando1 - operando2;  
        return resultado;
    }
    
    public int multiplicar(int operando1, int operando2){
        resultado = operando1 * operando2;  
        return resultado;
    }
    
    public int dividir(int operando1, int operando2){
        resultado = operando1 / operando2;  
        return resultado;
    }
    
}
