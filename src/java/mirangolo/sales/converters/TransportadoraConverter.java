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
import mirangolo.sales.ejbs.TransportadoraFacade;
import mirangolo.sales.entities.Transportadora;


/**
 *
 * @author Francis
 */
@FacesConverter(value = "transportadoraConverter")
public class TransportadoraConverter implements Converter {

    TransportadoraFacade transportadoraFacade = lookupTransportadoraFacadeBean();


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

       Transportadora transportadora;
        if (value != null) {
            transportadora = (Transportadora) transportadoraFacade.find(value);
            return transportadora;
        }

        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Transportadora) value).getIdtransportadora().toString();
        }
        return "";
    }

    private TransportadoraFacade lookupTransportadoraFacadeBean() {
        try {
            Context c = new InitialContext();
            return (TransportadoraFacade) c.lookup("java:global/mirangolosales/TransportadoraFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    

}
