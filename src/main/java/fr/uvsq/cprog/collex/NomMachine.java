package fr.uvsq.cprog.collex;

import java.util.Objects;

public class NomMachine {
    private final String nom;
    private final String domaine;

    public NomMachine(String nom, String domaine) {
        if (nom == null || nom.isEmpty()) {
            throw new NullPointerException("Nom de machine ne peut pas être nul ou vide");
        }
        if (domaine == null || domaine.isEmpty()) {
            throw new NullPointerException("Domaine ne peut pas être nul ou vide");
        }
        this.nom = nom;
        this.domaine = domaine;
    }

    public String getNom() {
        return nom;
    }

    public String getDomaine() {
        return domaine;
    }

    @Override
    public String toString() {
        return nom + "." + domaine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NomMachine nomMachine = (NomMachine) o;
        return this.nom.equals(nomMachine.nom) && this.domaine.equals(nomMachine.domaine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, domaine);
    }
}
