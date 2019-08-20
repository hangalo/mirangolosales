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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import mirangolo.sales.entities.Artigo;

import mirangolo.sales.entities.CategoriaArtigo;

/**
 *
 * @author informatica
 */
@Stateless
public class ArtigoFacade extends AbstractFacade<Artigo> {

    @PersistenceContext(unitName = "mirangolosalesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtigoFacade() {
        super(Artigo.class);
    }

    public List<Artigo> findArtigoByCategoria(CategoriaArtigo categoria) {
        Query query;
        query = em.createQuery("SELECT a FROM Artigo a WHERE a.idCategoriaArtigo =:categoria ORDER BY a.nomeArtigo");
        query.setParameter("categoria", categoria);
        return query.getResultList();

    }
    
    
    
      public List<Artigo> findArtigosByNome(String nomeArtigo) {
        Query query;
        query = em.createQuery("SELECT a FROM Artigo a WHERE a.nomeArtigo LIKE  :nomeArtigo ORDER BY a.nomeArtigo");
        query.setParameter("nomeArtigo", "%"+nomeArtigo+"%");
        return query.getResultList();
    }
      
      
      
      
      public List<Artigo> findArtigosByCodigoBarras(String codigoBarraArtigo) {
        Query query;
        query = em.createQuery("SELECT a FROM Artigo a WHERE a.codigoBarraArtigo LIKE  :codigoBarraArtigo ORDER BY a.nomeArtigo");
        query.setParameter("nomeArtigo", "%"+codigoBarraArtigo+"%");
        return query.getResultList();
    }

    
     public List<Artigo> queryArtigosAll() {
        List<Artigo> lista = new ArrayList<>();
        Query query = em.createQuery("SELECT a FROM Artigo a  ORDER BY a.nomeArtigo");
        lista = query.getResultList();
       
        return lista;

    }
    
    /*
    public List<Artigo> artigoPorCategoria(CategoriaArtigo categoria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Artigo> criteriaQuery = cb.createQuery(Artigo.class);
        Root<Artigo> artigoRoot = criteriaQuery.from(Artigo.class);
        criteriaQuery.select(artigoRoot).where(cb.equal(artigoRoot.get(Artigo_.idCategoriaArtigo), categoria));
        TypedQuery<Artigo> typedQuery = em.createQuery(criteriaQuery);
        typedQuery.setParameter("categoria", categoria);
        return typedQuery.getResultList();

    }*/

    /*
    
     CriteriaBuilder cb = em.getCriteriaBuilder();

  CriteriaQuery<Country> q = cb.createQuery(Country.class);
  Root<Country> c = q.from(Country.class);
  ParameterExpression<Integer> p = cb.parameter(Integer.class);
  q.select(c).where(cb.gt(c.get("population"), p));
    
      TypedQuery<Country> query = em.createQuery(q);
  query.setParameter(p, 10000000);
  List<Country> results = query.getResultList();
    
   
    public List<Artigo> artigoPorCategoria2(CategoriaArtigo categoria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Artigo> criteriaQuery = cb.createQuery(Artigo.class);
        Root<Artigo> artigoRoot = criteriaQuery.from(Artigo.class);
        ParameterExpression<CategoriaArtigo> p = cb.parameter(CategoriaArtigo.class);

        criteriaQuery.select(artigoRoot).where(cb.equal(artigoRoot.get(Artigo_.idCategoriaArtigo), p));
        TypedQuery<Artigo> typedQuery = em.createQuery(criteriaQuery);
        typedQuery.setParameter("categoria", categoria);
        return typedQuery.getResultList();

    }
  */
    public void acutalizaQuantidade(Integer artigo, Double quantidade) {
        Query query;
        query = em.createQuery("UPDATE Artigo a SET a.existencias = (a.existencias - :quantidade) WHERE a.idArtigo =:artigo ");
        query.setParameter("quantidade", quantidade);
        query.setParameter("artigo", artigo);
        query.executeUpdate();
    }

}
