/**
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   @author DiegoAlvarez 19498
   @author CesarVinicio 16776
   @author Pablo 19
   Ultima modificacion 16/03/2020  
   Clase Vista
   Interprete de lisp
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */

import java.util.*;
import java.io.*;
public class Vista {

	// Menu:
	public void inicio(){
		System.out.println("\t Bienvenido! \nElija una de las opciones disponibles\n 1. Leer archivo .txt (escribir junto con su extension |arhivo.txt|)\n 2. Ingresar una expresion\n 3. Salir");
	}
	// Mensaje de salida:
	public void salir(){
		System.out.println("\t++++++++++++++++++++++++++++++++++++++++++++++++\n\t\t\t  Hasta Pronto! \t\t\t\n\t++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	// Mensaje por defecto 
	public void mensaje(String t){
		System.out.println("\t++++++++++++++++++++++++++++++++++++++++++++++++\n\t\t\t "+ t +"\t\t\t\n\t++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	// Mensaje que muestra la operacion ingresada a traves del txt
	public void operacion(String op){
		System.out.println("\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\t Tu funcion es " + op + " \t\t\t\n\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
}
