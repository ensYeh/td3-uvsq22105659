package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.Test;


/**
* Tests unitaires de la classe {@link ResolveDomainCommande}.
*/
public class ResolveDomainCommandeTest {

  /**
  * Teste l'exécution d'une commande avec des résultats présents.
  */
  @Test
  public void testExecuteAvecResultat() throws IOException {
    Path tempFile = Files.createTempFile("dnsTemp", ".txt");
    tempFile.toFile().deleteOnExit();

    Dns dns = new Dns(tempFile);
    AdresseIP ip1 = new AdresseIP("192.168.0.1");
    AdresseIP ip2 = new AdresseIP("192.168.0.2");
    NomMachine nom1 = new NomMachine("www", "uvsq.fr");
    NomMachine nom2 = new NomMachine("mail", "uvsq.fr");

    dns.addItem(ip1, nom1);
    dns.addItem(ip2, nom2);

    ResolveDomainCommande cmd = new ResolveDomainCommande("uvsq.fr", false);
    Object result = cmd.execute(dns);

    assertTrue(result instanceof List<?>);
    List<?> items = (List<?>) result;
    assertEquals(2, items.size());
  }

  /**
  * Teste l'exécution d'une commande pour un domaine sans résultat.
  */
  @Test
  public void testExecuteSansResultat() throws IOException {
    Path tempFile = Files.createTempFile("dnsTemp", ".txt");
    tempFile.toFile().deleteOnExit();

    Dns dns = new Dns(tempFile);
    ResolveDomainCommande cmd = new ResolveDomainCommande("inexistant.fr", false);
    Object result = cmd.execute(dns);

    if (result instanceof List<?>) {
      List<?> items = (List<?>) result;
      assertTrue(items.isEmpty());
    } else if (result instanceof String) {
      assertTrue(result.toString().toLowerCase().contains("aucun résultat"));
    } else {
      fail("Résultat inattendu : " + result);
    }
  }

  /**
  * Teste l'exécution avec tri des résultats par adresse IP.
  */
  @Test
  public void testTriParAdresse() throws IOException {
    Path tempFile = Files.createTempFile("dnsTemp", ".txt");
    tempFile.toFile().deleteOnExit();

    Dns dns = new Dns(tempFile);
    AdresseIP ip1 = new AdresseIP("192.168.0.2");
    AdresseIP ip2 = new AdresseIP("192.168.0.1");
    NomMachine nom1 = new NomMachine("a", "uvsq.fr");
    NomMachine nom2 = new NomMachine("b", "uvsq.fr");

    dns.addItem(ip1, nom1);
    dns.addItem(ip2, nom2);

    ResolveDomainCommande cmd = new ResolveDomainCommande("uvsq.fr", true);
    Object result = cmd.execute(dns);

    assertTrue(result instanceof List<?>);
    List<DnsItem> items = (List<DnsItem>) result;
    assertEquals("192.168.0.1", items.get(0).getIp().toString());
  }
}