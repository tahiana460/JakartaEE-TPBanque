/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanqueismael.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import java.util.List;
import mg.itu.tpbanqueismael.entity.CompteBancaire;
import mg.itu.tpbanqueismael.service.GestionnaireCompte;

/**
 *
 * @author ASPIRE
 */
@ApplicationScoped
public class init {
    @Inject 
    private GestionnaireCompte gestionnaireCompte;
    
    public void initAccount(
    @Observes
    @Initialized(ApplicationScoped.class)
    ServletContext context) {
        List<CompteBancaire> comptes = gestionnaireCompte.getAllComptes();
        if(comptes.isEmpty()) {
            gestionnaireCompte.creerCompte(new CompteBancaire("John Lennon", 150000));
            gestionnaireCompte.creerCompte(new CompteBancaire("Paul McCartney", 950000));
            gestionnaireCompte.creerCompte(new CompteBancaire("Ringo Starr,", 20000));
            gestionnaireCompte.creerCompte(new CompteBancaire("Georges Harrisson", 100000));
        }
    }
}
