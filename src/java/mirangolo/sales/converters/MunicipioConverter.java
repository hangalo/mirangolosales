/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.converters;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import mirangolo.sales.ejbs.MunicipioFacade;
import mirangolo.sales.entities.Artigo;
import mirangolo.sales.entities.Municipio;


/**
 *
 * @author Francis
 */
@FacesConverter(value = "municipioConverter")
public class MunicipioConverter implements Converter {

    
     private static Map<String, Municipio> mapa = new HashMap<String, Municipio>();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
               return mapa.get(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Municipio) {
            Municipio f = (Municipio) value;
            mapa.put(String.valueOf(f.getIdMunicipio()), f);
             return String.valueOf(f.getIdMunicipio());
        } else {
            return "";
        }
    }

   

}
