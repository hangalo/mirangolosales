/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.ejbs;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import mirangolo.sales.dados.DadosVenda;
import mirangolo.sales.entities.Cliente;
import mirangolo.sales.entities.Venda;


/**
 *
 * @author informatica
 */
@Stateless
public class VendaFacade extends AbstractFacade<Venda> {

    @PersistenceContext(unitName = "mirangolosalesPU")
    private EntityManager em;

    private final String queryVendas = "SELECT NEW mirangolo.sales.dados.DadosVenda(v.idVenda, v.dataVenda, v.valorTotal, v.descontoIvaTotal,  v.idCliente.nomeCliente, v.idCliente.sobrenomeCliente, v.idFormaPagamento.nomeFormaPagamento, v.idFuncionario.nomeFuncionario, v.idFuncionario.sobrenomeFuncionario) FROM Venda as v ORDER BY v.idVenda";

    private final String queryVendasEntreDatas = "SELECT NEW mirangolo.sales.dados.DadosVenda(v.idVenda, v.dataVenda, v.valorTotal, v.descontoIvaTotal,  v.idCliente.nomeCliente, v.idCliente.sobrenomeCliente, v.idFormaPagamento.nomeFormaPagamento, v.idFuncionario.nomeFuncionario, v.idFuncionario.sobrenomeFuncionario) FROM Venda as v WHERE v.dataVenda BETWEEN :inicio AND :fim  ORDER BY v.idVenda";
    private final String queryVendasPorClienteNomeSobrenome = "SELECT NEW mirangolo.sales.dados.DadosVenda(v.idVenda, v.dataVenda, v.valorTotal, v.descontoIvaTotal,  v.idCliente.nomeCliente, v.idCliente.sobrenomeCliente, v.idFormaPagamento.nomeFormaPagamento, v.idFuncionario.nomeFuncionario, v.idFuncionario.sobrenomeFuncionario) FROM Venda as v WHERE v.idCliente.nomeCliente LIKE :nomeCliente AND v.idCliente.sobrenomeCliente LIKE :sobrenomeCliente ORDER BY v.idVenda";
    private final String queryVendasPorClienteNome = "SELECT NEW mirangolo.sales.dados.DadosVenda(v.idVenda, v.dataVenda, v.valorTotal, v.descontoIvaTotal,  v.idCliente.nomeCliente, v.idCliente.sobrenomeCliente, v.idFormaPagamento.nomeFormaPagamento, v.idFuncionario.nomeFuncionario, v.idFuncionario.sobrenomeFuncionario) FROM Venda as v WHERE v.idCliente.nomeCliente LIKE :nomeCliente  ORDER BY v.idVenda";
    private final String queryVendasPorClienteSobrenome = "SELECT NEW mirangolo.sales.dados.DadosVenda(v.idVenda, v.dataVenda, v.valorTotal, v.descontoIvaTotal,  v.idCliente.nomeCliente, v.idCliente.sobrenomeCliente, v.idFormaPagamento.nomeFormaPagamento, v.idFuncionario.nomeFuncionario, v.idFuncionario.sobrenomeFuncionario) FROM Venda as v WHERE  v.idCliente.sobrenomeCliente LIKE :sobrenomeCliente ORDER BY v.idVenda";
    
    
    private final String queryVendaPorCliente ="SELECT NEW mirangolo.sales.dados.DadosVenda(v.idVenda, v.dataVenda, v.valorTotal, v.descontoIvaTotal, v.idCliente, v.idFormaPagamento.nomeFormaPagamento, v.idFuncionario.nomeFuncionario, v.idFuncionario.sobrenomeFuncionario) FROM Venda as v WHERE  v.idCliente =:cliente ORDER BY v.idVenda";
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VendaFacade() {
        super(Venda.class);
    }

    public Venda buscaPorNumero(Integer numeroVenda) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Venda> c = cb.createQuery(Venda.class);
        Root<Venda> venda = c.from(Venda.class);
        c.where(cb.equal(venda.get("idVenda"), cb.parameter(String.class, "idVenda")));
        TypedQuery<Venda> q = em.createQuery(c);
        return q.getSingleResult();
    }

    public List<Venda> buscarPorPeriodo(Date inicio, Date fim) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Venda> c = cb.createQuery(Venda.class);
        Root<Venda> venda = c.from(Venda.class);

        ParameterExpression<Date> dataInicio = cb.parameter(Date.class, "inicio");
        ParameterExpression<Date> dataFim = cb.parameter(Date.class, "fim");

        Expression<Date> data = venda.get("dataVenda");
        c.where(cb.between(data, dataInicio, dataFim));

        TypedQuery<Venda> q = em.createQuery(c);
        q.setParameter("inicio", inicio);
        q.setParameter("fim", fim);

        return q.getResultList();
    }
/*
    public List<Venda> exemplos(Date inicio, Date fim) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Venda> cQry = cb.createQuery(Venda.class);
        Root<Venda> vendaRoot = cQry.from(Venda.class);
        cQry.select(vendaRoot);
        cQry.orderBy(cb.desc(vendaRoot.get(Venda_.dataVenda)));

        return null;
    }
*/
    public Integer lastFactura() {
        Query query = em.createQuery("SELECT MAX(v.idVenda) FROM Venda v");
        return Integer.parseInt(query.getSingleResult().toString());

    }

    public Integer ultimaFactura() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Number> query = cb.createQuery(Number.class);
        Root<Venda> vendaRoot = query.from(Venda.class);
        query.select(cb.max(vendaRoot.<BigDecimal>get("idVenda")));
        TypedQuery<Number> q = em.createQuery(query);
        return Integer.parseInt(q.getSingleResult().toString());
    }
    
    
    /*
     public List<DadosVenda> findClienteByCliente(Cliente cliente) {
         
        System.out.println("FindByCliente. Facade\t"+cliente.getNomeCliente());
        Query query = em.createQuery("SELECT v FROM Venda v WHERE v.idCliente =:cliente ");
        query.setParameter("cliente",  cliente);
        query.setMaxResults(5000);
        return query.getResultList();

    }*/
    
    
      public List<Venda> findClienteByCliente(Cliente cliente) {
         
        System.out.println("FindByCliente. Facade\t"+cliente.getNomeCliente());
        Query query = em.createQuery("SELECT v FROM Venda v WHERE v.idCliente =:cliente ");
        query.setParameter("cliente",  cliente);
        query.setMaxResults(5000);
        return query.getResultList();

    }

    public List<DadosVenda> findClienteByNomeCognome(String nomeCliente, String sobrenomeCliente) {
        Query query = em.createQuery(queryVendasPorClienteNomeSobrenome);
        query.setParameter("nomeCliente", "%" + nomeCliente + "%");
        query.setParameter("sobrenomeCliente", "%" + sobrenomeCliente + "%");
        query.setMaxResults(5000);
        return query.getResultList();

    }
    
    
      public List<DadosVenda> findClienteByNome(String nomeCliente) {
        Query query = em.createQuery(queryVendasPorClienteNome);
        query.setParameter("nomeCliente", "%" + nomeCliente + "%");
         query.setMaxResults(5000);
        return query.getResultList();

    }
      
      
       public List<DadosVenda> findClienteByCognome(String sobrenomeCliente) {
        Query query = em.createQuery(queryVendasPorClienteSobrenome);
        query.setParameter("sobrenomeCliente", "%" + sobrenomeCliente + "%");
        query.setMaxResults(5000);
        return query.getResultList();

    }

    public List<Venda> findBetweenDataNascita(Date inicio, Date fim) {
                       
         Query query = em.createQuery("SELECT v FROM Venda v WHERE v.dataVenda BETWEEN :inicio AND :fim");
        query.setParameter("inicio", inicio, TemporalType.DATE);
        query.setParameter("fim", fim, TemporalType.DATE);
        query.setMaxResults(5000);
        return query.getResultList();

    }
    
      public List<Venda> findVendaById(int numeroVenda) {

        List<Venda> lista;
        String queryString = "SELECT v FROM Venda as v  WHERE v.idVenda =:numeroVenda";
        Query query = em.createQuery(queryString, Venda.class);
        query.setParameter("numeroVenda", numeroVenda);
        lista = query.getResultList();

        return lista;

    }
      
      
       public List<Venda> findVendaTodas() {

        List<Venda> lista;
        String queryString = "SELECT v FROM Venda as v ";
        Query query = em.createQuery(queryString, Venda.class);
       
        lista = query.getResultList();

        return lista;

    }
      
      
    
/*
      public List<DadosVenda> findBetweenDataNascita(Date inicio, Date fim) {
        System.out.println("Datas>>>>>>>>>>>>>>>>>>>>>>Facade"+inicio +""+fim);
       // Query query = em.createQuery(queryVendasEntreDatas);
                  
         Query query = em.createQuery("SELECT v FROM Venda v WHERE v.dataVenda BETWEEN :inicio AND :fim");
        query.setParameter("inicio", inicio, TemporalType.DATE);
        query.setParameter("fim", fim, TemporalType.DATE);
        query.setMaxResults(5000);
        return query.getResultList();

    }
    
    
    */
}
