package fr.uvsq.cprog.collex;

public class ResolveNameCommande implements Commande{
    private final AdresseIP ip;

    public ResolveNameCommande(AdresseIP ip) {
        this.ip = ip;
    }
    
}
