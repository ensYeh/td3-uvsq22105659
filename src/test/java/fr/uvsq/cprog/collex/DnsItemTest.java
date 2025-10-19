package fr.uvsq.cprog.collex;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test unitaire de la classe {@link DnsItem}.
 */
public class DnsItemTest {

    /**
     * Vérifie la création d'un DnsItem valide.
     */
    @Test
    public void testCreationValide() {
        NomMachine nm = new NomMachine("www", "uvsq.fr");
        AdresseIP ip = new AdresseIP("192.168.0.1");
        DnsItem item = new DnsItem(ip, nm);
        assertEquals("www.uvsq.fr", item.getNomMachine().toString());
    }

    /**
     * Vérifie qu'une création avec AdresseIP nulle lève une exception.
     */
    @Test(expected = NullPointerException.class)
    public void testCreationInvalide_NullIP() {
        NomMachine nm = new NomMachine("www", "uvsq.fr");
        new DnsItem(null, nm);
    }

    /**
     * Vérifie qu'une création avec NomMachine nul lève une exception.
     */
    @Test(expected = NullPointerException.class)
    public void testCreationInvalide_NullNomMachine() {
        AdresseIP ip = new AdresseIP("192.168.0.1");
        new DnsItem(ip, null);
    }

    /**
     * Vérifie les getters {@link DnsItem#getNomMachine()} et
     * {@link DnsItem#getIp()}.
     */
    @Test
    public void testGetters() {
        NomMachine nm = new NomMachine("www", "uvsq.fr");
        AdresseIP ip = new AdresseIP("192.168.0.1");
        DnsItem item = new DnsItem(ip, nm);
        assertEquals(nm, item.getNomMachine());
        assertEquals(ip, item.getIp());
    }

    /**
     * Vérifie les méthodes {@code equals()} et {@code hashCode()}.
     */
    @Test
    public void testEqualsEtHashCode() {
        NomMachine nm1 = new NomMachine("www", "uvsq.fr");
        AdresseIP ip1 = new AdresseIP("192.168.0.1");
        DnsItem item1 = new DnsItem(ip1, nm1);

        NomMachine nm2 = new NomMachine("www", "uvsq.fr");
        AdresseIP ip2 = new AdresseIP("192.168.0.1");
        DnsItem item2 = new DnsItem(ip2, nm2);

        NomMachine nm3 = new NomMachine("poste", "uvsq.fr");
        AdresseIP ip3 = new AdresseIP("192.168.0.2");
        DnsItem item3 = new DnsItem(ip3, nm3);

        assertEquals(item1, item2);
        assertNotEquals(item1, item3);
        assertEquals(item1.hashCode(), item2.hashCode());
        assertNotEquals(item1.hashCode(), item3.hashCode());
    }
}