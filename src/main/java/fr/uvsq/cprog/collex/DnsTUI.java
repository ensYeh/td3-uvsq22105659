package fr.uvsq.cprog.collex;

import java.util.Scanner;
import java.util.List;

/** Interface utilisateur textuelle pour interagir avec le DNS. */
public class DnsTUI {

    private final Scanner sc = new Scanner(System.in);

    /**
     * Lit la prochaine commande depuis l'utilisateur.
     *
     * @return une instance de Commande ou null si la ligne est vide ou invalide
     */
    public Commande nextCommande() {
        System.out.print("> ");
        String ligne = sc.nextLine().trim();

        if (ligne.isEmpty()) {
            return null;
        }

        String[] parties = ligne.split("\\s+");

        try {
            switch (parties[0].toLowerCase()) {
                case "add" -> {
                    if (parties.length != 3)
                        return null;
                    AdresseIP ip = new AdresseIP(parties[1]);
                    String[] nd = parties[2].split("\\.", 2);
                    NomMachine nom = new NomMachine(nd[0], nd[1]);
                    return new AddCommande(ip, nom);
                }
                case "ls" -> {
                    boolean triParAdresse = (parties.length == 3 && "-a".equals(parties[1]));
                    String domaine = triParAdresse ? parties[2] : parties[1];
                    return new ResolveDomainCommande(domaine, triParAdresse);
                }
                case "quit" -> {
                    return new QuitCommande();
                }
                default -> {
                    if (parties[0].matches(
                            "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")) {
                        AdresseIP ip = new AdresseIP(parties[0]);
                        return new ResolveNameCommande(ip);
                    } else {
                        String[] nd = parties[0].split("\\.", 2);
                        NomMachine nm = new NomMachine(nd[0], nd[1]);
                        return new ResolveIpCommande(nm);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Commande invalide : " + e.getMessage());
            return null;
        }
    }

    /**
     * Affiche le résultat d'une commande.
     *
     * @param resultat le résultat à afficher
     */
    public void affiche(Object resultat) {
        if (resultat == null) {
            System.out.println("Aucun résultat");
        } else if (resultat instanceof List<?> liste) {
            liste.forEach(System.out::println);
        } else {
            System.out.println(resultat);
        }
    }
}