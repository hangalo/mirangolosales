/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.ejbs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mirangolo.sales.entities.CategoriaArtigo;

/**
 *
 * @author informatica
 */
@Stateless
public class CategoriaArtigoFacade extends AbstractFacade<CategoriaArtigo> {

    @PersistenceContext(unitName = "mirangolosalesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaArtigoFacade() {
        super(CategoriaArtigo.class);
    }
    
}
