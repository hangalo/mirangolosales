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
import mirangolo.sales.ejbs.SeccaoFacade;
import mirangolo.sales.entities.Seccao;
import mirangolo.sales.entities.Sector;
import mirangolosales.util.JSFUtil;

/**
 *
 * @author informatica
 */
@Named(value = "seccaoMB")
@RequestScoped
public class SeccaoMB {

  
     @Inject
    private SeccaoFacade seccaoFacade;

    private Seccao seccao;
    private List<Seccao> seccoes;
    public SeccaoMB() {
    }
    
    
     @PostConstruct
    public void init() {
        seccao = new Seccao();
        seccoes = seccaoFacade.findAll();
    }

    public Seccao getSeccao() {
        return seccao;
    }

    public void setSeccao(Seccao seccao) {
        this.seccao = seccao;
    }

    public List<Seccao> getSeccoes() {
        return seccoes;
    }

    public void setSeccoes(List<Seccao> seccoes) {
        this.seccoes = seccoes;
    }
    
    
    
     
    public List<SelectItem> carregarSeccoes() {
        List<SelectItem> selectItems = new ArrayList<>();
        List<Seccao> seccaoList = seccaoFacade.findAll();
        for (Seccao sectorActual : seccaoList) {
            SelectItem item = new SelectItem(sectorActual, sectorActual.getNomeSeccao());
            selectItems.add(item);
        }
        return selectItems;
    }

    public void save(ActionEvent actionEvent) {

        seccaoFacade.create(seccao);
        seccao = new Seccao();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Seccão guardada com sucesso", "Guardar"));
        JSFUtil.refresh();
    }

    public void edit(ActionEvent actionEvent) {

         seccaoFacade.edit(seccao);
       seccao = new Seccao();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Seccão alterada com sucesso", "Editar"));
        JSFUtil.refresh();
    }

    public void delete(ActionEvent actionEvent) {
       seccaoFacade.remove(seccao);
       seccao = new Seccao();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Seccão eliminada com sucesso", "Eliminar"));
        JSFUtil.refresh();
    }
    
    
    
    
    
}
