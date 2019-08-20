/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolosales.util;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

/**
 *
 * @author Francis
 */
@Named(value = "resourceFiles")
@SessionScoped
public class ResourceFiles implements Serializable {

    Locale localeActual;
    ResourceBundle etiquetasFormularios;
    ResourceBundle etiquetasAplicacao;

    public ResourceFiles() {
    }

    @PostConstruct
    public void init() {
        carregarLocale();
    }

    public void carregarLocale() {

        localeActual = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        etiquetasFormularios = ResourceBundle.getBundle("mirangolosales.properties.messages", localeActual);
        etiquetasAplicacao = ResourceBundle.getBundle("mirangolosales.properties.application", localeActual);

    }

    public Locale getLocaleActual() {
        return localeActual;
    }

    public void setLocaleActual(Locale localeActual) {
        this.localeActual = localeActual;
    }

    public ResourceBundle getEtiquetasFormularios() {
        return etiquetasFormularios;
    }

    public void setEtiquetasFormularios(ResourceBundle etiquetasFormularios) {
        this.etiquetasFormularios = etiquetasFormularios;
    }

    public ResourceBundle getEtiquetasAplicacao() {
        return etiquetasAplicacao;
    }

    public void setEtiquetasAplicacao(ResourceBundle etiquetasAplicacao) {
        this.etiquetasAplicacao = etiquetasAplicacao;
    }

    /*
    
    
    Devolve a mensagem para a etiquetas dos formularios
    
     */
    public String getMensagemEtiquetasFormularios(String mensagem) {
        return etiquetasFormularios.getString(mensagem);

    }

    /*
    
    
    Devolve a mensagem para a etiquetas para a aplicação
    
     */
    public String getMensagemEtiquetasAplicação(String mensagem) {
        return etiquetasAplicacao.getString(mensagem);

    }

}
