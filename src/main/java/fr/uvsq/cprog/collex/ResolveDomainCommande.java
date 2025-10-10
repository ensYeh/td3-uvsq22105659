package fr.uvsq.cprog.collex;

import java.util.List;

public class ResolveDomainCommande implements Commande {
    private final String domaine;

    public ResolveDomainCommande(String domaine) {
        this.domaine = domaine;
    }

    @Override
    public Object execute(Dns dns) {
        List<DnsItem> items = dns.getItems(domaine);
        if (items == null || items.isEmpty()) {
            return "Aucun résultat pour ce domaine";
        } else {
            return items;
        }
    }
}