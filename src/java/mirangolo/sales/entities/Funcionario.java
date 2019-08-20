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
@Table(name = "funcionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f")
    , @NamedQuery(name = "Funcionario.findByIdFuncionario", query = "SELECT f FROM Funcionario f WHERE f.idFuncionario = :idFuncionario")
    , @NamedQuery(name = "Funcionario.findByNomeFuncionario", query = "SELECT f FROM Funcionario f WHERE f.nomeFuncionario = :nomeFuncionario")
    , @NamedQuery(name = "Funcionario.findBySobrenomeFuncionario", query = "SELECT f FROM Funcionario f WHERE f.sobrenomeFuncionario = :sobrenomeFuncionario")
    , @NamedQuery(name = "Funcionario.findByCasaFuncionario", query = "SELECT f FROM Funcionario f WHERE f.casaFuncionario = :casaFuncionario")
    , @NamedQuery(name = "Funcionario.findByRuaFuncionario", query = "SELECT f FROM Funcionario f WHERE f.ruaFuncionario = :ruaFuncionario")
    , @NamedQuery(name = "Funcionario.findByBairroFuncionario", query = "SELECT f FROM Funcionario f WHERE f.bairroFuncionario = :bairroFuncionario")
    , @NamedQuery(name = "Funcionario.findByDistritoFuncionario", query = "SELECT f FROM Funcionario f WHERE f.distritoFuncionario = :distritoFuncionario")
    , @NamedQuery(name = "Funcionario.findByTelefonePrincipal", query = "SELECT f FROM Funcionario f WHERE f.telefonePrincipal = :telefonePrincipal")
    , @NamedQuery(name = "Funcionario.findByTelemovelPrincipal", query = "SELECT f FROM Funcionario f WHERE f.telemovelPrincipal = :telemovelPrincipal")
    , @NamedQuery(name = "Funcionario.findByEmailPrincipal", query = "SELECT f FROM Funcionario f WHERE f.emailPrincipal = :emailPrincipal")})
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_funcionario")
    private Integer idFuncionario;
    @Size(max = 45)
    @Column(name = "nome_funcionario")
    private String nomeFuncionario;
    @Size(max = 45)
    @Column(name = "sobrenome_funcionario")
    private String sobrenomeFuncionario;
    @Size(max = 45)
    @Column(name = "casa_funcionario")
    private String casaFuncionario;
    @Size(max = 45)
    @Column(name = "rua_funcionario")
    private String ruaFuncionario;
    @Size(max = 45)
    @Column(name = "bairro_funcionario")
    private String bairroFuncionario;
    @Size(max = 45)
    @Column(name = "distrito_funcionario")
    private String distritoFuncionario;
    @Size(max = 45)
    @Column(name = "telefone_principal")
    private String telefonePrincipal;
    @Size(max = 45)
    @Column(name = "telemovel_principal")
    private String telemovelPrincipal;
    @Size(max = 45)
    @Column(name = "email_principal")
    private String emailPrincipal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFuncionario")
    private Collection<AcessoSistema> acessoSistemaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFuncionario")
    private Collection<Venda> vendaCollection;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne(optional = false)
    private Municipio idMunicipio;

    public Funcionario() {
    }

    public Funcionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getSobrenomeFuncionario() {
        return sobrenomeFuncionario;
    }

    public void setSobrenomeFuncionario(String sobrenomeFuncionario) {
        this.sobrenomeFuncionario = sobrenomeFuncionario;
    }

    public String getCasaFuncionario() {
        return casaFuncionario;
    }

    public void setCasaFuncionario(String casaFuncionario) {
        this.casaFuncionario = casaFuncionario;
    }

    public String getRuaFuncionario() {
        return ruaFuncionario;
    }

    public void setRuaFuncionario(String ruaFuncionario) {
        this.ruaFuncionario = ruaFuncionario;
    }

    public String getBairroFuncionario() {
        return bairroFuncionario;
    }

    public void setBairroFuncionario(String bairroFuncionario) {
        this.bairroFuncionario = bairroFuncionario;
    }

    public String getDistritoFuncionario() {
        return distritoFuncionario;
    }

    public void setDistritoFuncionario(String distritoFuncionario) {
        this.distritoFuncionario = distritoFuncionario;
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

    @XmlTransient
    public Collection<AcessoSistema> getAcessoSistemaCollection() {
        return acessoSistemaCollection;
    }

    public void setAcessoSistemaCollection(Collection<AcessoSistema> acessoSistemaCollection) {
        this.acessoSistemaCollection = acessoSistemaCollection;
    }

    @XmlTransient
    public Collection<Venda> getVendaCollection() {
        return vendaCollection;
    }

    public void setVendaCollection(Collection<Venda> vendaCollection) {
        this.vendaCollection = vendaCollection;
    }

    public Municipio getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Municipio idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFuncionario != null ? idFuncionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.idFuncionario == null && other.idFuncionario != null) || (this.idFuncionario != null && !this.idFuncionario.equals(other.idFuncionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mirangolo.sales.entities.Funcionario[ idFuncionario=" + idFuncionario + " ]";
    }
    
}
