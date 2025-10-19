package fr.uvsq.cprog.collex;

import static org.junit.Assert.*;
import org.junit.Test;

public class AdresseIPTest {

    @Test
    public void testCreationValide() {
        AdresseIP ip = new AdresseIP("192.168.1.1");
        assertEquals("192.168.1.1", ip.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreationInvalide_Null() {
        new AdresseIP(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreationInvalide_Format() {
        new AdresseIP("999.999.999.999");
    }

    @Test
    public void testToString() {
        AdresseIP ip = new AdresseIP("192.168.1.1");
        assertEquals("192.168.1.1", ip.toString());
    }

    @Test
    public void testEqualsEtHashCode() {
        AdresseIP ip1 = new AdresseIP("192.168.1.1");
        AdresseIP ip2 = new AdresseIP("192.168.1.1");
        AdresseIP ip3 = new AdresseIP("192.168.1.2");

        assertEquals(ip1, ip2);
        assertNotEquals(ip1, ip3);
        assertEquals(ip1.hashCode(), ip2.hashCode());
        assertNotEquals(ip1.hashCode(), ip3.hashCode());
    }

}   
