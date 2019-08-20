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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "artigo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artigo.findAll", query = "SELECT a FROM Artigo a")
    , @NamedQuery(name = "Artigo.findByIdArtigo", query = "SELECT a FROM Artigo a WHERE a.idArtigo = :idArtigo")
    , @NamedQuery(name = "Artigo.findByCodigoBarraArtigo", query = "SELECT a FROM Artigo a WHERE a.codigoBarraArtigo = :codigoBarraArtigo")
    , @NamedQuery(name = "Artigo.findByCodigoComercialArtigo", query = "SELECT a FROM Artigo a WHERE a.codigoComercialArtigo = :codigoComercialArtigo")
    , @NamedQuery(name = "Artigo.findByNomeArtigo", query = "SELECT a FROM Artigo a WHERE a.nomeArtigo = :nomeArtigo")
    , @NamedQuery(name = "Artigo.findByPrecoArtigo", query = "SELECT a FROM Artigo a WHERE a.precoArtigo = :precoArtigo")
    , @NamedQuery(name = "Artigo.findByExistencias", query = "SELECT a FROM Artigo a WHERE a.existencias = :existencias")
    , @NamedQuery(name = "Artigo.findByDisponivel", query = "SELECT a FROM Artigo a WHERE a.disponivel = :disponivel")})
public class Artigo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_artigo")
    private Integer idArtigo;
    @Size(max = 45)
    @Column(name = "codigo_barra_artigo")
    private String codigoBarraArtigo;
    @Size(max = 45)
    @Column(name = "codigo_comercial_artigo")
    private String codigoComercialArtigo;
    @Size(max = 45)
    @Column(name = "nome_artigo")
    private String nomeArtigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preco_artigo")
    private Double precoArtigo;
    @Column(name = "existencias")
    private Double existencias;
    @Column(name = "disponivel")
    private Boolean disponivel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArtigo")
    private Collection<Compra> compraCollection;
    @JoinColumn(name = "id_categoria_artigo", referencedColumnName = "id_categoria_artigo")
    @ManyToOne(optional = false)
    private CategoriaArtigo idCategoriaArtigo;
    @JoinColumn(name = "id_seccao", referencedColumnName = "id_seccao")
    @ManyToOne(optional = false)
    private Seccao idSeccao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArtigo")
    private Collection<Estoque> estoqueCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArtigo")
    private Collection<ItensVenda> itensVendaCollection;

    public Artigo() {
    }

    public Artigo(Integer idArtigo) {
        this.idArtigo = idArtigo;
    }

    public Integer getIdArtigo() {
        return idArtigo;
    }

    public void setIdArtigo(Integer idArtigo) {
        this.idArtigo = idArtigo;
    }

    public String getCodigoBarraArtigo() {
        return codigoBarraArtigo;
    }

    public void setCodigoBarraArtigo(String codigoBarraArtigo) {
        this.codigoBarraArtigo = codigoBarraArtigo;
    }

    public String getCodigoComercialArtigo() {
        return codigoComercialArtigo;
    }

    public void setCodigoComercialArtigo(String codigoComercialArtigo) {
        this.codigoComercialArtigo = codigoComercialArtigo;
    }

    public String getNomeArtigo() {
        return nomeArtigo;
    }

    public void setNomeArtigo(String nomeArtigo) {
        this.nomeArtigo = nomeArtigo;
    }

    public Double getPrecoArtigo() {
        return precoArtigo;
    }

    public void setPrecoArtigo(Double precoArtigo) {
        this.precoArtigo = precoArtigo;
    }

    public Double getExistencias() {
        return existencias;
    }

    public void setExistencias(Double existencias) {
        this.existencias = existencias;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    @XmlTransient
    public Collection<Compra> getCompraCollection() {
        return compraCollection;
    }

    public void setCompraCollection(Collection<Compra> compraCollection) {
        this.compraCollection = compraCollection;
    }

    public CategoriaArtigo getIdCategoriaArtigo() {
        return idCategoriaArtigo;
    }

    public void setIdCategoriaArtigo(CategoriaArtigo idCategoriaArtigo) {
        this.idCategoriaArtigo = idCategoriaArtigo;
    }

    public Seccao getIdSeccao() {
        return idSeccao;
    }

    public void setIdSeccao(Seccao idSeccao) {
        this.idSeccao = idSeccao;
    }

    @XmlTransient
    public Collection<Estoque> getEstoqueCollection() {
        return estoqueCollection;
    }

    public void setEstoqueCollection(Collection<Estoque> estoqueCollection) {
        this.estoqueCollection = estoqueCollection;
    }

    @XmlTransient
    public Collection<ItensVenda> getItensVendaCollection() {
        return itensVendaCollection;
    }

    public void setItensVendaCollection(Collection<ItensVenda> itensVendaCollection) {
        this.itensVendaCollection = itensVendaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArtigo != null ? idArtigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artigo)) {
            return false;
        }
        Artigo other = (Artigo) object;
        if ((this.idArtigo == null && other.idArtigo != null) || (this.idArtigo != null && !this.idArtigo.equals(other.idArtigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mirangolo.sales.entities.Artigo[ idArtigo=" + idArtigo + " ]";
    }
    
}
