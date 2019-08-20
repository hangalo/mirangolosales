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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author informatica
 */
@Entity
@Table(name = "acesso_sistema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AcessoSistema.findAll", query = "SELECT a FROM AcessoSistema a")
    , @NamedQuery(name = "AcessoSistema.findByIdAcessoSistema", query = "SELECT a FROM AcessoSistema a WHERE a.idAcessoSistema = :idAcessoSistema")
    , @NamedQuery(name = "AcessoSistema.findByLogin", query = "SELECT a FROM AcessoSistema a WHERE a.login = :login")
    , @NamedQuery(name = "AcessoSistema.findByPassword", query = "SELECT a FROM AcessoSistema a WHERE a.password = :password")})
public class AcessoSistema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_acesso_sistema")
    private Integer idAcessoSistema;
    @Size(max = 45)
    @Column(name = "login")
    private String login;
    @Size(max = 45)
    @Column(name = "password")
    private String password;
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id_funcionario")
    @ManyToOne(optional = false)
    private Funcionario idFuncionario;

    public AcessoSistema() {
    }

    public AcessoSistema(Integer idAcessoSistema) {
        this.idAcessoSistema = idAcessoSistema;
    }

    public Integer getIdAcessoSistema() {
        return idAcessoSistema;
    }

    public void setIdAcessoSistema(Integer idAcessoSistema) {
        this.idAcessoSistema = idAcessoSistema;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Funcionario getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Funcionario idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcessoSistema != null ? idAcessoSistema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcessoSistema)) {
            return false;
        }
        AcessoSistema other = (AcessoSistema) object;
        if ((this.idAcessoSistema == null && other.idAcessoSistema != null) || (this.idAcessoSistema != null && !this.idAcessoSistema.equals(other.idAcessoSistema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mirangolo.sales.entities.AcessoSistema[ idAcessoSistema=" + idAcessoSistema + " ]";
    }
    
}
