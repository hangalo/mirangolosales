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
import mirangolo.sales.ejbs.FuncionarioFacade;
import mirangolo.sales.entities.Funcionario;

/**
 *
 * @author Francis
 */
@FacesConverter(value = "funcionarioConverter")
public class FuncionarioConverter1 implements Converter {

    FuncionarioFacade funcionarioFacade = lookupFuncionarioFacadeBean();

   

   

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        Funcionario funcionario;
        if (value != null) {
            funcionario = (Funcionario) funcionarioFacade.find(value);
            return funcionario;
        }

        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Funcionario) value).getIdFuncionario().toString();
        }
        return "";
    }

    private FuncionarioFacade lookupFuncionarioFacadeBean() {
        try {
            Context c = new InitialContext();
            return (FuncionarioFacade) c.lookup("java:global/mirangolosales/FuncionarioFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

   

   

}
