package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Test;


/**
* Tests unitaires de la classe {@link ResolveIpCommande}.
*/
public class ResolveIpCommandeTest {
  /**
  * Teste la résolution réussie d'un nom de machine vers son adresse IP.
  */
  @Test
  public void testExecuteResolutionReussie() throws IOException {
    Path tempFile = Files.createTempFile("dnsTemp", ".txt");
    tempFile.toFile().deleteOnExit();

    Dns dns = new Dns(tempFile);
    AdresseIP ip = new AdresseIP("192.168.0.1");
    NomMachine nom = new NomMachine("www", "uvsq.fr");
    dns.addItem(ip, nom);

    ResolveIpCommande resolve = new ResolveIpCommande(nom);
    Object result = resolve.execute(dns);

    assertTrue(result instanceof AdresseIP);
    assertEquals("192.168.0.1", result.toString());
  }

  /**
  * Teste la résolution échouée lorsqu'aucune machine correspondante n'existe.
  */
  @Test
  public void testExecuteResolutionEchouee() throws IOException {
    Path tempFile = Files.createTempFile("dnsTemp", ".txt");
    tempFile.toFile().deleteOnExit();

    Dns dns = new Dns(tempFile);
    NomMachine nom = new NomMachine("inconnu", "uvsq.fr");

    ResolveIpCommande resolve = new ResolveIpCommande(nom);
    Object result = resolve.execute(dns);

    assertEquals("Aucun résultat pour cette machine", result);
  }
}