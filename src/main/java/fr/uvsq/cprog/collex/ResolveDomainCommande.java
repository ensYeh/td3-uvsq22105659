package fr.uvsq.cprog.collex;

import java.util.List;
import java.util.Comparator;

public class ResolveDomainCommande implements Commande {
    private final String domaine;
    private final boolean triParAdresse;

    public ResolveDomainCommande(String domaine) {
        this(domaine, false);
    }

    public ResolveDomainCommande(String domaine, boolean triParAdresse) {
        this.domaine = domaine;
        this.triParAdresse = triParAdresse;
    }

    @Override
    public Object execute(Dns dns) {
        List<DnsItem> items = dns.getItems(domaine);
        if (items == null || items.isEmpty()) {
            return "Aucun résultat pour ce domaine";
        }

        if (triParAdresse) {
            items.sort(Comparator.comparing(item -> item.getIp().toString()));
        } else {
            items.sort(Comparator.comparing(item -> item.getNomMachine().toString()));
        }

        return items;
    }
}