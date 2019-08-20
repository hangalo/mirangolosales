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
@Table(name = "sector")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sector.findAll", query = "SELECT s FROM Sector s")
    , @NamedQuery(name = "Sector.findByIdSector", query = "SELECT s FROM Sector s WHERE s.idSector = :idSector")
    , @NamedQuery(name = "Sector.findByNomeSector", query = "SELECT s FROM Sector s WHERE s.nomeSector = :nomeSector")})
public class Sector implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sector")
    private Integer idSector;
    @Size(max = 45)
    @Column(name = "nome_sector")
    private String nomeSector;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSector")
    private Collection<Seccao> seccaoCollection;

    public Sector() {
    }

    public Sector(Integer idSector) {
        this.idSector = idSector;
    }

    public Integer getIdSector() {
        return idSector;
    }

    public void setIdSector(Integer idSector) {
        this.idSector = idSector;
    }

    public String getNomeSector() {
        return nomeSector;
    }

    public void setNomeSector(String nomeSector) {
        this.nomeSector = nomeSector;
    }

    @XmlTransient
    public Collection<Seccao> getSeccaoCollection() {
        return seccaoCollection;
    }

    public void setSeccaoCollection(Collection<Seccao> seccaoCollection) {
        this.seccaoCollection = seccaoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSector != null ? idSector.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sector)) {
            return false;
        }
        Sector other = (Sector) object;
        if ((this.idSector == null && other.idSector != null) || (this.idSector != null && !this.idSector.equals(other.idSector))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mirangolo.sales.entities.Sector[ idSector=" + idSector + " ]";
    }
    
}
