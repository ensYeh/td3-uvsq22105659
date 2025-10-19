package fr.uvsq.cprog.collex;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Test unitaire de la classe {@link AddCommande}.
 */
public class AddCommandeTest {

    /**
     * Vérifie que le constructeur initialise correctement les attributs.
     */
    @Test
    public void testConstructeurEtGetters() {
        AdresseIP ip = new AdresseIP("192.168.0.1");
        NomMachine nom = new NomMachine("www", "uvsq.fr");
        AddCommande add = new AddCommande(ip, nom);

        assertEquals(ip, add.ip);
        assertEquals(nom, add.nomMachine);
    }

    /**
     * Vérifie que l'ajout via execute() fonctionne correctement.
     *
     * @throws IOException si une erreur de fichier survient
     */
    @Test
    public void testExecuteAjoutReussi() throws IOException {
        Path tempFile = Files.createTempFile("dns", ".txt");
        tempFile.toFile().deleteOnExit();

        Dns dns = new Dns(tempFile);
        AdresseIP ip = new AdresseIP("192.168.0.1");
        NomMachine nom = new NomMachine("www", "uvsq.fr");

        AddCommande add = new AddCommande(ip, nom);

        Object result = add.execute(dns);
        assertEquals("Ajout effectué", result);
        assertNotNull(dns.getItem(ip));
        assertNotNull(dns.getItem(nom));
    }

    /**
     * Vérifie que l'ajout d'un doublon retourne une erreur.
     *
     * @throws IOException si une erreur de fichier survient
     */
    @Test
    public void testExecuteAjoutDoublon() throws IOException {
        Path tempFile = Files.createTempFile("dns", ".txt");
        tempFile.toFile().deleteOnExit();

        Dns dns = new Dns(tempFile);
        AdresseIP ip = new AdresseIP("192.168.0.1");
        NomMachine nom = new NomMachine("www", "uvsq.fr");

        dns.addItem(ip, nom);

        AddCommande add = new AddCommande(ip, nom);

        Object result = add.execute(dns);
        assertTrue(result.toString().startsWith("Erreur lors de l'ajout"));
    }
}