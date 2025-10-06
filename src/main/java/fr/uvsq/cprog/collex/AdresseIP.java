package fr.uvsq.cprog.collex;

import java.util.Objects;

public class AdresseIP {
    private  final String ip;

    public AdresseIP(String ip) {
        if (ip == null) {
            throw new IllegalArgumentException("Adresse IP ne peut pas être nulle");
        }
        if (!ip.matches("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")) {
            throw new IllegalArgumentException("Adresse IP invalide : " + ip);
        } 
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return ip;
    }


}
