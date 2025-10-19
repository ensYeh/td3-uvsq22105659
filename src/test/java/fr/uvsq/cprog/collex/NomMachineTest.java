package fr.uvsq.cprog.collex;

import static org.junit.Assert.*;
import org.junit.Test;

public class NomMachineTest {

    @Test
    public void testCreationValide() {
        NomMachine nm = new NomMachine("www", "uvsq.fr");
        assertEquals("www.uvsq.fr", nm.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreationInvalide_Null() {
        new NomMachine(null, "uvsq.fr");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreationInvalide_Vide() {
        new NomMachine("", "uvsq.fr");
    }

    @Test
    public void testEqualsEtHashCode() {
        NomMachine nm1 = new NomMachine("www", "uvsq.fr");
        NomMachine nm2 = new NomMachine("www", "uvsq.fr");
        NomMachine nm3 = new NomMachine("poste", "uvsq.fr");

        assertEquals(nm1, nm2);
        assertNotEquals(nm1, nm3);
        assertEquals(nm1.hashCode(), nm2.hashCode());
        assertNotEquals(nm1.hashCode(), nm3.hashCode());
    }
    
}
