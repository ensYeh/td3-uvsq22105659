package fr.uvsq.cprog.collex;

/**
 * Représente une commande pouvant être exécutée sur un DNS.
 */
public interface Commande {

    /**
     * Exécute la commande sur le DNS donné.
     *
     * @param dns Le DNS sur lequel exécuter la commande.
     * @return Le résultat de l'exécution (peut être un message ou un objet).
     */
    Object execute(Dns dns);
}