package fr.uvsq.cprog.collex;

public class AddCommande implements Commande{
    private final AdresseIP ip;
    private final NomMachine  nomMachine;

    public AddCommande(AdresseIP ip, NomMachine nomMachine) {
        this.ip = ip;
        this.nomMachine = nomMachine;
    }
    

    
}
