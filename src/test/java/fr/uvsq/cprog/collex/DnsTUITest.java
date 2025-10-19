package fr.uvsq.cprog.collex;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.*;
import java.util.List;

/**
 * Tests unitaires de la classe {@link DnsTUI}.
 */
public class DnsTUITest {

    /**
     * Teste la commande 'add' dans la TUI.
     * 
     * @throws Exception en cas d'erreur
     */
    @Test
    public void testNextCommandeAdd() throws Exception {
        String input = "add 192.168.0.1 www.uvsq.fr\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        DnsTUI tui = new DnsTUI();
        Commande cmd = tui.nextCommande();

        assertTrue(cmd instanceof AddCommande);
    }

    /**
     * Teste la commande 'ls <domaine>' dans la TUI.
     * 
     * @throws Exception en cas d'erreur
     */
    @Test
    public void testNextCommandeResolveDomain() throws Exception {
        String input = "ls uvsq.fr\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        DnsTUI tui = new DnsTUI();
        Commande cmd = tui.nextCommande();

        assertTrue(cmd instanceof ResolveDomainCommande);
    }

    /**
     * Teste l'affichage d'une liste d'éléments.
     */
    @Test
    public void testAfficheList() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        DnsTUI tui = new DnsTUI();
        List<String> liste = List.of("item1", "item2");
        tui.affiche(liste);

        String output = out.toString();
        assertTrue(output.contains("item1"));
        assertTrue(output.contains("item2"));
    }

    /**
     * Teste l'affichage lorsque le résultat est null.
     */
    @Test
    public void testAfficheNull() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        DnsTUI tui = new DnsTUI();
        tui.affiche(null);

        String output = out.toString();
        assertTrue(output.contains("Aucun résultat"));
    }
}