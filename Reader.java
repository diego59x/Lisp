
import java.util.*;
import java.io.*;
public class Reader {
	 
    String operacion[];
    int operando1;
    int operando2;
    int total = 0;
    boolean bandera = true;
    ICalculadora calculadora = new Calculadora();
    IStack<String> miStack = new Stack<String>();
    public void splitOP(String instruccion){
    	
    	operacion = instruccion.trim().split(" ");
    	// Se agrega a un stack
    	for(int i = 0; i < operacion.length ; i++){
    		
    		miStack.push(operacion[i]);
    		
    			
    	}
   	}
   	public Integer algebra(){
        	// Se intenta convertir a entero los caracteres ingresados
   			for(int i=0;i<operacion.length;i++){
		        try{
		            Integer.parseInt(operacion[i]);
		            bandera = true;
		        }catch(NumberFormatException excepcion){
		            bandera = false;
	        	}
      		
      		
      		 	if (bandera == true){
		            miStack.push(operacion[i]);
		        }else{
		            if("+".equals(operacion[i])){
		               
		                // se obtienen los dos operandos
		                operando2 = Integer.parseInt(miStack.pop());
                	    operando1 = Integer.parseInt(miStack.pop());
		                total = calculadora.sumar(operando1,operando2);
		                // se agrega el resultado de la operacion al stack
		                miStack.push(String.valueOf(total));
		            }else if("-".equals(operacion[i])){
		               
		                // se obtienen los dos operandos
		                operando2 = Integer.parseInt(miStack.pop());
                	    operando1 = Integer.parseInt(miStack.pop());
		                total = calculadora.restar(operando1,operando2);
		                // se agrega el resultado de la operacion al stack
		                miStack.push(String.valueOf(total));
		            }else if ("/".equals(operacion[i])){
		               
		                // se obtienen los dos operandos
		                operando2 = Integer.parseInt(miStack.pop());
                	    operando1 = Integer.parseInt(miStack.pop());
		                total = calculadora.dividir(operando1,operando2);
		                // se agrega el resultado de la operacion al stack
		                miStack.push(String.valueOf(total));
		            }else if ("*".equals(operacion[i])){
		                
		                // se obtienen los dos operandos
		               operando2 = Integer.parseInt(miStack.pop());
                	   operando1 = Integer.parseInt(miStack.pop());
                	   total = calculadora.multiplicar(operando1,operando2);
                	   // se agrega el resultado de la operacion al stack
               		   miStack.push(String.valueOf(total));

		            }else{
		                System.out.println("ERROR");
		            }
		        }
		    }
  		
  		total = Integer.parseInt(miStack.pop());
  		return  total;
  	}

}