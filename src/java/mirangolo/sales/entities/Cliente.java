/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author informatica
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByIdCliente", query = "SELECT c FROM Cliente c WHERE c.idCliente = :idCliente")
    , @NamedQuery(name = "Cliente.findByNomeCliente", query = "SELECT c FROM Cliente c WHERE c.nomeCliente = :nomeCliente")
    , @NamedQuery(name = "Cliente.findBySobrenomeCliente", query = "SELECT c FROM Cliente c WHERE c.sobrenomeCliente = :sobrenomeCliente")
    , @NamedQuery(name = "Cliente.findByCasaCliente", query = "SELECT c FROM Cliente c WHERE c.casaCliente = :casaCliente")
    , @NamedQuery(name = "Cliente.findByRuaCliente", query = "SELECT c FROM Cliente c WHERE c.ruaCliente = :ruaCliente")
    , @NamedQuery(name = "Cliente.findByBairroCliente", query = "SELECT c FROM Cliente c WHERE c.bairroCliente = :bairroCliente")
    , @NamedQuery(name = "Cliente.findByDistritoCliente", query = "SELECT c FROM Cliente c WHERE c.distritoCliente = :distritoCliente")
    , @NamedQuery(name = "Cliente.findByTelefonePrincipal", query = "SELECT c FROM Cliente c WHERE c.telefonePrincipal = :telefonePrincipal")
    , @NamedQuery(name = "Cliente.findByTelemovelPrincipal", query = "SELECT c FROM Cliente c WHERE c.telemovelPrincipal = :telemovelPrincipal")
    , @NamedQuery(name = "Cliente.findByEmailPrincipal", query = "SELECT c FROM Cliente c WHERE c.emailPrincipal = :emailPrincipal")
    , @NamedQuery(name = "Cliente.findByDataCadastro", query = "SELECT c FROM Cliente c WHERE c.dataCadastro = :dataCadastro")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cliente")
    private Integer idCliente;
      
    @Size(max = 45)
    @Column(name = " numero_contribuinte")
    private String  numeroContribuinte;
    @Size(max = 45)
    @Column(name = "nome_cliente")
    private String nomeCliente;
    
    
    @Size(max = 45)
    @Column(name = "sobrenome_cliente")
    private String sobrenomeCliente;
    @Size(max = 45)
    @Column(name = "casa_cliente")
    private String casaCliente;
    @Size(max = 45)
    @Column(name = "rua_cliente")
    private String ruaCliente;
    @Size(max = 45)
    @Column(name = "bairro_cliente")
    private String bairroCliente;
    @Size(max = 45)
    @Column(name = "distrito_cliente")
    private String distritoCliente;
    @Size(max = 45)
    @Column(name = "telefone_principal")
    private String telefonePrincipal;
    @Size(max = 45)
    @Column(name = "telemovel_principal")
    private String telemovelPrincipal;
    @Size(max = 45)
    @Column(name = "email_principal")
    private String emailPrincipal;
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne(optional = false)
    private Municipio idMunicipio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente")
    private Collection<Venda> vendaCollection;

    public Cliente() {
    }

    public Cliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNumeroContribuinte() {
        return numeroContribuinte;
    }

    public void setNumeroContribuinte(String numeroContribuinte) {
        this.numeroContribuinte = numeroContribuinte;
    }
          
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getSobrenomeCliente() {
        return sobrenomeCliente;
    }

    public void setSobrenomeCliente(String sobrenomeCliente) {
        this.sobrenomeCliente = sobrenomeCliente;
    }

    public String getCasaCliente() {
        return casaCliente;
    }

    public void setCasaCliente(String casaCliente) {
        this.casaCliente = casaCliente;
    }

    public String getRuaCliente() {
        return ruaCliente;
    }

    public void setRuaCliente(String ruaCliente) {
        this.ruaCliente = ruaCliente;
    }

    public String getBairroCliente() {
        return bairroCliente;
    }

    public void setBairroCliente(String bairroCliente) {
        this.bairroCliente = bairroCliente;
    }

    public String getDistritoCliente() {
        return distritoCliente;
    }

    public void setDistritoCliente(String distritoCliente) {
        this.distritoCliente = distritoCliente;
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

    public String getEmailPrincipal() {
        return emailPrincipal;
    }

    public void setEmailPrincipal(String emailPrincipal) {
        this.emailPrincipal = emailPrincipal;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Municipio getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Municipio idMunicipio) {
        this.idMunicipio = idMunicipio;
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
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mirangolo.sales.entities.Cliente[ idCliente=" + idCliente + " ]";
    }
    
}
