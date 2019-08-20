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
import mirangolo.sales.ejbs.DepartamentoFacade;
import mirangolo.sales.entities.Departamento;
import mirangolosales.util.JSFUtil;

/**
 *
 * @author informatica
 */
@Named(value = "departamentoMB")
@RequestScoped
public class DepartamentoMB {

    /**
     * Creates a new instance of DepartamentoMB
     */
    @Inject
    private DepartamentoFacade departamentoFacade;

    private Departamento departamento;
    private List<Departamento> departamentos;

    public DepartamentoMB() {
    }

    @PostConstruct
    public void init() {
        departamento = new Departamento();
        departamentos = departamentoFacade.findAll();
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public List<SelectItem> carregarDepartamentos() {
        List<SelectItem> selectItems = new ArrayList<>();
        List<Departamento> departamentoList = departamentoFacade.findAll();
        for (Departamento departamentoActual : departamentoList) {
            SelectItem item = new SelectItem(departamentoActual, departamentoActual.getNomeDepartamento());
            selectItems.add(item);
        }
        return selectItems;
    }

    public void save(ActionEvent actionEvent) {

        departamentoFacade.create(departamento);
        departamento = new Departamento();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento guardado com sucesso", "Guardar"));
        JSFUtil.refresh();
    }

    public void edit(ActionEvent actionEvent) {

        departamentoFacade.edit(departamento);
        departamento = new Departamento();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento alterado com sucesso", "Editar"));
        JSFUtil.refresh();
    }

    public void delete(ActionEvent actionEvent) {
        departamentoFacade.remove(departamento);
        departamento = new Departamento();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento eliminado com sucesso", "Eliminar"));
        JSFUtil.refresh();
    }

}
