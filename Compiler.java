import java.util.ArrayList;

public class Compiler  {

    private String compilado;
    private String evalToken;
    private Tokens tokens;
    private ArrayList<String> tokenList;
    private ArrayList<String> params;
    private int cont = 0;
    private int cont2 = 0;
        
    private LispTree lispTree = new LispTree(null);

    public Compiler() { 
      tokens = Tokens.getInstance();
    }
    
    
    protected void compilar(String lispCode){
    	tokenList = tokens.getTokens();
        int pa=0;
        for (int i=0; i< lispCode.length(); i++) {
            if (lispCode.substring(i,i+1).equals("(")) pa++;
        }

        int pc = 0;
        for (int i=0; i< lispCode.length(); i++) {
            if (lispCode.substring(i,i+1).equals("(")) pc++;
        }
        
        if (lispCode.startsWith("(") && lispCode.endsWith(")") && (pa == pc)){
            compilado = (String) insert(lispCode, null, 1);
            if (!evalToken.equals("Error")) {
            	compilado= lispTree.evalExpression();
            }
        } else compilado= "Error al compilar el código";
        
        System.out.println(compilado);
    }
    
    private Object insert (String lineCode, LispTree root, int op){
        try {
            //Variable nueva, nombre de la funcion en caso de que exista una
            int i = 0;
            boolean next = false, procesar = false, funParams = true;
            LispTree nLispTree = new LispTree(null);
            
            evalToken = "";
            String sToken="";
            
            while (i<lineCode.length()){
                
            	if (lineCode.substring(i,i+1).equals("(")){
                    
            		while (lineCode.substring(i+1,i+2).equals(" ")) {
                        i++;
                    }
            		
                    if (lineCode.substring(i+1,i+2).equals("(")) {
                    	throw new Exception();
                    }
                    
                    if (isNum(lineCode.substring(i+1, i+2))) {
                    	throw new Exception();
                    }else {
                        //Verificamos aca si es variable o si es un caractér sin sentido
                        int i_validacion = i+1;
                        String reserved_key = getToken(lineCode,i_validacion);
                        if (!tokenList.contains(reserved_key)) {
                        	throw new Exception();
                        }
                    }
                    
                    procesar = true;
                }

                if (procesar){
                    if (evalToken.equals("")) {
                        evalToken = getToken(lineCode,i+1);
                        i+=evalToken.length();
                        next =true;
                        
                        if (!tokens.tokenExists(evalToken)) {
                        	tokens.addToken(evalToken);
                        }
                        nLispTree = new LispTree(evalToken);
                    }
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
                            if (evalToken.equals("defun")){
                                Object[] cosas = new Object[3];
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
                                cosas[0] = contp;
                                cosas[1]= params;
                                cosas[2]=funcion;
                                if (!tokens.functionExists(sToken)) {
                                	tokens.addFunction(sToken,cosas);
                                }
                            }
                            if (tokens.functionExists(evalToken)){
                                boolean seg = false;
                                Object[] func = tokens.getFunciton(evalToken);
                                while (lineCode.substring(i,i+1).equals(" ")) {
                                	i++;
                                }
                                //if (lineCode.substring(i,i+1).equals(")")&& cont2==0) throw new  Exception();
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
        return "todo bien";
    }

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

    private String getToken (String lispCode, int contador){
        String t = "";
        while (!lispCode.substring(contador,contador+1).equals(" ")&& !lispCode.substring(contador,contador+1).equals(")")){
            t += lispCode.substring(contador,contador+1);
            contador++;
        }
        t = t.toLowerCase();
        return t;
    }

    public boolean isNum(String token) {
        try {
            double n = Integer.parseInt(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private int coasntarespecial(String string, String anything, String something, int start){
        int contador = 0;
        boolean bandera = true;
        for (int i=start; i< string.length() && bandera;  i++) {
            if (string.substring(i,i+1).equals(anything)) contador++;
            if (string.substring(i,i+1).equals(something)) bandera = false;
        }
        return contador;
    }
}