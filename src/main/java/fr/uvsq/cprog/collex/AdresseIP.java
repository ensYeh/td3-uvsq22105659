package fr.uvsq.cprog.collex;

import java.util.Objects;

/**
 * Représente une adresse IP valide.
 */
public class AdresseIP {

    private final String ip;

    /**
     * Constructeur d'une adresse IP.
     *
     * @param ip La chaîne représentant l'adresse IP.
     * @throws IllegalArgumentException si l'adresse IP est nulle ou invalide.
     */
    public AdresseIP(String ip) {
        if (ip == null) {
            throw new IllegalArgumentException("Adresse IP ne peut pas être nulle");
        }
        ip = ip.trim();
        if (!ip.matches("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")) {
            throw new IllegalArgumentException("Adresse IP invalide : " + ip);
        }
        this.ip = ip;
    }

    /**
     * Retourne l'adresse IP sous forme de chaîne.
     *
     * @return La chaîne de l'adresse IP.
     */
    public String getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdresseIP adresseIP = (AdresseIP) o;
        return ip.equals(adresseIP.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}