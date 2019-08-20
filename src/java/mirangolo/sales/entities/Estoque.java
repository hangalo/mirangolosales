/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author informatica
 */
@Entity
@Table(name = "estoque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estoque.findAll", query = "SELECT e FROM Estoque e")
    , @NamedQuery(name = "Estoque.findByIdEstoque", query = "SELECT e FROM Estoque e WHERE e.idEstoque = :idEstoque")
    , @NamedQuery(name = "Estoque.findByDataEntradaArtigo", query = "SELECT e FROM Estoque e WHERE e.dataEntradaArtigo = :dataEntradaArtigo")
    , @NamedQuery(name = "Estoque.findByQuantidadeArtigo", query = "SELECT e FROM Estoque e WHERE e.quantidadeArtigo = :quantidadeArtigo")
    , @NamedQuery(name = "Estoque.findByDisponivel", query = "SELECT e FROM Estoque e WHERE e.disponivel = :disponivel")
    , @NamedQuery(name = "Estoque.findByValidade", query = "SELECT e FROM Estoque e WHERE e.validade = :validade")})
public class Estoque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estoque")
    private Integer idEstoque;
    @Column(name = "data_entrada_artigo")
    @Temporal(TemporalType.DATE)
    private Date dataEntradaArtigo;
    @Column(name = "quantidade_artigo")
    private Double quantidadeArtigo;
    @Column(name = "disponivel")
    private Boolean disponivel;
    @Column(name = "validade")
    @Temporal(TemporalType.DATE)
    private Date validade;
    @JoinColumn(name = "id_artigo", referencedColumnName = "id_artigo")
    @ManyToOne(optional = false)
    private Artigo idArtigo;

    public Estoque() {
    }

    public Estoque(Integer idEstoque) {
        this.idEstoque = idEstoque;
    }

    public Integer getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(Integer idEstoque) {
        this.idEstoque = idEstoque;
    }

    public Date getDataEntradaArtigo() {
        return dataEntradaArtigo;
    }

    public void setDataEntradaArtigo(Date dataEntradaArtigo) {
        this.dataEntradaArtigo = dataEntradaArtigo;
    }

    public Double getQuantidadeArtigo() {
        return quantidadeArtigo;
    }

    public void setQuantidadeArtigo(Double quantidadeArtigo) {
        this.quantidadeArtigo = quantidadeArtigo;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public Artigo getIdArtigo() {
        return idArtigo;
    }

    public void setIdArtigo(Artigo idArtigo) {
        this.idArtigo = idArtigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstoque != null ? idEstoque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estoque)) {
            return false;
        }
        Estoque other = (Estoque) object;
        if ((this.idEstoque == null && other.idEstoque != null) || (this.idEstoque != null && !this.idEstoque.equals(other.idEstoque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mirangolo.sales.entities.Estoque[ idEstoque=" + idEstoque + " ]";
    }
    
}
