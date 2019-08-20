/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author informatica
 */
@Entity
@Table(name = "forma_pagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormaPagamento.findAll", query = "SELECT f FROM FormaPagamento f")
    , @NamedQuery(name = "FormaPagamento.findByIdFormaPagamento", query = "SELECT f FROM FormaPagamento f WHERE f.idFormaPagamento = :idFormaPagamento")
    , @NamedQuery(name = "FormaPagamento.findByNomeFormaPagamento", query = "SELECT f FROM FormaPagamento f WHERE f.nomeFormaPagamento = :nomeFormaPagamento")})
public class FormaPagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_forma_pagamento")
    private Integer idFormaPagamento;
    @Size(max = 45)
    @Column(name = "nome_forma_pagamento")
    private String nomeFormaPagamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormaPagamento")
    private Collection<Venda> vendaCollection;

    public FormaPagamento() {
    }

    public FormaPagamento(Integer idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public Integer getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(Integer idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public String getNomeFormaPagamento() {
        return nomeFormaPagamento;
    }

    public void setNomeFormaPagamento(String nomeFormaPagamento) {
        this.nomeFormaPagamento = nomeFormaPagamento;
    }

    @XmlTransient
    public Collection<Venda> getVendaCollection() {
        return vendaCollection;
    }

    public void setVendaCollection(Collection<Venda> vendaCollection) {
        this.vendaCollection = vendaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormaPagamento != null ? idFormaPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormaPagamento)) {
            return false;
        }
        FormaPagamento other = (FormaPagamento) object;
        if ((this.idFormaPagamento == null && other.idFormaPagamento != null) || (this.idFormaPagamento != null && !this.idFormaPagamento.equals(other.idFormaPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mirangolo.sales.entities.FormaPagamento[ idFormaPagamento=" + idFormaPagamento + " ]";
    }
    
}
