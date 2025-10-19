package fr.uvsq.cprog.collex;

import java.util.Objects;

/** Représente le nom complet d'une machine, incluant son nom et son domaine. */
public class NomMachine {

    private final String nom;
    private final String domaine;

    /**
     * Crée un nouveau NomMachine.
     *
     * @param nom     le nom de la machine, non nul et non vide
     * @param domaine le domaine de la machine, non nul et non vide
     */
    public NomMachine(String nom, String domaine) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Nom de machine ne peut pas être nul ou vide");
        }
        if (domaine == null || domaine.isEmpty()) {
            throw new IllegalArgumentException("Domaine ne peut pas être nul ou vide");
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
        if (this == o)
            return true;
        if (!(o instanceof NomMachine))
            return false;
        NomMachine that = (NomMachine) o;
        return nom.equals(that.nom) && domaine.equals(that.domaine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, domaine);
    }
}