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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author informatica
 */
@Entity
@Table(name = "transportadora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transportadora.findAll", query = "SELECT t FROM Transportadora t")
    , @NamedQuery(name = "Transportadora.findByIdtransportadora", query = "SELECT t FROM Transportadora t WHERE t.idtransportadora = :idtransportadora")})
public class Transportadora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtransportadora")
    private Integer idtransportadora;

    public Transportadora() {
    }

    public Transportadora(Integer idtransportadora) {
        this.idtransportadora = idtransportadora;
    }

    public Integer getIdtransportadora() {
        return idtransportadora;
    }

    public void setIdtransportadora(Integer idtransportadora) {
        this.idtransportadora = idtransportadora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtransportadora != null ? idtransportadora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transportadora)) {
            return false;
        }
        Transportadora other = (Transportadora) object;
        if ((this.idtransportadora == null && other.idtransportadora != null) || (this.idtransportadora != null && !this.idtransportadora.equals(other.idtransportadora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mirangolo.sales.entities.Transportadora[ idtransportadora=" + idtransportadora + " ]";
    }
    
}
