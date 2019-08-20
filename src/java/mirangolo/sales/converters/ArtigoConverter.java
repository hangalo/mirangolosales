/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.converters;

import java.util.HashMap;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import mirangolo.sales.entities.Artigo;


/**
 *
 * @author Francis
 */
@FacesConverter(value = "artigoConverter")
public class ArtigoConverter implements Converter {

    
    
     
     private static Map<String, Artigo> mapa = new HashMap<String, Artigo>();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
               return mapa.get(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Artigo) {
            Artigo f = (Artigo) value;
            mapa.put(String.valueOf(f.getIdArtigo()), f);
             return String.valueOf(f.getIdArtigo());
        } else {
            return "";
        }
    }

}
