package fr.uvsq.cprog.collex;

public class ResolveIpCommande implements Commande{
    private final NomMachine nomMachine;

    public ResolveIpCommande(NomMachine nomMachine) {
        this.nomMachine = nomMachine;
    }

    @Override
    public Object execute(Dns dns) {
        DnsItem item = dns.getItem(nomMachine);
        if (item != null) {
            return item.getIp()
        } else {
            return "Aucun résultat pour cette machine";
        }
    }
    
}
