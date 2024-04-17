/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueismael.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.tpbanqueismael.entity.CompteBancaire;
import mg.itu.tpbanqueismael.service.GestionnaireCompte;
import mg.itu.tpbanqueismael.util.Util;

/**
 *
 * @author ASPIRE
 */
@Named(value = "modificationBean")
@ViewScoped
public class modificationBean implements Serializable{

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private CompteBancaire compte;
    private Long id;
    private String ancienNom;

    public String enregistrer() {
        gestionnaireCompte.update(compte);
        Util.addFlashInfoMessage("Nom "+ ancienNom +" changé en "+ compte.getNom());
        return "listeComptes?faces-redirect=true";
    }

    public void loadCompte() {
        compte = gestionnaireCompte.findById(id);
        ancienNom = compte.getNom();
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Creates a new instance of modificationBean
     */
    public modificationBean() {
    }

}
