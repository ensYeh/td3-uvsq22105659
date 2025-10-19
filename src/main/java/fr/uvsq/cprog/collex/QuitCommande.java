package fr.uvsq.cprog.collex;

/** Commande pour quitter l'application DNS. */
public class QuitCommande implements Commande {

    @Override
    public Object execute(Dns dns) {
        System.out.println("Fermeture du programme...");
        // Arrêt immédiat de l'application
        System.exit(0);
        return null; // jamais atteint
    }
}