/**
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   @author DiegoAlvarez 19498
   @author CesarVinicio 16776
   @author Pablo 19
   Ultima modificacion 16/03/2020  
   Clase Main
   Interprete de lisp
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */

import java.util.ArrayList;
	
public class Calculadora implements ICalculadora{
    
	public double operar (String operador, ArrayList<Double> numeros){
        double resultado = 0;
        switch (operador){
            case "+": 
            	resultado = sumar(numeros);
            	break;
            case "-": 
            	resultado = restar(numeros);
            	break;
            case "*": 
            	resultado = multiplicar(numeros);
            	break;
            case "/": 
            	resultado = dividir(numeros);
            	break;
            case ">": 
            	resultado = mayor(numeros);
            	break;
            case "<": 
            	resultado = menor(numeros);
            	break;
            case "=": 
            	resultado = igual(numeros);
            	break;
        }
        return resultado;
    }
    
    public double sumar( ArrayList<Double> nums ) {
    	double suma = 0;
        if ( nums.size()==1 ) {
        	suma = nums.get(0);
        }
        else {
            for (double num: nums) {
                suma+= num;
            }
        }
        return suma;
    }
    
    public double restar( ArrayList<Double> nums ) {
    	double resta = nums.get(0);
        if ( nums.size()==1 ) {
          resta = -resta;
        }
        else {
            for (int i=1; i<nums.size(); i++) {
                resta-= nums.get(i);
            }
        }
        return resta;
    }
    
    public double multiplicar( ArrayList<Double> nums )  {
    	double multiplica = 1;
        if ( nums.size()==1 ) {
        	multiplica = nums.get(0);
        }
        else {
            for (double num: nums) {
                multiplica*= num;
            }
        }
        return multiplica;
    }
    
    public double dividir( ArrayList<Double> nums ) {
    	double division = nums.get(0); 
        if ( nums.size() != 1 ){
            for (int i=1; i<nums.size(); i++) {
                division/= nums.get(i);
            }
        } else {
        	return 1/division;
        }
        return Math.round((division)*1000)/1000;
    }
    
    private double igual ( ArrayList<Double> nums ) {
        if ( nums.size() == 1 ) { 
        	return 1;
        } else {
            for (int i=0; i<nums.size()-1; i++) {
                if ( !nums.get(i).equals(nums.get(i+1)) ) {
                	return 0;
                }
            }
        }
        return 1;
    }
    
    private double mayor( ArrayList<Double> nums ) {
        if ( nums.size() == 1 ) {
        	return 1;
        } else {
            for ( int i=0; i < nums.size() - 1; i++) {
                if ( nums.get(i) < nums.get(i+1) ) {
                	return 0;
                }
            }
        }
        return 1;
    }

    private double menor( ArrayList<Double> nums ) {
        if ( nums.size()==1 ) {
        	return 1;
        } else {
            for ( int i=0; i < nums.size() - 1 ; i++ ) {
                if ( nums.get(i) > nums.get(i+1) ) {
                	return 0;
                }
            }
        }
        return 1;
    }

}
