/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.mb;

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
import mirangolo.sales.ejbs.CategoriaArtigoFacade;
import mirangolo.sales.entities.CategoriaArtigo;
import mirangolosales.util.JSFUtil;

/**
 *
 * @author informatica
 */
@Named(value = "categoriaArtigoMB")
@RequestScoped
public class CategoriaArtigoMB {

     @Inject
    private CategoriaArtigoFacade categoriaArtigoFacade;

    
    
    
    private CategoriaArtigo categoriaArtigo;
    private List<CategoriaArtigo> categoriaArtigos;
    
    
    public CategoriaArtigoMB() {
    }
    
    @PostConstruct
    public void init(){
    categoriaArtigo = new CategoriaArtigo();
    categoriaArtigos = categoriaArtigoFacade.findAll();
    }

    public CategoriaArtigo getCategoriaArtigo() {
        return categoriaArtigo;
    }

    public void setCategoriaArtigo(CategoriaArtigo categoriaArtigo) {
        this.categoriaArtigo = categoriaArtigo;
    }

    public List<CategoriaArtigo> getCategoriaArtigos() {
        return categoriaArtigos;
    }

    public void setCategoriaArtigos(List<CategoriaArtigo> categoriaArtigos) {
        this.categoriaArtigos = categoriaArtigos;
    }
    
    
     public List<SelectItem> getCarregarCategoriaArtigos() {
        List<SelectItem> selectItems = new ArrayList<>();
        List<CategoriaArtigo> artigos = categoriaArtigoFacade.findAll();
        for (CategoriaArtigo categoriaActual : artigos) {
            SelectItem item = new SelectItem(categoriaActual, categoriaActual.getNomeCategoriaArtigo());
            selectItems.add(item);
        }
        return selectItems;
    }
    
    
    
     public List<SelectItem> getCarregarCategoriasArtigo() {
        List<SelectItem> selectItems = new ArrayList<>();
        List<CategoriaArtigo> categoriaArtigoList = categoriaArtigoFacade.findAll();
        for (CategoriaArtigo categoriaArtigoActual : categoriaArtigoList) {
            SelectItem item = new SelectItem(categoriaArtigoActual, categoriaArtigoActual.getNomeCategoriaArtigo());
            selectItems.add(item);
        }
        return selectItems;
    }
    
    
    
    
    public void save(ActionEvent actionEvent){

        categoriaArtigoFacade.create(categoriaArtigo);
        categoriaArtigo = new CategoriaArtigo();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Categoria de Artigo guardada com sucesso",  "Guardar"));
        JSFUtil.refresh();
    }
    
    
      
    public void edit(ActionEvent actionEvent){

        categoriaArtigoFacade.edit(categoriaArtigo);
        categoriaArtigo = new CategoriaArtigo();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Categoria de Artigo alterada com sucesso", "Editar" ));
        JSFUtil.refresh();
    }
    
    
    
      public void delete(ActionEvent actionEvent){
        categoriaArtigoFacade.remove(categoriaArtigo);
        categoriaArtigo = new CategoriaArtigo();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Categoria de Artigo alterada com sucesso", "Editar" ));
        JSFUtil.refresh();
    }
    
    
    
}
