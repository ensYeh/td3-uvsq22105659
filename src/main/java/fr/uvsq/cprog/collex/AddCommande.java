package fr.uvsq.cprog.collex;

import java.io.IOException;

public class AddCommande implements Commande{
    private final AdresseIP ip;
    private final NomMachine  nomMachine;

    public AddCommande(AdresseIP ip, NomMachine nomMachine) {
        this.ip = ip;
        this.nomMachine = nomMachine;
    }
    

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
