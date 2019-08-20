/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.converters;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import mirangolo.sales.ejbs.ProvinciaFacade;
import mirangolo.sales.entities.Provincia;


/**
 *
 * @author Francis
 */
@FacesConverter(value = "provinciaConverter")
public class ProvinciaConverter implements Converter {

    ProvinciaFacade provinciaFacade = lookupProvinciaFacadeBean();

   

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        Provincia provincia;
        if (value != null) {
            provincia = (Provincia) provinciaFacade.find(value);
            return provincia;
        }

        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Provincia) value).getIdProvincia().toString();
        }
        return "";
    }

    private ProvinciaFacade lookupProvinciaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ProvinciaFacade) c.lookup("java:global/mirangolosales/ProvinciaFacade!mirangolosales.facade.ProvinciaFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

   

}
