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
import mirangolo.sales.ejbs.SectorFacade;
import mirangolo.sales.entities.Sector;
import mirangolosales.util.JSFUtil;

/**
 *
 * @author informatica
 */
@Named(value = "sectorMB")
@RequestScoped
public class SectorMB {

    @Inject
    private SectorFacade sectorFacade;

    private Sector sector;
    private List<Sector> sectores;

    /**
     * Creates a new instance of SectorMB
     */
    public SectorMB() {
    }

    @PostConstruct
    public void init() {
        sector = new Sector();
        sectores = sectorFacade.findAll();
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public List<Sector> getSectores() {
        return sectores;
    }

    public void setSectores(List<Sector> sectores) {
        this.sectores = sectores;
    }

    public List<SelectItem> carregarSectores() {
        List<SelectItem> selectItems = new ArrayList<>();
        List<Sector> sectorList = sectorFacade.findAll();
        for (Sector sectorActual : sectorList) {
            SelectItem item = new SelectItem(sectorActual, sectorActual.getNomeSector());
            selectItems.add(item);
        }
        return selectItems;
    }

    public void save(ActionEvent actionEvent) {

        sectorFacade.create(sector);
        sector = new Sector();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sector guardado com sucesso", "Guardar"));
        JSFUtil.refresh();
    }

    public void edit(ActionEvent actionEvent) {

        sectorFacade.edit(sector);
        sector = new Sector();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sector alterado com sucesso", "Editar"));
        JSFUtil.refresh();
    }

    public void delete(ActionEvent actionEvent) {
        sectorFacade.remove(sector);
        sector = new Sector();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sector eliminado com sucesso", "Eliminar"));
        JSFUtil.refresh();
    }

}
