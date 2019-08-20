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
import mirangolo.sales.ejbs.FornecedorFacade;
import mirangolo.sales.entities.Fornecedor;


/**
 *
 * @author Francis
 */
@FacesConverter(value = "fornecedorConverter")
public class FornecedorConverter implements Converter {

    FornecedorFacade fornecedorFacade = lookupFornecedorFacadeBean();

   

   

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        Fornecedor fornecedor;
        if (value != null) {
            fornecedor = (Fornecedor) fornecedorFacade.find(value);
            return fornecedor;
        }

        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Fornecedor) value).getIdFornecedor().toString();
        }
        return "";
    }

    private FornecedorFacade lookupFornecedorFacadeBean() {
        try {
            Context c = new InitialContext();
            return (FornecedorFacade) c.lookup("java:global/mirangolosales/FornecedorFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

   

   

}
