/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.util;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Francis
 */
@Named(value = "linguaMBean")
@SessionScoped
public class LinguaMBean implements Serializable {

    /**
     * Creates a new instance of LinguaMBean
     */
    private String lingua;
    private Map<String, Object> listaLinguas;
    
    @PostConstruct
    public void init() {
        carregaLinguas();
    }
    
    public LinguaMBean() {
    }
    
    public String getLingua() {
        return lingua;
    }
    
    public void setLingua(String lingua) {
        this.lingua = lingua;
    }
    
    public Map<String, Object> getListaLinguas() {
        return listaLinguas;
    }
    
    public void setListaLinguas(Map<String, Object> listaLinguas) {
        this.listaLinguas = listaLinguas;
    }
    
    private void carregaLinguas() {
        lingua = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
        listaLinguas = new LinkedHashMap<>();
        listaLinguas.put("Português", new Locale("pt", "PT"));
        listaLinguas.put("Inglês", new Locale("en", "GB"));
        listaLinguas.put("Francês", new Locale("fr", "FR"));
        
    }
    
    public void mudarLingua(ValueChangeEvent event) {
        String linguaSeleccionada = event.getNewValue().toString();
        //percorre o mada para compare o codigo actual com os existentes no map
        
        for (Map.Entry<String, Object> valorMapa : listaLinguas.entrySet()) {
            if (valorMapa.getValue().toString().equals(linguaSeleccionada)) {
                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) valorMapa.getValue());
            }
        }
        
    }
    
    
    public void mudarLinguaPelaImagem(String linguaSeleccionada){
      
        for (Map.Entry<String, Object> valorMapa : listaLinguas.entrySet()) {
            if (valorMapa.getValue().toString().equals(linguaSeleccionada)) {
                this.lingua = linguaSeleccionada;
                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) valorMapa.getValue());
            }
        }
    
    }
    
}
