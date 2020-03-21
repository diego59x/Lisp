import org.jboss.arquillian.junit.container.JUnitTestRunner;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CalculadoraTest {

    @Test
    public void operar() throws Exception {
        String operador = "+";
        ArrayList<Double> num = new ArrayList<Double>();
        num.add((double) 2);
        num.add((double) 4);
        Calculadora test = new Calculadora();
        test.operar(operador,num);
        fail("Se prueba la funcion operar");
    }


    @Test
    public void sumar() {
        ArrayList<Double> num = new ArrayList<Double>();
        num.add((double) 2);
        num.add((double) 4);
        Calculadora test = new Calculadora();
        int result = (int) test.sumar(num);
        int esperado = 6;
        assertEquals(esperado,result);
    }

    @Test
    public void restar() {
        ArrayList<Double> num = new ArrayList<Double>();
        num.add((double) 2);
        num.add((double) 4);
        Calculadora test = new Calculadora();
        int result = (int) test.restar(num);
        int esperado = -2;
        assertEquals(esperado,result);
    }

    @Test
    public void multiplicar() {
        ArrayList<Double> num = new ArrayList<Double>();
        num.add((double) 2);
        num.add((double) 4);
        Calculadora test = new Calculadora();
        int result = (int) test.multiplicar(num);
        int esperado = 8;
        assertEquals(esperado,result);
    }

    @Test
    public void dividir() {
        ArrayList<Double> num = new ArrayList<Double>();
        num.add((double) 4);
        num.add((double) 2);
        Calculadora test = new Calculadora();
        int result = (int) test.dividir(num);
        int esperado = 2;
        assertEquals(esperado,result);
    }
}