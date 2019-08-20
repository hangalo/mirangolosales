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
import mirangolo.sales.entities.Sector;

/**
 *
 * @author Francis
 */
@FacesConverter(value = "sectorConverter")
public class SectorConverter implements Converter {

      
     private static Map<String, Sector> mapa = new HashMap<String, Sector>();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
               return mapa.get(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Sector) {
            Sector f = (Sector) value;
            mapa.put(String.valueOf(f.getIdSector()), f);
             return String.valueOf(f.getIdSector());
        } else {
            return "";
        }
    }
    

}
