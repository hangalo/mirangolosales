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
import mirangolo.sales.ejbs.DepartamentoFacade;
import mirangolo.sales.entities.Departamento;


/**
 *
 * @author Francis
 */
@FacesConverter(value = "departamentoConverter")
public class DepartamentoConverter implements Converter {

    DepartamentoFacade departamentoFacade = lookupDepartamentoFacadeBean();

   

   

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        Departamento departamento;
        if (value != null) {
            departamento = (Departamento) departamentoFacade.find(value);
            return departamento;
        }

        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Departamento) value).getIdDepartamento().toString();
        }
        return "";
    }

    private DepartamentoFacade lookupDepartamentoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (DepartamentoFacade) c.lookup("java:global/mirangolosales/DepartamentoFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    
   

}
