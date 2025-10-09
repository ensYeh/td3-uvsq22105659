package fr.uvsq.cprog.collex;

public class DnsItem {
    private final AdresseIP  ip;
    private final NomMachine nomMachine;

    public DnsItem(AdresseIP ip, NomMachine nomMachine) {
        if (ip == null) {
            throw new NullPointerException("Adresse IP ne peut pas être nulle");
        }
        if (nomMachine == null) {
            throw new NullPointerException("Nom de machine ne peut pas être nulle");
        }
        this.ip = ip;
        this.nomMachine = nomMachine;
    }
}
