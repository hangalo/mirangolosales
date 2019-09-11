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
@Table(name = "categoria_artigo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaArtigo.findAll", query = "SELECT c FROM CategoriaArtigo c")
    , @NamedQuery(name = "CategoriaArtigo.findByIdCategoriaArtigo", query = "SELECT c FROM CategoriaArtigo c WHERE c.idCategoriaArtigo = :idCategoriaArtigo")
    , @NamedQuery(name = "CategoriaArtigo.findByNomeCategoriaArtigo", query = "SELECT c FROM CategoriaArtigo c WHERE c.nomeCategoriaArtigo = :nomeCategoriaArtigo")})
public class CategoriaArtigo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categoria_artigo")
    private Integer idCategoriaArtigo;
    @Size(max = 45)
    @Column(name = "nome_categoria_artigo")
    private String nomeCategoriaArtigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoriaArtigo")
    private Collection<Artigo> artigoCollection;

    public CategoriaArtigo() {
    }

    public CategoriaArtigo(Integer idCategoriaArtigo) {
        this.idCategoriaArtigo = idCategoriaArtigo;
    }

    public Integer getIdCategoriaArtigo() {
        return idCategoriaArtigo;
    }

    public void setIdCategoriaArtigo(Integer idCategoriaArtigo) {
        this.idCategoriaArtigo = idCategoriaArtigo;
    }

    public String getNomeCategoriaArtigo() {
        return nomeCategoriaArtigo;
    }

    public void setNomeCategoriaArtigo(String nomeCategoriaArtigo) {
        this.nomeCategoriaArtigo = nomeCategoriaArtigo;
    }

    @XmlTransient
    public Collection<Artigo> getArtigoCollection() {
        return artigoCollection;
    }

    public void setArtigoCollection(Collection<Artigo> artigoCollection) {
        this.artigoCollection = artigoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoriaArtigo != null ? idCategoriaArtigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaArtigo)) {
            return false;
        }
        CategoriaArtigo other = (CategoriaArtigo) object;
        if ((this.idCategoriaArtigo == null && other.idCategoriaArtigo != null) || (this.idCategoriaArtigo != null && !this.idCategoriaArtigo.equals(other.idCategoriaArtigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomeCategoriaArtigo;
    }
    
}
