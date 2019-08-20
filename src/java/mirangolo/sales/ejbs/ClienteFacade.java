/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.ejbs;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mirangolo.sales.entities.Cliente;

/**
 *
 * @author informatica
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "mirangolosalesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

     public List<Cliente> queryClientesAll() {
        List<Cliente> lista = new ArrayList<>();
        Query query = em.createQuery("SELECT e FROM Cliente e  ORDER BY e.nomeCliente");
        lista = query.getResultList();
       
        return lista;

    }
    
    public List<Cliente> queryClienteByNomeSobrenome(String nome, String sobrenome) {
        Query query;
        query = em.createQuery("SELECT c FROM Cliente c WHERE c.nomeCliente LIKE :nome AND c.sobrenomeCliente LIKE :sobrenome");
        query.setParameter("nome", "%" + nome + "%");
        query.setParameter("sobrenome", "%" + sobrenome + "%");
        return query.getResultList();

    }

    public List<Cliente> queryClienteByNumeroContribuinte(String numeroContribuinte) {
        Query query;
        query = em.createQuery("SELECT c FROM Cliente c WHERE c.numeroContribuinte LIKE :numeroContribuinte");
        query.setParameter("numeroContribuinte", "%" + numeroContribuinte + "%");
        return query.getResultList();
    }

}
