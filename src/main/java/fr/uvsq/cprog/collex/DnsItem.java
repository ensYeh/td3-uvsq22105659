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

    public AdresseIP getIp()  {
        return ip;
    }

    public NomMachine getNomMachine() {
        return nomMachine;
    }

    @Override
    public String toString() {
        return ip + " " + nomMachine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DnsItem dnsItem = (DnsItem) o;
        return this.ip.equals(dnsItem.ip) && this.nomMachine.equals(dnsItem.nomMachine);
    }
}
