package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Représente un DNS stockant des correspondances entre adresses IP et noms de
 * machines.
 */
public class Dns {

    private final List<DnsItem> items = new ArrayList<>();
    private final Path fichier;

    /**
     * Construit un DNS à partir d'un fichier.
     *
     * @param fichier Le chemin du fichier contenant les enregistrements DNS.
     * @throws IOException Si la lecture du fichier échoue.
     */
    public Dns(Path fichier) throws IOException {
        if (fichier == null) {
            throw new NullPointerException("Le chemin du fichier ne peut pas être nul");
        }
        this.fichier = fichier;
        if (Files.exists(fichier)) {
            List<String> lignes = Files.readAllLines(fichier);
            for (String ligne : lignes) {
                ligne = ligne.trim();
                if (ligne.isEmpty()) {
                    continue;
                }

                String[] parties = ligne.split("\\s+");
                if (parties.length != 2) {
                    continue;
                }

                AdresseIP ip = new AdresseIP(parties[1].trim());

                String[] nomParties = parties[0].trim().split("\\.", 2);
                NomMachine nomMachine = new NomMachine(nomParties[0].trim(), nomParties[1].trim());

                items.add(new DnsItem(ip, nomMachine));
            }
        }
    }

    /**
     * Retourne l'enregistrement correspondant à l'adresse IP donnée, ou null si
     * inexistant.
     */
    public DnsItem getItem(AdresseIP ip) {
        for (DnsItem item : items) {
            if (item.getIp().equals(ip)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Retourne l'enregistrement correspondant au nom de machine donné, ou null si
     * inexistant.
     */
    public DnsItem getItem(NomMachine nomMachine) {
        for (DnsItem item : items) {
            if (item.getNomMachine().equals(nomMachine)) {
                return item;
            }
        }
        return null;
    }

    /** Retourne tous les enregistrements correspondant au domaine donné. */
    public List<DnsItem> getItems(String domaine) {
        List<DnsItem> resultat = new ArrayList<>();
        for (DnsItem item : items) {
            if (item.getNomMachine().getDomaine().equals(domaine)) {
                resultat.add(item);
            }
        }
        return resultat;
    }

    /**
     * Ajoute un nouvel enregistrement au DNS et met à jour le fichier.
     *
     * @param ip         L'adresse IP à ajouter.
     * @param nomMachine Le nom de machine à ajouter.
     * @throws IllegalArgumentException Si l'adresse IP ou le nom de machine existe
     *                                  déjà.
     * @throws IOException              Si l'écriture dans le fichier échoue.
     */
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
        List<String> lignes = items.stream()
                .map(DnsItem::toString)
                .collect(Collectors.toList());
        Files.write(fichier, lignes);
    }
}