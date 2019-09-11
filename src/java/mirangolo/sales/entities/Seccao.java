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
@Table(name = "seccao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seccao.findAll", query = "SELECT s FROM Seccao s")
    , @NamedQuery(name = "Seccao.findByIdSeccao", query = "SELECT s FROM Seccao s WHERE s.idSeccao = :idSeccao")
    , @NamedQuery(name = "Seccao.findByNomeSeccao", query = "SELECT s FROM Seccao s WHERE s.nomeSeccao = :nomeSeccao")})
public class Seccao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_seccao")
    private Integer idSeccao;
    @Size(max = 45)
    @Column(name = "nome_seccao")
    private String nomeSeccao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeccao")
    private Collection<Artigo> artigoCollection;
    @JoinColumn(name = "id_sector", referencedColumnName = "id_sector")
    @ManyToOne(optional = false)
    private Sector idSector;

    public Seccao() {
    }

    public Seccao(Integer idSeccao) {
        this.idSeccao = idSeccao;
    }

    public Integer getIdSeccao() {
        return idSeccao;
    }

    public void setIdSeccao(Integer idSeccao) {
        this.idSeccao = idSeccao;
    }

    public String getNomeSeccao() {
        return nomeSeccao;
    }

    public void setNomeSeccao(String nomeSeccao) {
        this.nomeSeccao = nomeSeccao;
    }

    @XmlTransient
    public Collection<Artigo> getArtigoCollection() {
        return artigoCollection;
    }

    public void setArtigoCollection(Collection<Artigo> artigoCollection) {
        this.artigoCollection = artigoCollection;
    }

    public Sector getIdSector() {
        return idSector;
    }

    public void setIdSector(Sector idSector) {
        this.idSector = idSector;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeccao != null ? idSeccao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seccao)) {
            return false;
        }
        Seccao other = (Seccao) object;
        if ((this.idSeccao == null && other.idSeccao != null) || (this.idSeccao != null && !this.idSeccao.equals(other.idSeccao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomeSeccao;
    }
    
}
