package fr.uvsq.cprog.collex;

/** Commande pour résoudre l'adresse IP d'une machine donnée. */
public class ResolveIpCommande implements Commande {
    private final NomMachine nomMachine;

    public ResolveIpCommande(NomMachine nomMachine) {
        if (nomMachine == null) {
            throw new IllegalArgumentException("Nom de machine ne peut pas être nul");
        }
        this.nomMachine = nomMachine;
    }

    @Override
    public Object execute(Dns dns) {
        DnsItem item = dns.getItem(nomMachine);
        if (item != null) {
            return item.getIp();
        }
        return "Aucun résultat pour cette machine";
    }
}