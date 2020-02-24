/**
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   @param DiegoAlvarez19498
   @param CesarVinicio19
   @param Pablo19  
   Clase Main
   Interprete de lisp
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */




import java.util.*;
import java.io.*;
public class Main {

    
    public static void main(String[] args) throws Exception{
    	Reader reader = new Reader();
    	//Se lee el archivo
    	File f = new File("datos.txt");
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
        reader.splitOP(info);
        System.out.println(reader.algebra());
    	// Se separa el texto ingresado         	



    }
}