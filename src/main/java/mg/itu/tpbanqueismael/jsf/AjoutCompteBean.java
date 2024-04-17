/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueismael.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.PositiveOrZero;
import mg.itu.tpbanqueismael.entity.CompteBancaire;
import mg.itu.tpbanqueismael.service.GestionnaireCompte;
import mg.itu.tpbanqueismael.util.Util;

/**
 *
 * @author ASPIRE
 */
@Named(value = "ajoutCompteBean")
@RequestScoped
public class AjoutCompteBean {
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    private String nom;
    @PositiveOrZero
    private int solde;
    
    public String action() {
        CompteBancaire newCb = new CompteBancaire(nom, solde);
        gestionnaireCompte.creerCompte(newCb);
        Util.addFlashInfoMessage("Compte ajouté avec succès.");
        return "listeComptes?faces-redirect=true";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    /**
     * Creates a new instance of AjoutCompteBean
     */
    public AjoutCompteBean() {
    }
    
}
