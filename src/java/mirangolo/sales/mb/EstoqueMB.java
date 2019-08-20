/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import mirangolo.sales.ejbs.EstoqueFacade;
import mirangolo.sales.entities.Artigo;
import mirangolo.sales.entities.Estoque;
import mirangolosales.util.DateUtil;
import mirangolosales.util.JSFUtil;

/**
 *
 * @author Francis
 */
@Named(value = "estoqueMB")
@RequestScoped
public class EstoqueMB implements Serializable {

    @Inject
    EstoqueFacade estoqueFacade;

    private Artigo artigo;
    private Estoque estoque;
    private List<Estoque> estoques;
    private List<Estoque> auditoriaEstoques;
    private Date inicio, fim;

    public EstoqueMB() {
    }

    @PostConstruct
    public void init() {
        estoques = estoqueFacade.findAll();
        estoque = new Estoque();
        estoque.setDataEntradaArtigo(DateUtil.getDataActual());
        artigo = new Artigo();
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public List<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }

    public Artigo pegaArtigo(Artigo artigo) {
        this.artigo = artigo;
        System.out.println("EstoqueMB >>>>>>>>>>" + this.artigo);
        return artigo;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public List<Estoque> getAuditoriaEstoques() {
        return auditoriaEstoques;
    }

    public void setAuditoriaEstoques(List<Estoque> auditoriaEstoques) {
        this.auditoriaEstoques = auditoriaEstoques;
    }

    public void save() {
        estoque.setIdArtigo(artigo);
        estoque.setDisponivel(artigo.getDisponivel());
        estoque.setQuantidadeArtigo(artigo.getExistencias());
        estoqueFacade.create(estoque);
        estoque = new Estoque();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Estoque registado com sucesso", "Guardar"));
        JSFUtil.refresh();
    }

    public void save(ActionEvent actionEvent) {
        Double quantidade = artigo.getExistencias();
        estoque.setIdArtigo(artigo);
        estoque.setQuantidadeArtigo(quantidade);
        estoqueFacade.create(estoque);
        estoque = new Estoque();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Estoque registado com sucesso", "Guardar"));
        JSFUtil.refresh();
    }

    public void edit(ActionEvent actionEvent) {

        estoqueFacade.edit(estoque);
        estoque = new Estoque();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Estoque alterado com sucesso", "Editar"));
        JSFUtil.refresh();
    }

    public void delete(ActionEvent actionEvent) {
        estoqueFacade.remove(estoque);
        estoque = new Estoque();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Estoque alterado com sucesso", "Editar"));
        JSFUtil.refresh();
    }

    public void executarBuscarEstoqueEntreDatas(ActionEvent event) {
        buscarAuditoriaEntreDatas();
    }

    public List<Estoque> buscarAuditoriaEntreDatas() {
        auditoriaEstoques = estoqueFacade.findEstoqueBetweenDataEntrada(inicio, fim);
        System.out.println("Passou >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>01");
        return auditoriaEstoques;
    }

}
