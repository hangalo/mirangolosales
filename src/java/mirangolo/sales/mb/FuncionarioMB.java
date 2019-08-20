/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import mirangolo.sales.ejbs.FuncionarioFacade;
import mirangolo.sales.entities.Artigo;
import mirangolo.sales.entities.Funcionario;
import mirangolosales.util.JSFUtil;
import mirangolosales.util.ResourceFiles;

/**
 *
 * @author Francis
 */
@Named(value = "funcionarioMB")
@RequestScoped
public class FuncionarioMB implements Serializable{

    @Inject
    FuncionarioFacade  funcionarioFacade;
     @Inject
    ResourceFiles resourceFiles;
    
    private Funcionario funcionario;
    private List<Funcionario> funcionarios;
 
    public FuncionarioMB() {
    }
    
    @PostConstruct
    public void init(){
    funcionarios = funcionarioFacade.findAll();
    funcionario = new Funcionario();
    
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

   
     public List<SelectItem> carregarFuncionarios(){
    List<SelectItem> selectItems = new ArrayList<>();
    List<Funcionario>funcionarioList = funcionarioFacade.findAll();
        for (Funcionario funcionarioActual : funcionarioList) {
            SelectItem item = new SelectItem(funcionarioActual, funcionarioActual.getNomeFuncionario()+"\t"+funcionarioActual.getSobrenomeFuncionario());
            selectItems.add(item);
        }
    return selectItems;
    }
    
     
        
    public void save(ActionEvent actionEvent){

        funcionarioFacade.create(funcionario);
        funcionario = new Funcionario();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Funcionario registado com sucesso",  "Guardar"));
        JSFUtil.refresh();
    }
    
    
      
    public void edit(ActionEvent actionEvent){

        funcionarioFacade.edit(funcionario);
        funcionario = new Funcionario();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Funcionario alterado com sucesso", "Editar" ));
        JSFUtil.refresh();
    }
    
    
    
      public void delete(ActionEvent actionEvent){
        funcionarioFacade.remove(funcionario);
        funcionario = new Funcionario();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Funcionario alterado com sucesso", "Editar" ));
        JSFUtil.refresh();
    }
    
    
    
}
