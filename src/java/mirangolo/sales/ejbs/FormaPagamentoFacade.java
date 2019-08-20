/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.ejbs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mirangolo.sales.entities.FormaPagamento;

/**
 *
 * @author informatica
 */
@Stateless
public class FormaPagamentoFacade extends AbstractFacade<FormaPagamento> {

    @PersistenceContext(unitName = "mirangolosalesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormaPagamentoFacade() {
        super(FormaPagamento.class);
    }
    
}
