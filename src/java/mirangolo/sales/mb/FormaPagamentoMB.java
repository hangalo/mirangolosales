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
import mirangolo.sales.ejbs.FormaPagamentoFacade;
import mirangolo.sales.entities.FormaPagamento;
import mirangolosales.util.JSFUtil;

/**
 *
 * @author informatica
 */
@Named(value = "formaPagamentoMB")
@RequestScoped
public class FormaPagamentoMB {

    @Inject
    private FormaPagamentoFacade formaPagamentoFacade;

    private FormaPagamento formaPagamento;
    private List<FormaPagamento> formaPagamentos;

    /**
     * Creates a new instance of FormaPagamentoMB
     */
    public FormaPagamentoMB() {
    }

    @PostConstruct
    public void init() {
        formaPagamento = new FormaPagamento();
        formaPagamentos = formaPagamentoFacade.findAll();
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<FormaPagamento> getFormaPagamentos() {
        return formaPagamentos;
    }

    public void setFormaPagamentos(List<FormaPagamento> formaPagamentos) {
        this.formaPagamentos = formaPagamentos;
    }
    
    
    

    public List<SelectItem> getCarregarFormaPagamentos() {
        List<SelectItem> selectItems = new ArrayList<>();
        List<FormaPagamento> formaPagamentoList = formaPagamentoFacade.findAll();
        for (FormaPagamento formaPagamentoActual : formaPagamentoList) {
            SelectItem item = new SelectItem(formaPagamentoActual, formaPagamentoActual.getNomeFormaPagamento());
            selectItems.add(item);
        }
        return selectItems;
    }

    public void save(ActionEvent actionEvent) {

        formaPagamentoFacade.create(formaPagamento);
        formaPagamento = new FormaPagamento();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Forma de pagamento guardada com sucesso", "Guardar"));
        JSFUtil.refresh();
    }

    public void edit(ActionEvent actionEvent) {

         formaPagamentoFacade.edit(formaPagamento);
       formaPagamento = new FormaPagamento();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Forma de pagamento alterada com sucesso", "Editar"));
        JSFUtil.refresh();
    }

    public void delete(ActionEvent actionEvent) {
       formaPagamentoFacade.remove(formaPagamento);
       formaPagamento = new FormaPagamento();
       
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Forma de pagamento eliminada com sucesso", "Eliminar"));
        JSFUtil.refresh();
    }

}
