import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

public class TokensTest {

    @Test
    public void getInstance() {
        String[] token_list = { "+", "-", "*", "/", "quote", "defun", "atom", "list", "equal", "cond", "set", "<", ">", "=", "c"};
        Tokens thisInstance = new Tokens(token_list);
        Tokens test = new Tokens(token_list);
        test.getTokens();
    }

    @Test
    public void addToken() {
        String[] token_list = { "+", "-", "*", "/", "quote", "defun", "atom", "list", "equal", "cond", "set", "<", ">", "=", "c"};
        ArrayList<String> token= new ArrayList<>();
        token.addAll(Arrays.asList(token_list));
        String nuevo = "jungla";
        Tokens test = new Tokens(token_list);
        test.addToken(nuevo);
        boolean esperado = false;
        assertEquals(esperado,token.equals("jungla"));
    }

    @Test
    public void addFunction() {
        String[] token_list = { "+", "-", "*", "/", "quote", "defun", "atom", "list", "equal", "cond", "set", "<", ">", "=", "c"};
        Tokens test = new Tokens(token_list);
    }

    @Test
    public void getTokens() {
        String[] token_list = { "+", "-", "*", "/", "quote", "defun", "atom", "list", "equal", "cond", "set", "<", ">", "=", "c"};
        Tokens test = new Tokens(token_list);
        test.getTokens();
    }

    @Test
    public void getFunciton() {
        String[] token_list = { "+", "-", "*", "/", "quote", "defun", "atom", "list", "equal", "cond", "set", "<", ">", "=", "c"};
        Tokens thisInstance = new Tokens(token_list);

        ArrayList<String> token= new ArrayList<>();
        HashMap<String,Object[]> map;

        //Tokens test = new Tokens();
    }

    @Test
    public void tokenExists() {
        String[] token_list = { "+", "-", "*", "/", "quote", "defun", "atom", "list", "equal", "cond", "set", "<", ">", "=", "c"};
        String tokenn = "+";
        Tokens test = new Tokens(token_list);
        boolean x = test.tokenExists(tokenn);
        assertEquals(true,x);
    }

    @Test
    public void functionExists() {
        String[] token_list = { "+", "-", "*", "/", "quote", "defun", "atom", "list", "equal", "cond", "set", "<", ">", "=", "c"};
        String func = "posicion";
        Tokens test = new Tokens(token_list);
        boolean z = test.functionExists(func);
        assertEquals(true,z);
    }
}