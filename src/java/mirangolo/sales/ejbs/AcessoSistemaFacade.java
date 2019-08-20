/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.ejbs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mirangolo.sales.entities.AcessoSistema;

/**
 *
 * @author informatica
 */
@Stateless
public class AcessoSistemaFacade extends AbstractFacade<AcessoSistema> {

    @PersistenceContext(unitName = "mirangolosalesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AcessoSistemaFacade() {
        super(AcessoSistema.class);
    }
    
}
