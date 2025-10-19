package fr.uvsq.cprog.collex;

import java.util.List;
import java.util.Comparator;

/** Commande pour résoudre un domaine et afficher les machines associées. */
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
            // Tri par adresse IP
            items.sort((a, b) -> {
                String[] pa = a.getIp().toString().split("\\.");
                String[] pb = b.getIp().toString().split("\\.");
                for (int i = 0; i < 4; i++) {
                    int ia = Integer.parseInt(pa[i]);
                    int ib = Integer.parseInt(pb[i]);
                    if (ia != ib) {
                        return Integer.compare(ia, ib);
                    }
                }
                return 0;
            });
        } else {
            // Tri par nom de machine
            items.sort(Comparator.comparing(item -> item.getNomMachine().toString()));
        }

        return items;
    }
}