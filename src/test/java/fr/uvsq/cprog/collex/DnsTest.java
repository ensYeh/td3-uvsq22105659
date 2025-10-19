package fr.uvsq.cprog.collex;

import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class DnsTest {

    private Path fichierTemp;

    @Before
    public void setUp() throws IOException {
        fichierTemp = Files.createTempFile("dnstemp", ".txt");
    }

    @After
    public void tearDown() throws IOException {
        Files.deleteIfExists(fichierTemp);
    }

    @Test
    public void testCreationVide() throws IOException {
        Dns dns = new Dns(fichierTemp);
        assertNotNull(dns);
        assertTrue(dns.getItems("uvsq.fr").isEmpty());
    }

    @Test
    public void testAddItemEtGetItem() throws IOException {
        Dns dns = new Dns(fichierTemp);

        AdresseIP ip = new AdresseIP("192.168.0.1");
        NomMachine nom = new NomMachine("www", "uvsq.fr");

        dns.addItem(ip, nom);

        DnsItem itemParIP = dns.getItem(ip);
        DnsItem itemParNom = dns.getItem(nom);

        assertNotNull(itemParIP);
        assertNotNull(itemParNom);
        assertEquals("www.uvsq.fr 192.168.0.1", itemParIP.toString());
        assertEquals(itemParIP, itemParNom);
    }

    @Test(expected = NullPointerException.class)
    public void testAddItem_Null() throws IOException {
        Dns dns = new Dns(fichierTemp);
        dns.addItem(null, new NomMachine("www", "uvsq.fr"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItem_DuplicateIP() throws IOException {
        Dns dns = new Dns(fichierTemp);

        AdresseIP ip = new AdresseIP("192.168.0.1");
        NomMachine nom1 = new NomMachine("www", "uvsq.fr");
        NomMachine nom2 = new NomMachine("mail", "uvsq.fr");

        dns.addItem(ip, nom1);
        dns.addItem(ip, nom2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItem_DuplicateNom() throws IOException {
        Dns dns = new Dns(fichierTemp);

        AdresseIP ip1 = new AdresseIP("192.168.0.1");
        AdresseIP ip2 = new AdresseIP("192.168.0.2");
        NomMachine nom = new NomMachine("www", "uvsq.fr");

        dns.addItem(ip1, nom);
        dns.addItem(ip2, nom);
    }

    @Test
    public void testGetItemsParDomaine() throws IOException {
        Dns dns = new Dns(fichierTemp);

        dns.addItem(new AdresseIP("192.168.0.1"), new NomMachine("www", "uvsq.fr"));
        dns.addItem(new AdresseIP("192.168.0.2"), new NomMachine("mail", "uvsq.fr"));
        dns.addItem(new AdresseIP("10.0.0.1"), new NomMachine("srv", "other.fr"));

        List<DnsItem> itemsUVSQ = dns.getItems("uvsq.fr");
        assertEquals(2, itemsUVSQ.size());

        List<DnsItem> itemsOther = dns.getItems("other.fr");
        assertEquals(1, itemsOther.size());
    }
}