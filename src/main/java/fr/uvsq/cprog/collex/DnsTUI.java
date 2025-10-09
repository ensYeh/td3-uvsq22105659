package fr.uvsq.cprog.collex;

import java.util.Scanner;
import java.util.List;

public class DnsTUI {
    
    private final Scanner sc = new Scanner(System.in);

    public Commande nextCommande() {
        System.out.print("> ");
        String ligne = sc.nextLine().trim();
        return null;
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