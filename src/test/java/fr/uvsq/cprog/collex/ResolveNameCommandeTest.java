package fr.uvsq.cprog.collex;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResolveNameCommandeTest {

    @Test
    public void testExecuteResolutionReussie() throws IOException {
        Path tempFile = Files.createTempFile("dnsTemp", ".txt");
        tempFile.toFile().deleteOnExit();

        Dns dns = new Dns(tempFile);
        AdresseIP ip = new AdresseIP("192.168.0.1");
        NomMachine nom = new NomMachine("www", "uvsq.fr");
        dns.addItem(ip, nom);

        ResolveNameCommande resolve = new ResolveNameCommande(ip);
        Object result = resolve.execute(dns);

        assertTrue(result instanceof NomMachine);
        assertEquals("www.uvsq.fr", result.toString());
    }

    @Test
    public void testExecuteResolutionEchouee() throws IOException {
        Path tempFile = Files.createTempFile("dnsTemp", ".txt");
        tempFile.toFile().deleteOnExit();

        Dns dns = new Dns(tempFile);
        AdresseIP ip = new AdresseIP("10.0.0.1");

        ResolveNameCommande resolve = new ResolveNameCommande(ip);
        Object result = resolve.execute(dns);

        assertEquals("Aucun résultat pour cette adresse IP", result);
    }
}