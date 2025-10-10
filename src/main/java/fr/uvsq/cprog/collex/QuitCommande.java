package fr.uvsq.cprog.collex;

public class QuitCommande implements Commande {
    
    @Override
    public Object execute(Dns dns) {
        System.out.println("Fermeture du programme...");
        System.exit(0);
        return null;
    }
}
