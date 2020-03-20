/**
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   @author DiegoAlvarez 19498
   @author CesarVinicio 16776
   @author Pablo Reyna 19822
   Ultima modificacion 16/03/2020  
   Clase que maneja el compilador de Lisp
   Interprete de lisp
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */

import java.util.*;
import java.io.*;

public class Controlador  {
    Scanner scan = new Scanner(System.in);
    private String compilado, evalToken, info = "";
    private Tokens tokens;
    private ArrayList<String> tokenList;
    private ArrayList<String> params;
    private int cont = 0, cont2 = 0, opcion = 0, opcion1 = 0;
    private Vista vista = new Vista();
    private LispTree lispTree = new LispTree(null);

    // Constructor 
    public Controlador() { 
      tokens = Tokens.getInstance();
    }


    private void leerTxt(String txt){
        //Se lee el archivo
        File f = new File(txt);
        BufferedReader entrada;
        String exepcion = "";
        try {
             entrada = new BufferedReader(new FileReader(f));
             while(entrada.ready()){
                  info += entrada.readLine();
              }
        }catch (IOException e) {
            e.printStackTrace();
            exepcion += "\nEl archivo no se encuentra";
        }
        vista.operacion(info);
        compilar(info);
        info = "";
    }
    
    public void opciones(){
        // se inicializa el menu
        boolean menu = true;
        while(menu){
            vista.inicio();
            int opcion = verificacion();
            if(opcion == 1){
                // se llama al metodo que lee el archivo
                // se pide que ingrese el nombre con la extension .txt
                leerTxt(scan.nextLine());
            }else if(opcion == 2){
                // se llama al metodo que identifica lo ingresado
                compilar(scan.nextLine());
            }else{
                // muestra mensaje de finalizacion
            vista.salir();
            menu = false;
            }
        }   
    }

    private Integer verificacion(){
        // se verifica que la opcion ingresada sea entera
        // y que este dentro del rango 
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
    
    
    protected void compilar(String lispCode){
    	//se compila una cadena de codigo ingresada 
    	
    	//se obtienen los tokens
    	tokenList = tokens.getTokens();
        
    	//se valida que venga el mismo numero de parentesis
    	int pa=0;
        for (int i=0; i< lispCode.length(); i++) {
            if (lispCode.substring(i,i+1).equals("(")) pa++;
        }

        int pc = 0;
        for (int i=0; i< lispCode.length(); i++) {
            if (lispCode.substring(i,i+1).equals(")")) pc++;
        }
        
        //la expresión viene entre parentesis y esta correcta 
        if (lispCode.startsWith("(") && lispCode.endsWith(")") && (pa == pc)){
            
        	//inserta una linea de codigo para que se compile
        	compilado = (String) insert(lispCode, null, 1);
            
        	if (!evalToken.equals("Error")) {
        		//evaluar expresión
            	compilado= lispTree.evalExpression();
            }
        	
        } else compilado= "Error al compilar el codigo";
        // se manda la conclusion de lo realizado a la vista 
        vista.mensaje(compilado);
    }
    
    private Object insert (String lineCode, LispTree root, int op){
    	//funcion que inserta una linea de codigo para que sea analizada
        try {
            int i = 0;
            boolean next = false, procesar = false, funParams = true;
            LispTree nLispTree = new LispTree(null);
            
            evalToken = "";
            String sToken="";
            
            //se recorre la expresion caracter por caracter
            while (i<lineCode.length()){
                
            	//la expresion inicia con parentesis abierto
            	if (lineCode.substring(i,i+1).equals("(")){
                    
            		//incrementa la variable i cuando son espacios en blanco
            		while (lineCode.substring(i+1,i+2).equals(" ")) {
                        i++;
                    }
            		
            		//se valida que la función tenga la forma correcta 
            		
                    if (lineCode.substring(i+1,i+2).equals("(")) {
                    	throw new Exception();
                    }
                    
                    if (isNum(lineCode.substring(i+1, i+2))) {
                    	throw new Exception();
                    }else {
                        int i_validacion = i+1;
                        //se valida que la expresión ingresada sea una palabra reservada
                        String reserved_key = getToken(lineCode,i_validacion);
                        if (!tokenList.contains(reserved_key)) {
                        	throw new Exception();
                        }
                    }
                    
                    //si esta correcta se cambia la bandera a true para que la expresion se procese
                    procesar = true;
                }

                if (procesar){
                	//se inicia el analisis de la expresión
                    if (evalToken.equals("")) {
                        evalToken = getToken(lineCode,i+1);
                        i+=evalToken.length();
                        next =true;
                        
                        if (!tokens.tokenExists(evalToken)) {
                        	tokens.addToken(evalToken);
                        }
                        nLispTree = new LispTree(evalToken);
                    }
                    //se recorre la expresion para ejecutar el codigo lisp, se valida
                    //si es una operación aritmetica o si es alguna funcionalidad de las palabras reservadas
                    if(next){
                        if (!lineCode.substring(i,i+1).equals(" ")){
                            if (!funParams) {
                            	throw new Exception();
                            }
                            if (lineCode.substring(i,i+1).equals("(")){
                                String lista = "";
                                cont = 0;
                                boolean bcont = true;
                                for (int j=i; j< lineCode.length() && bcont;  j++) {
                                    if (lineCode.substring(j,j+1).equals("(")) {
                                    	cont++;
                                    }
                                    if (lineCode.substring(j,j+1).equals(")")) {
                                    	bcont = false;
                                    }
                                }
                                    
                                while (cont>0){
                                    if (!lineCode.substring(i,i+1).equals(")")){
                                        lista+=lineCode.substring(i,i+1);
                                        i++;
                                    } else {
                                        lista+=lineCode.substring(i,i+1);
                                        i++;
                                        cont--;
                                    }
                                }
                                insert(lista,nLispTree,1);
                            }
                            if (isNum(lineCode.substring(i,i+1))){
                                String list = "";
                                list = getToken(lineCode,i);
                                i+=list.length();
                                nLispTree.insertChild(list);
                            }
                            if (evalToken.equals("cond")){
                                boolean band= false;
                                String cond="", action=""; 

                                if (lineCode.substring(i,i+1).toLowerCase().equals("d")) {
                                	i++;
                                }

                                while (lineCode.substring(i,i+1).equals(" ")) {
                                	i++;
                                }
                                
                                if (lineCode.substring(i,i+1).equals("(")){
                                    i++;
                                    cond= getFuncParams(lineCode,i);
                                    i+=cond.length();
                                    band =true;
                                }
                                
                                if (band){
                                    while (lineCode.substring(i,i+1).equals(" ")) i++;
                                    nLispTree = (LispTree) insert(cond,null,2);
                                    String res = nLispTree.evalExpression();
                                    if (res.equals("T")) next=false;
                                    while (lineCode.substring(i,i+1).equals(" ")) i++;
                                    if (!lineCode.substring(i,i+1).equals("(")) {
                                    	throw new Exception();
                                    }
                                    else {
                                        action=getFuncParams(lineCode,i);
                                        i+=action.length();
                                        i++;
                                        while (lineCode.substring(i,i+1).equals(" ")) {
                                        	i++;
                                        }
                                        if (res.equals("T")) {
                                        	nLispTree=(LispTree)insert(action, null,2);
                                        }

                                    }
                                }

                            }
                            //cuando se define una funcion nueva
                            if (evalToken.equals("defun")){
                                Object[] definicionFun = new Object[3];
                                i++;
                                int contp = 0;
                                params = new ArrayList<>();
                                String funcion ="";
                                while (lineCode.substring(i,i+1).equals(" ")) {
                                	i++;
                                }
                                if (!lineCode.substring(i,i+1).equals(" ")){
                                    while (!lineCode.substring(i,i+1).equals(" ")){
                                        sToken+= lineCode.substring(i,i+1);
                                        i++;
                                    }
                                }
                                else {
                                	throw new Exception();
                                }
                                evalToken ="Error";
                                if (!tokens.tokenExists(sToken)) {
                                	tokens.addToken(sToken.toLowerCase());
                                }
                                
                                while (lineCode.substring(i,i+1).equals(" ")) {
                                	i++;
                                }
                                
                                if (!lineCode.substring(i,i+1).equals("(")) {
                                	throw new Exception();
                                }
                                else {
                                    funParams = false;
                                    i++;
                                    while (!lineCode.substring(i,i+1).equals(")")){
                                        if (lineCode.substring(i,i+1).equals(" ")) {
                                        	i++;
                                        }
                                        else {
                                            String parametro = getToken(lineCode,i);
                                            i+= parametro.length();
                                            contp++;
                                            if (parametro.contains("(")||parametro.contains(")")) {
                                            	throw new Exception();
                                            }
                                            params.add(parametro);
                                        }
                                    }
                                    i++;
                                }
                                while (lineCode.substring(i,i+1).equals(" ")) {
                                	i++;
                                }
                                if (!lineCode.substring(i,i+1).equals("(")) {
                                	throw new Exception();
                                }
                                else {
                                    funcion+=getFuncParams(lineCode,i);
                                    i+=funcion.length();
                                }
                                
                                //se agrega la funcion nueva para que este disponible
                                definicionFun[0] = contp;
                                definicionFun[1]= params;
                                definicionFun[2]=funcion;
                                if (!tokens.functionExists(sToken)) {
                                	tokens.addFunction(sToken,definicionFun);
                                }
                            }
                            //si se esta evaluando una funcion ya ingresada
                            if (tokens.functionExists(evalToken)){
                                boolean seg = false;
                                Object[] func = tokens.getFunciton(evalToken);
                                while (lineCode.substring(i,i+1).equals(" ")) {
                                	i++;
                                }

                                if (lineCode.substring(i,i+1).equals("(")){
                                    cont2++;
                                    String par = getFuncParams(lineCode,i);
                                    i+=par.length();
                                    nLispTree.insertChild(insert(par,nLispTree,1));
                                }
                                if (isNum(lineCode.substring(i,i+1))){
                                    cont2++;
                                    String par = getToken(lineCode,i);
                                    i+=par.length();
                                    nLispTree.insertChild(par);
                                }
                                if (lineCode.substring(i,i+1).equals(")")) {
                                	seg =true;
                                }
                                if (cont2>(int)func[0]) {
                                	throw new  Exception();
                                }
                                if (seg){
                                    String res = nLispTree.evalExpression();
                                    nLispTree = (LispTree) insert(res,null,2);

                                }
                            }

                        }
                    }
                } else throw new Exception();
                i++;
            }
            if (root != null){
                root.insertChild(nLispTree);
            } else if (op==1) {
            	lispTree = nLispTree;
            }
            if (op==2) {
            	return nLispTree;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return "Error al compliar el codigo";
        }
        return "Se agrego correctamente";
    }

    //obtiene los parametros de una funcion definida por el usuario
    private String getFuncParams (String lispCode, int contfp){
        String par = lispCode.substring(contfp,contfp+1);
        contfp++;
        int conta = 1;
        while (conta>0){
            if (lispCode.substring(contfp,contfp+1).equals("(")) conta++;
            if (lispCode.substring(contfp,contfp+1).equals(")")) conta--;
            par+=lispCode.substring(contfp,contfp+1);
            contfp++;
        }
        return par;
    }
    
    //obtiene un token del codigo analizado
    private String getToken (String lispCode, int contador){
        String t = "";
        while (!lispCode.substring(contador,contador+1).equals(" ")&& !lispCode.substring(contador,contador+1).equals(")")){
            t += lispCode.substring(contador,contador+1);
            contador++;
        }
        t = t.toLowerCase();
        return t;
    }
    
    //funcion para determinar si es numero
    public boolean isNum(String token) {
        try {
            double n = Integer.parseInt(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
