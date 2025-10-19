package fr.uvsq.cprog.collex;

/** Commande pour résoudre le nom de machine à partir d'une adresse IP. */
public class ResolveNameCommande implements Commande {
    private final AdresseIP ip;

    public ResolveNameCommande(AdresseIP ip) {
        if (ip == null) {
            throw new IllegalArgumentException("Adresse IP ne peut pas être nulle");
        }
        this.ip = ip;
    }

    @Override
    public Object execute(Dns dns) {
        DnsItem item = dns.getItem(ip);
        if (item != null) {
            return item.getNomMachine();
        }
        return "Aucun résultat pour cette adresse IP";
    }
}