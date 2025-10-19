package fr.uvsq.cprog.collex;

import java.io.IOException;

/**
 * Représente la commande d'ajout d'un élément dans un DNS.
 */
public class AddCommande implements Commande {

    final AdresseIP ip;
    final NomMachine nomMachine;

    /**
     * Constructeur de la commande AddCommande.
     *
     * @param ip         L'adresse IP de l'élément à ajouter.
     * @param nomMachine Le nom de la machine de l'élément à ajouter.
     */
    public AddCommande(AdresseIP ip, NomMachine nomMachine) {
        this.ip = ip;
        this.nomMachine = nomMachine;
    }

    /**
     * Exécute la commande sur le DNS donné.
     *
     * @param dns Le DNS sur lequel exécuter la commande.
     * @return Un message indiquant le succès ou l'erreur.
     */
    @Override
    public Object execute(Dns dns) {
        try {
            dns.addItem(ip, nomMachine);
            return "Ajout effectué";
        } catch (IOException | IllegalArgumentException e) {
            return "Erreur lors de l'ajout : " + e.getMessage();
        }
    }
}