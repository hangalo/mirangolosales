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
import mirangolo.sales.ejbs.FornecedorFacade;
import mirangolo.sales.entities.Artigo;
import mirangolo.sales.entities.Fornecedor;
import mirangolosales.util.JSFUtil;
import mirangolosales.util.ResourceFiles;



/**
 *
 * @author Francis
 */
@Named(value = "fornecedorMB")
@RequestScoped
public class FornecedorMB implements Serializable{

    @Inject
    FornecedorFacade  fornecedorFacade;
     @Inject
    ResourceFiles resourceFiles;
    
    private Fornecedor fornecedor;
    private List<Fornecedor> fornecedores;
 
    public FornecedorMB() {
    }
    
    @PostConstruct
    public void init(){
    fornecedores = fornecedorFacade.findAll();
    fornecedor = new Fornecedor();
    
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    
      public List<SelectItem> carregarFornecedores(){
    List<SelectItem> selectItems = new ArrayList<>();
    List<Fornecedor>fornecedorList = fornecedorFacade.findAll();
        for (Fornecedor fornecedorActual : fornecedorList) {
            SelectItem item = new SelectItem(fornecedorActual, fornecedorActual.getNomeFornecedor()+"\t"+fornecedorActual.getDistritoFornecedor());
            selectItems.add(item);
        }
    return selectItems;
    }
    
       
    public void save(ActionEvent actionEvent){

        fornecedorFacade.create(fornecedor);
        fornecedor = new Fornecedor();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Fornecedor registado com sucesso",  "Guardar"));
        JSFUtil.refresh();
    }
    
    
      
    public void edit(ActionEvent actionEvent){

        fornecedorFacade.edit(fornecedor);
        fornecedor = new Fornecedor();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Fornecedor alterado com sucesso", "Editar" ));
        JSFUtil.refresh();
    }
    
    
    
      public void delete(ActionEvent actionEvent){
        fornecedorFacade.remove(fornecedor);
        fornecedor = new Fornecedor();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Fornecedor alterado com sucesso", "Editar" ));
        JSFUtil.refresh();
    }
    
    
    
}
