/**
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   @author DiegoAlvarez 19498
   @author CesarVinicio 16776
   @author Pablo Reyna 19822
   Ultima modificacion 16/03/2020  
   Clase LispTree
   Clase para ejecutar las funciones de Lisp 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */
import java.util.ArrayList;

public class LispTree {
    private String lex;
    private Calculadora calc;
    public ArrayList<Object> childs;
    private ArrayList<Double> numeros = new ArrayList<Double>();
    private Tokens tokens = Tokens.getInstance();

    public LispTree (String lexema){
        this.lex = lexema;
        childs = new ArrayList<Object>();
        calc = new Calculadora();
    }
    
    public String evalExpression() {
        String result = "";
        //System.out.println(lex);
        try {
            switch (lex) {
                case "+": {
             	   result = isOperator("+");
             	   break;
                }
                case "-": {
             	   result = isOperator("-");
             	   break;
                }
                case "*": {
             	   result = isOperator("*");
             	   break;
                }
                case "/": {
             	   result = isOperator("/");
             	   break;
                }
                case ">": {
             	   result = isLogic(isOperator(">"));
             	   break;
                }
                case "<": {
             	   result = isLogic(isOperator("<"));
             	   break;
                }
                case "=": {
             	   result = isLogic(isOperator("="));
             	   break;
                }
                case "equal": {
             	   result = isLogic(isOperator("="));
             	   break;
                }
                case "atom": {
             	   result = dat("T","NIL");
             	   break;
                }
                case "list": {
             	   result = dat("NIl", "T");
             	   break;
                }
                default: {
             	   
                    Object[] obj = tokens.getFunciton(lex);
                    if ((int) obj[0] != childs.size()) {
                 	   throw new Exception();
                    }
                    
                    ArrayList<String> parametos = (ArrayList<String>) obj[1];
                    String sObj = (String) obj[2];
                    
                    for (Object c: childs){
                       try {
                           LispTree b = (LispTree) c;
                           c = b.evalExpression();
                       } catch (Exception e){}
                    }
                    
                    int j=0;
                    String sObjParams = "";
                    for (String a: parametos) {
                        sObj = replaceParams(sObj, a, childs.get(j).toString());
                    	j++;
                    }
                    result = sObj;
                }break;
            }
        } catch (Exception e){
            return "Error al evaluar la expresión";
        }
        return result;
    }
    
    private String replaceParams(String function, String param, String value) {
    	String ret="", tmp ="";
    	
    	char ch;
    	
    	for (int i = 0; i < function.length(); i++) { 
            ch = function.charAt(i);
            
            if (String.valueOf(ch).equals(" ")) {
            	if (tmp.toLowerCase().equals(param.toLowerCase())) {
            		ret = ret + value;
            	}else 
            	{
            		ret = ret + tmp;
            		
            	}
            	ret = ret + String.valueOf(ch);
            	tmp = "";
            } else {
            	tmp =tmp + String.valueOf(ch);
            	if (i ==function.length()-1) {
            		ret = ret + tmp;
            		tmp ="";
            	}
            }
        }
    	return ret;
    }
    
    private String isLogic (String eq){
    	return (eq.equals("1.0"))? "T":"NIL";
    }
    
    private String isOperator(String op){
        double result = 0;
        try {
            if (childs.size() > 0){
                for (Object a : childs) {
                    try {
                        LispTree lispTree = (LispTree) a;
                        numeros.add(Double.parseDouble(lispTree.evalExpression()));
                    } catch (Exception e){
                        numeros.add(Double.parseDouble(a.toString()));
                    }
                }
            } else {
            	throw new Exception();
            }
            result = calc.operar(op, numeros);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error en el codigo al evaluar";}
        return Double.toString(result);
    }

    private String dat (String t, String n){
        try {
            if (childs.size()==1){
                try {
                    LispTree a = (LispTree) childs.get(0);
                    return  n;
                } catch (Exception e){
                    return  t;
                }
            } else throw new Exception();
        }catch (Exception e){
            return "Error en el cÃ³digo";
        }
    }

    public void insertChild(Object child) {
        childs.add(child);
    }
}
