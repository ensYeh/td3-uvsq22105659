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

    public DnsItem getItem(AdresseIP ip) {
        for (DnsItem item : items) {
            if (item.getIp().equals(ip)) {
                return item;
            }
        }
        return null;
    }

    public DnsItem getItem(NomMachine nomMachine) {
        for (DnsItem item : items) {
            if (item.getNomMachine().equals(nomMachine)){
                return item;
            }
        }
        return null;
    }

    public List<DnsItem> getItems(String domaine) {
        List<DnsItem> resultat = new ArrayList<>();
        for (DnsItem item : items) {
            if (item.getNomMachine().getDomaine().equals(domaine)) {
                resultat.add(item);
            }
        }
        return resultat;
    }

    public void addItem(AdresseIP ip, NomMachine nomMachine) throws IllegalArgumentException, IOException {
        if (ip == null || nomMachine == null) {
            throw new NullPointerException("Adresse IP ou Nom de machine ne peut pas être nul");
        }
        if (getItem(ip) != null) {
            throw new IllegalArgumentException("Adresse IP déjà existante !");
        }
        if (getItem(nomMachine) != null) {
            throw new IllegalArgumentException("Nom de machine déjà existant !");
        }
        DnsItem newItem = new DnsItem(ip, nomMachine);
        items.add(newItem);
        List<String> lignes = items.stream().map(DnsItem::toString).collect(Collectors.toList());
        Files.write(fichier, lignes);
    }

}
