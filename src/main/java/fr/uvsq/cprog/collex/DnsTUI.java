package fr.uvsq.cprog.collex;

import java.util.Scanner;
import java.util.List;

public class DnsTUI {
    
    private final Scanner sc = new Scanner(System.in);

    public Commande nextCommande() {
        System.out.print("> ");
        String ligne = sc.nextLine().trim();
        
        if (ligne.isEmpty()) return null;

        String[] parties = ligne.split("\\s+");

        try {
            if (parties[0].equalsIgnoreCase("add") && parties.length == 3) {
                AdresseIP ip = new AdresseIP(parties[1]);
                String[] nd = parties[2].split("\\.", 2);
                NomMachine nom = new NomMachine(nd[0], nd[1]);
                return new AddCommande(ip, nom);
            }

            if (parties[0].equalsIgnoreCase("ls")) {
                boolean triParAdresse = (parties.length == 3 && parties[1].equals("-a"));
                String domaine = triParAdresse ? parties[2] : parties[1];
                return new ResolveDomainCommande(domaine, triParAdresse);
            }

            if (parties[0].equalsIgnoreCase("quit")) {
                return new QuitCommande();
            }

            if (parties[0].matches("((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")) {
                AdresseIP ip = new AdresseIP(parties[0]);
                return new ResolveNameCommande(ip);
            }

            String[] nd = parties[0].split("\\.", 2);
            NomMachine nm = new NomMachine(nd[0], nd[1]);
            return new ResolveIpCommande(nm);
        }  catch (Exception e) {
            System.out.println("Commande invalide : " + e.getMessage());
            return null;
        }
    }

    public void affiche (Object resultat) {
        if (resultat == null) {
            System.out.println("Aucun résultat");
        } else if (resultat instanceof List<?> liste) {
            for (Object item : liste) {
                System.out.println(item);
            }
        } else {
            System.out.println(resultat);
        }
    }
}