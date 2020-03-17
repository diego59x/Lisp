/**
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   @author DiegoAlvarez 19498
   @author CesarVinicio 19
   @author Pablo 19
   Ultima modificacion 16/03/2020  
   Clase Reader (controlador)
   Interprete de lisp
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */

import java.util.*;
import java.io.*;
public class Reader {
	Scanner scan = new Scanner(System.in);
    String operacion[], instruccion;
    int total = 0 , operando1, operando2, opcion1 = 0;
    boolean bandera = true;
    ICalculadora calculadora = new Calculadora();
    ArrayList<String> operandos = new ArrayList<String>();
    IStack<String> miStack = new Stack<String>();
    Vista vista = new Vista();

    private void leerTxt(String txt){
    	//Se lee el archivo
    	File f = new File(txt);
    	BufferedReader entrada;
    	String info = " ";
    	try {
             entrada = new BufferedReader(new FileReader(f));
             while(entrada.ready()){
                  info += entrada.readLine() + "\n";
              }
        }catch (IOException e) {
            e.printStackTrace();
		}
		splitOP(info);
    }
    public void opciones(){
    	vista.inicio();
    	int opcion = verificacion();
    	if(opcion == 1){
    		leerTxt(scan.nextLine());
    		vista.resultado(algebra());
    	}else if (opcion == 2){
    		escribir();
    	}else{
    		vista.salir();
    	}
    }
    public void escribir(){
    	instruccion = scan.nextLine();
    	splitOP(instruccion);
    	vista.resultado(algebra());
    }
	private Integer verificacion(){

		Boolean pedir = true;
		while(pedir){
			String opcion = scan.nextLine();
			try {
				opcion1 = Integer.parseInt(opcion);
				if (opcion1<=3 && opcion1>0) {
					pedir = false;
				} else {
					System.out.println("Ingrese un numero dentro del rango");
				}	
			} catch (Exception e){
				System.out.println("Ingrese un numero entero");
			}
		}
		pedir = true;
		return opcion1;
	} 

    private void splitOP(String instruccion){
    	/**
    	 @param Hice-una-copia-de-lo-ingresado-por-si-necesitan-guiarse
    	*/
    	String operacionOriginal = instruccion;
    	// Se quitan los parentesis para poder operar lo que se ha ingresado 
    	operacion = instruccion.replace("(","").replace(")","").trim().split(" ");
    	// Se agrega a un stack
    	for(int i = 0; i < operacion.length ; i++){
    		miStack.push(operacion[i]);
       	}

   	}
   	private Integer algebra(){
   		boolean operador = false;
   		for(int i = 0; i < operacion.length ; i++){
    		operandos.add(miStack.pop());
       	}
       	//si se encuentran dos numeros y un operando se puede operar
       	for(int i = 0; i < operandos.size(); i++){
       		try{
		        Integer.parseInt(operandos.get(i));
		        Integer.parseInt(operandos.get(i+1));
		        operador = true;
		    }catch(NumberFormatException excepcion){
		        operador = false;
		    }	
		    if ("+".equals(operandos.get(i+2)) || "-".equals(operandos.get(i+2)) || "/".equals(operandos.get(i+2)) || "*".equals(operandos.get(i+2))){
		     	bandera = true; 

		    }else{
		     	bandera = false;
		    }
		    
		  	        		
		    if (bandera == true && operador == true){
					// se obtienen los dos operandos
       				operando2 = (Integer.parseInt(operandos.get(i)));
           		 	operando1 = (Integer.parseInt(operandos.get(i+1)));
           		 	
           		 	
       				if("+".equals(operandos.get(i+2))){   
		                total = calculadora.sumar(operando1,operando2);
		                // se agrega el resultado de la operacion al stack
		                operandos.add(String.valueOf(total));
		                operandos.remove(i);
           		 		operandos.remove(i);
           		 		operandos.remove(i);
		            }else if("-".equals(operandos.get(i+2))){
		                total = calculadora.restar(operando1,operando2);
		                // se agrega el resultado de la operacion al stack
		                operandos.add(String.valueOf(total));
		            }else if ("/".equals(operandos.get(i+2))){
		                total = calculadora.dividir(operando1,operando2);
		                // se agrega el resultado de la operacion al stack
		                operandos.add(String.valueOf(total));
		            }else if ("*".equals(operandos.get(i+2))){
                	   total = calculadora.multiplicar(operando1,operando2);
                	   // se agrega el resultado de la operacion al stack
               		   operandos.add(String.valueOf(total));
		            }else{
		                System.out.println("ERROR");	
       				}
       				for(int o = 0; o < operandos.size() ; o++){
       					System.out.println("num: " + operandos.get(o));
       				}
       		
    		
    		}
       	}
    	return total;
		}
}








       	