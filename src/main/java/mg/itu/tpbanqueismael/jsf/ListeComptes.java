/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueismael.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.itu.tpbanqueismael.entity.CompteBancaire;
import mg.itu.tpbanqueismael.service.GestionnaireCompte;
import mg.itu.tpbanqueismael.util.Util;

/**
 *
 * @author ASPIRE
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    private List<CompteBancaire> accountList;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public String supprimerCompte(CompteBancaire compteBancaire) {
        gestionnaireCompte.supprimerCompte(compteBancaire);
        Util.addFlashInfoMessage("Compte de " + compteBancaire.getNom() + " supprimé");
        return "listeComptes?faces-redirect=true";
    }

    public List<CompteBancaire> getAllComptes() {
        if (accountList == null) {
            accountList = gestionnaireCompte.getAllComptes();
        }
        return accountList;
    }

    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }

}
