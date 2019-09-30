package mirangolo.sales.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import mirangolo.sales.ejbs.AcessoSistemaFacade;
import mirangolo.sales.ejbs.FuncionarioFacade;
import mirangolo.sales.entities.AcessoSistema;
import mirangolo.sales.entities.Funcionario;

/**
 *
 * @author kleyn
 */
@Named(value = "acessoSitemaMB")
@SessionScoped

public class AcessoSitemaMB implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private AcessoSistema acessoSistema;
    private List<AcessoSistema> acessos;
    private List<Funcionario> funcionarios;

    @Inject
    AcessoSistemaFacade acessoSistemaFacade;
    @Inject
    FuncionarioFacade funcionarioFacade;    
    
    public AcessoSitemaMB() {
    }
    
    @PostConstruct
    public void inicializar(){
        acessoSistema = new AcessoSistema();
        acessos = new ArrayList<>();
        funcionarios = new ArrayList<>();        
    }

    public AcessoSistema getAcessoSistema() {
        return acessoSistema;
    }

    public void setAcessoSistema(AcessoSistema acessoSistema) {
        this.acessoSistema = acessoSistema;
    }

    public List<AcessoSistema> getAcessos() {
        acessos = acessoSistemaFacade.findAll();
        return acessos;
    }

    public List<Funcionario> getFuncionarios() {
        funcionarios = funcionarioFacade.findAll();
        return funcionarios;
    }
    
    public void guardar (ActionEvent evt){
        acessoSistemaFacade.create(acessoSistema);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(" ");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(AcessoSitemaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String startEdit() {
        return "index?faces-redirect=true";
    }
    
    public void editar (ActionEvent evt){
        acessoSistemaFacade.edit(acessoSistema);
        try {
             FacesContext.getCurrentInstance().getExternalContext().redirect(" ");
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(AcessoSitemaMB.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public String delete (){
        acessoSistemaFacade.remove(acessoSistema);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
        try {
             FacesContext.getCurrentInstance().getExternalContext().redirect(" ");
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(AcessoSitemaMB.class.getName()).log(Level.SEVERE, null, ex);
         }
        acessos = null;
        return "autoNoticiaListar?faces-redirect=true";
    }
    
}
