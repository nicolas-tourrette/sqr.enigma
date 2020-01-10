package enigma;

import static org.junit.Assert.*;

import org.junit.Test;

public class RotorTest {

    @Test
    public void TestAdvance(){
        Rotor r = new Rotor();
        r = r.rotorFactory("B D F H J L C P R T X V Z N Y E I W G A K M U S Q O", "V");
        r.setPosition(1);
        int start = r.getPosition();
        r.advance();
        assertEquals(2, r.getPosition());
    }
    
    @Test
    public void TestAdvanceLimite(){
        Rotor r = new Rotor();
        r = r.rotorFactory("B D F H J L C P R T X V Z N Y E I W G A K M U S Q O", "V");
        r.setPosition(25);
        int start = r.getPosition();
        r.advance();
        assertEquals(0, r.getPosition());
    }
    
    @Test
    public void TestConvertForward(){
        Rotor r = new Rotor();
        r = r.rotorFactory("B D F H J L C P R T X V Z N Y E I W G A K M U S Q O", "V");
        int p = r.convertForward(15);
        System.out.println(r.toLetter(p));
        assertEquals('E',r.toLetter(p));
    }

}
