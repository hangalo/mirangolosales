/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author informatica
 */
@Entity
@Table(name = "itens_venda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItensVenda.findAll", query = "SELECT i FROM ItensVenda i")
    , @NamedQuery(name = "ItensVenda.findByIdItensVenda", query = "SELECT i FROM ItensVenda i WHERE i.idItensVenda = :idItensVenda")
    , @NamedQuery(name = "ItensVenda.findByQuantidadeProduto", query = "SELECT i FROM ItensVenda i WHERE i.quantidadeProduto = :quantidadeProduto")
    , @NamedQuery(name = "ItensVenda.findByPrecoVenda", query = "SELECT i FROM ItensVenda i WHERE i.precoVenda = :precoVenda")
    , @NamedQuery(name = "ItensVenda.findByTaxaIva", query = "SELECT i FROM ItensVenda i WHERE i.taxaIva = :taxaIva")})
public class ItensVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_itens_venda")
    private Integer idItensVenda;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantidade_produto")
    private Double quantidadeProduto;
    @Column(name = "preco_venda")
    private Double precoVenda;
    @Column(name = "taxa_iva")
    private Double taxaIva;
    @JoinColumn(name = "id_artigo", referencedColumnName = "id_artigo")
    @ManyToOne(optional = false)
    private Artigo idArtigo;
    @JoinColumn(name = "id_venda", referencedColumnName = "id_venda")
    @ManyToOne(optional = false)
    private Venda idVenda;

    public ItensVenda() {
    }

    public ItensVenda(Integer idItensVenda) {
        this.idItensVenda = idItensVenda;
    }

    
  public ItensVenda(Artigo artigo) {
       this.idArtigo = artigo;
      
    }
    
    
    public ItensVenda(Artigo artigo, Double precoArtigo, Double quantidade) {
       this.idArtigo = artigo;
       this.precoVenda = precoArtigo;
       this.quantidadeProduto = quantidade;
    }
    
    

    public Integer getIdItensVenda() {
        return idItensVenda;
    }

    public void setIdItensVenda(Integer idItensVenda) {
        this.idItensVenda = idItensVenda;
    }

    public Double getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(Double quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Double getTaxaIva() {
        return taxaIva;
    }

    public void setTaxaIva(Double taxaIva) {
        this.taxaIva = taxaIva;
    }

    public Artigo getIdArtigo() {
        return idArtigo;
    }

    public void setIdArtigo(Artigo idArtigo) {
        this.idArtigo = idArtigo;
    }

    public Venda getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Venda idVenda) {
        this.idVenda = idVenda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItensVenda != null ? idItensVenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensVenda)) {
            return false;
        }
        ItensVenda other = (ItensVenda) object;
        if ((this.idItensVenda == null && other.idItensVenda != null) || (this.idItensVenda != null && !this.idItensVenda.equals(other.idItensVenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mirangolo.sales.entities.ItensVenda[ idItensVenda=" + idItensVenda + " ]";
    }
    
}
