package fr.uvsq.cprog.collex;

public class ResolveNameCommande implements Commande{
    private final AdresseIP ip;

    public ResolveNameCommande(AdresseIP ip) {
        this.ip = ip;
    }

    @Override
    public Object execute(Dns dns) {
        DnsItem item = dns.getItem(ip);
        if (item != null) {
            return item.getNomMachine();
        } else {
            return "Aucun résultat pour cette adresse IP";
        }
    }
    
}
