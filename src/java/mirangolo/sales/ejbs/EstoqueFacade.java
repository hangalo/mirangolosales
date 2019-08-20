/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.ejbs;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import mirangolo.sales.entities.Estoque;

/**
 *
 * @author informatica
 */
@Stateless
public class EstoqueFacade extends AbstractFacade<Estoque> {

    @PersistenceContext(unitName = "mirangolosalesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstoqueFacade() {
        super(Estoque.class);
    }
    
    
     public List<Estoque> findEstoqueBetweenDataEntrada(Date inicio, Date fim) {
        Query query = em.createQuery("SELECT e FROM Estoque e WHERE e.dataEntradaArtigo BETWEEN :inicio AND :fim");
        query.setParameter("inicio", inicio, TemporalType.DATE);
        query.setParameter("fim", fim, TemporalType.DATE);
        query.setMaxResults(5000);
        return query.getResultList();

    }
    
}
