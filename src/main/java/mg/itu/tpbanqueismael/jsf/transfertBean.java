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

/**
 * Backing bean de la page transfert.xhtml
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
        gestionnaireCompte.transferer(compteSource, compteDestination, montant);
        return "listeComptes?faces-redirect=true";
    }

    /**
     * Creates a new instance of transfertBean
     */
    public transfertBean() {
    }
    
}
