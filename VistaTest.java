import org.junit.Test;

import static org.junit.Assert.*;

public class VistaTest {

    @Test
    public void inicio() {
        Vista test = new Vista();
        test.inicio();
    }

    @Test
    public void salir() {
        Vista test = new Vista();
        test.salir();
    }

    @Test
    public void mensaje() {
        String t ="Hola";
        Vista test = new Vista();
        test.mensaje(t);
    }

    @Test
    public void operacion() {
        String t = "hola";
        Vista test = new Vista();
        test.operacion(t);
    }
}