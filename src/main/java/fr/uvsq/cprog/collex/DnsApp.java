package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Path;

public class DnsApp {

    private final Dns dns;
    private final DnsTUI tui;

    public DnsApp(Path fichier) throws IOException {
        this.dns = new Dns(fichier);
        this.tui = new DnsTUI();
    }
    
    public void run() {
        while (true) {
            Commande cmd = tui.nextCommande();
            if (cmd == null) continue;

            Object resultat = cmd.execute(dns);
            tui.affiche(resultat);
        }
    }
}
