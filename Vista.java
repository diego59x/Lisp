/**
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   @author DiegoAlvarez 19498
   @author CesarVinicio 19
   @author Pablo 19
   Ultima modificacion 16/03/2020  
   Clase Vista
   Interprete de lisp
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */

import java.util.*;
import java.io.*;
public class Vista {


	public void inicio(){
		System.out.println("\t Bienvenido! \nElija una de las opciones disponibles\n 1. Leer archivo .txt (escribir junto con su extension |arhivo.txt|)\n 2. Ingresar una expresion\n 3. Salir");
	}
	public void salir(){
		System.out.println("Hasta pronto!");
	}
	public void resultado(int num){
		System.out.println("++++++++++++++++++++++++++++++++++++++\n+\t El resultado es "+ num +"\t     +\n++++++++++++++++++++++++++++++++++++++");
	}
}
