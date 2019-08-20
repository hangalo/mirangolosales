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
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import mirangolo.sales.ejbs.MunicipioFacade;
import mirangolo.sales.ejbs.ProvinciaFacade;
import mirangolo.sales.entities.Municipio;
import mirangolo.sales.entities.Provincia;

/**
 *
 * @author Francis
 */
@Named(value = "localidadeMB")
@RequestScoped
public class LocalidadeMB implements Serializable {

    @Inject
    ProvinciaFacade provinciaFacade;
    @Inject
    MunicipioFacade municipioFacade;

    List<Provincia> provincias;
    List<Municipio> municipios;

    public LocalidadeMB() {
    }

    @PostConstruct
    public void init() {
        provincias = provinciaFacade.findAll();
        municipios = municipioFacade.findAll();

    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }
    
    
    
    
     public List<SelectItem> getCarregaMunicipios() {
        List<SelectItem> items = new ArrayList<>();
        for (Municipio municipioActual : municipioFacade.findAll()) {
            items.add(new SelectItem(municipioActual, municipioActual.getNomeMunicipio()));
        }

        return items;
    }
    
     
     public List<SelectItem> getCarregaProvincias() {
        List<SelectItem> items = new ArrayList<>();
        for (Provincia provinciaActual : provinciaFacade.findAll()) {
            items.add(new SelectItem(provinciaActual, provinciaActual.getNomeProvincia()));
        }

        return items;
    }
    

}
