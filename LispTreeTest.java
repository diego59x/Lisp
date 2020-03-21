import org.junit.Test;

import static org.junit.Assert.*;

public class LispTreeTest {

    @Test
    public void evalExpression() {
        String x = "+";
        LispTree test = new LispTree(x);
        String result = test.evalExpression();
        System.out.println(result);
        assertEquals("Error en el codigo al evaluar",result);
    }

    @Test
    public void insertChild() {
        String x = "sda";
        LispTree test = new LispTree(x);
        test.insertChild(x);
    }
}