package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.*;
import java.util.stream.Collectors;

public class Dns {

    private final List<DnsItem> items = new ArrayList<>();
    private final Path fichier;

    public Dns (Path fichier)  throws IOException {
         if (fichier == null) {
            throw new NullPointerException("Le chemin du fichier ne peut pas être nul");
        }
        this.fichier = fichier;
        if (Files.exists(fichier)) {
            List<String> lignes = Files.readAllLines(fichier);
            for (String ligne : lignes) {
                String[] parties = ligne.trim().split("\\s+");
                if (parties.length != 2) continue;
                AdresseIP ip = new AdresseIP(parties[1]);
                String[] nomParties = parties[0].split("\\.", 2);
                NomMachine nomMachine = new NomMachine(nomParties[0],nomParties[1]);
                items.add(new DnsItem(ip, nomMachine));
            }
        }
    }
}
