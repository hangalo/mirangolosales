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
@Table(name = "fornecedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f")
    , @NamedQuery(name = "Fornecedor.findByIdFornecedor", query = "SELECT f FROM Fornecedor f WHERE f.idFornecedor = :idFornecedor")
    , @NamedQuery(name = "Fornecedor.findByNomeFornecedor", query = "SELECT f FROM Fornecedor f WHERE f.nomeFornecedor = :nomeFornecedor")
    , @NamedQuery(name = "Fornecedor.findByCasaFornecedor", query = "SELECT f FROM Fornecedor f WHERE f.casaFornecedor = :casaFornecedor")
    , @NamedQuery(name = "Fornecedor.findByRuaFornecedor", query = "SELECT f FROM Fornecedor f WHERE f.ruaFornecedor = :ruaFornecedor")
    , @NamedQuery(name = "Fornecedor.findByBairroFornecedor", query = "SELECT f FROM Fornecedor f WHERE f.bairroFornecedor = :bairroFornecedor")
    , @NamedQuery(name = "Fornecedor.findByDistritoFornecedor", query = "SELECT f FROM Fornecedor f WHERE f.distritoFornecedor = :distritoFornecedor")
    , @NamedQuery(name = "Fornecedor.findByTelefonePrincipal", query = "SELECT f FROM Fornecedor f WHERE f.telefonePrincipal = :telefonePrincipal")
    , @NamedQuery(name = "Fornecedor.findByTelemovelPrincipal", query = "SELECT f FROM Fornecedor f WHERE f.telemovelPrincipal = :telemovelPrincipal")
    , @NamedQuery(name = "Fornecedor.findByEmailFornecedor", query = "SELECT f FROM Fornecedor f WHERE f.emailFornecedor = :emailFornecedor")})
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_fornecedor")
    private Integer idFornecedor;
    @Size(max = 45)
    @Column(name = "nome_fornecedor")
    private String nomeFornecedor;
    @Size(max = 45)
    @Column(name = "casa_fornecedor")
    private String casaFornecedor;
    @Size(max = 45)
    @Column(name = "rua_fornecedor")
    private String ruaFornecedor;
    @Size(max = 45)
    @Column(name = "bairro_fornecedor")
    private String bairroFornecedor;
    @Size(max = 45)
    @Column(name = "distrito_fornecedor")
    private String distritoFornecedor;
    @Size(max = 45)
    @Column(name = "telefone_principal")
    private String telefonePrincipal;
    @Size(max = 45)
    @Column(name = "telemovel_principal")
    private String telemovelPrincipal;
    @Size(max = 45)
    @Column(name = "email_fornecedor")
    private String emailFornecedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFornecedor")
    private Collection<Compra> compraCollection;

    public Fornecedor() {
    }

    public Fornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getCasaFornecedor() {
        return casaFornecedor;
    }

    public void setCasaFornecedor(String casaFornecedor) {
        this.casaFornecedor = casaFornecedor;
    }

    public String getRuaFornecedor() {
        return ruaFornecedor;
    }

    public void setRuaFornecedor(String ruaFornecedor) {
        this.ruaFornecedor = ruaFornecedor;
    }

    public String getBairroFornecedor() {
        return bairroFornecedor;
    }

    public void setBairroFornecedor(String bairroFornecedor) {
        this.bairroFornecedor = bairroFornecedor;
    }

    public String getDistritoFornecedor() {
        return distritoFornecedor;
    }

    public void setDistritoFornecedor(String distritoFornecedor) {
        this.distritoFornecedor = distritoFornecedor;
    }

    public String getTelefonePrincipal() {
        return telefonePrincipal;
    }

    public void setTelefonePrincipal(String telefonePrincipal) {
        this.telefonePrincipal = telefonePrincipal;
    }

    public String getTelemovelPrincipal() {
        return telemovelPrincipal;
    }

    public void setTelemovelPrincipal(String telemovelPrincipal) {
        this.telemovelPrincipal = telemovelPrincipal;
    }

    public String getEmailFornecedor() {
        return emailFornecedor;
    }

    public void setEmailFornecedor(String emailFornecedor) {
        this.emailFornecedor = emailFornecedor;
    }

    @XmlTransient
    public Collection<Compra> getCompraCollection() {
        return compraCollection;
    }

    public void setCompraCollection(Collection<Compra> compraCollection) {
        this.compraCollection = compraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFornecedor != null ? idFornecedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.idFornecedor == null && other.idFornecedor != null) || (this.idFornecedor != null && !this.idFornecedor.equals(other.idFornecedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mirangolo.sales.entities.Fornecedor[ idFornecedor=" + idFornecedor + " ]";
    }
    
}
