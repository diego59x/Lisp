/**
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   @author DiegoAlvarez 19498
   @author CesarVinicio 16776
   @author Pablo Reyna 19822
   Ultima modificacion 18/03/2020  
   Clase Tokens: se definen los tokens que serán utilizados en el codigo lisp
   ademas de las funciones necesarias para manejarlos
   Interprete de lisp
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Tokens {
	private static String[] token_list = { "+", "-", "*", "/", "quote", "defun", "atom", "list", "equal", "cond", "set", "<", ">", "=", "c"};
	private static Tokens thisInstance = new Tokens(token_list);
    
	private ArrayList<String> token= new ArrayList<>();
    private HashMap<String,Object[]> map;
    
    public static Tokens getInstance() {
        return thisInstance;
    }
    
    //genera listado de tokens
    private Tokens(String[] token_list) {
        token.addAll(Arrays.asList(token_list));
        map = new HashMap<>();
    }

    //agrega un token nuevo
    public void addToken(String newToken){
        token.add(newToken.toLowerCase());
    }
    
    //agrega una funcion al hashmap
    public void addFunction (String token, Object[] func){
        map.put(token.toLowerCase(),func);
    }
    
    //retorna el listado de tokens
    public ArrayList<String> getTokens(){
        return token;
    }
    
    //obtiene una funcion
    public Object[] getFunciton (String token){
        return map.get(token.toLowerCase());
    }
    
    //verifica si el token existe
    public Boolean tokenExists(String token){
        if (token.contains(token.toLowerCase())) {
        	return true;
        }else {
        	return false;
        }
    }

    //verifica que una funcion agregada por el usuario exista 
    public Boolean functionExists(String token){
        if (map.containsKey(token.toLowerCase())) {
        	return true;
        }else {
        	return false;
        }
    }

}
