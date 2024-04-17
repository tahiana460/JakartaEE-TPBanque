/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueismael.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import mg.itu.tpbanqueismael.entity.CompteBancaire;
import mg.itu.tpbanqueismael.service.GestionnaireCompte;
import mg.itu.tpbanqueismael.util.Util;

/**
 * Backing bean de la page transfert.xhtml
 *
 * @author ASPIRE
 */
@Named(value = "transfertBean")
@RequestScoped
public class transfertBean {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private CompteBancaire compteSource;
    private CompteBancaire compteDestination;
    private Long idSource;
    private Long idDestination;
    private int montant;

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public void setIdDestination(Long idDestination) {
        this.idDestination = idDestination;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Long getIdSource() {
        return idSource;
    }

    public Long getIdDestination() {
        return idDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void loadCompteSource() {
        this.compteSource = gestionnaireCompte.findById(idSource);
    }

    public void loadCompteDestination() {
        this.compteDestination = gestionnaireCompte.findById(idDestination);
    }

    public String transferer() {
        this.loadCompteSource();
        this.loadCompteDestination();
        
        CompteBancaire source = gestionnaireCompte.findById(idSource);
        CompteBancaire destination = gestionnaireCompte.findById(idDestination);

        boolean erreur = false;
        if (source == null || destination == null) {
            // Message d'erreur associé au composant source ; form:source est l'id client
            // si l'id du formulaire est "form" et l'id du champ de saisie de l'id de la source est "source"
            // dans la page JSF qui lance le transfert.
            if(source == null) {
                Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            }
            if(destination == null) {
                Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destination");
            }
            erreur = true;
        } else {
            if (source.getSolde() < montant) { // à compléter pour le cas où le solde du compte source est insuffisant...
                Util.messageErreur("Solde du compte source insuffisant !", "Solde du compte source insuffisant !", "form:source");
                erreur = true;
            }
        }
        if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }

        gestionnaireCompte.transferer(compteSource, compteDestination, montant);
        Util.addFlashInfoMessage("Transfert de "+ montant +" de "+ compteSource.getNom() +" vers "+ compteDestination.getNom() +" correctement effectué");
        return "listeComptes?faces-redirect=true";
    }

    /**
     * Creates a new instance of transfertBean
     */
    public transfertBean() {
    }

}
