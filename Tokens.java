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

    private Tokens(String[] token_list) {
        token.addAll(Arrays.asList(token_list));
        map = new HashMap<>();
    }

    public void addToken(String newToken){
        token.add(newToken);
    }
    
    public void addFunction (String token, Object[] func){
    	//this.token.add(token);
        map.put(token,func);
        //System.out.println(this.token);
    }

    public ArrayList<String> getTokens(){
        return token;
    }
    
    public Object[] getFunciton (String token){
        return map.get(token);
    }

    public Boolean tokenExists(String token){
        if (token.contains(token)) {
        	return true;
        }else {
        	return false;
        }
    }

    public Boolean functionExists(String token){
        if (map.containsKey(token)) {
        	return true;
        }else {
        	return false;
        }
    }

}
