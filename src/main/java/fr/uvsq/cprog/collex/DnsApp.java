package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Application console pour gérer un DNS.
 */
public class DnsApp {

    private final Dns dns;
    private final DnsTUI tui;

    public DnsApp(Path fichier) throws IOException {
        if (fichier == null) {
            throw new NullPointerException("Le chemin du fichier ne peut pas être nul");
        }
        this.dns = new Dns(fichier);
        this.tui = new DnsTUI();
    }

    /** Boucle principale de l'application. */
    public void run() {
        while (true) {
            Commande cmd = tui.nextCommande();
            if (cmd == null) {
                continue;
            }
            Object resultat = cmd.execute(dns);
            tui.affiche(resultat);
        }
    }

    /** Point d'entrée de l'application. */
    public static void main(String[] args) {
        try {
            Path fichier = Path.of("dns.txt");
            DnsApp app = new DnsApp(fichier);
            app.run();
        } catch (IOException e) {
            System.err.println("Erreur lors de l'initialisation du DNS : " + e.getMessage());
        }
    }
}