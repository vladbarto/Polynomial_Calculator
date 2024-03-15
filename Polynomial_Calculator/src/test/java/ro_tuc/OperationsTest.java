package ro_tuc;

import Data_Models.Polinom;
import GUI.Model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OperationsTest {
    private Model model;
    public OperationsTest(){
        model = new Model();
        model.setModel("2x^3-4x^2+5x-2", "x^2-x+1");
    }
    @Test
    public void addTest(){
        String r = "-1.0+4.0x-3.0x^2+2.0x^3";
        assertTrue(r.equals(model.Adunare()));
    }
    @Test
    public void subTest(){
        String r = "-3.0+6.0x-5.0x^2+2.0x^3";
        assertTrue(r.equals(model.Scadere()));
    }
    @Test
    public void mulTest(){
        String r = "-2.0+7.0x-11.0x^2+11.0x^3-6.0x^4+2.0x^5";
        assertTrue(r.equals(model.Inmultire()));
    }
    @Test
    public void divTest(){
        String r = "Cât: -2.0+2.0x || Rest: +x";
        assertTrue(r.equals(model.Impartire()));
    }
    @Test
    public void integrateTest(){
        String r = "-2.0x+2.5x^2-1.3333334x^3+0.5x^4";
        assertTrue(r.equals(model.Integrare()));
    }
    @Test
    public void derivativeTest(){
        String r = "+5.0-8.0x+6.0x^2";
        assertTrue(r.equals(model.Derivare()));
    }
}
