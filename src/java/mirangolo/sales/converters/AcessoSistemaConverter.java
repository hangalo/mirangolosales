package mirangolo.sales.converters;

import java.util.HashMap;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import mirangolo.sales.ejbs.AcessoSistemaFacade;
import mirangolo.sales.entities.AcessoSistema;

@FacesConverter(value="acessoSistemaConverter")
public class AcessoSistemaConverter implements Converter {
    
    private static Map<String, AcessoSistema> mapa = new HashMap<String, AcessoSistema>();
   
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value){
        return mapa.get(value);
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value){
        if (value instanceof AcessoSistema) {
            AcessoSistema acessoSistema = (AcessoSistema) value;
            mapa.put(String.valueOf(acessoSistema.getIdAcessoSistema()), acessoSistema);
            return String.valueOf(acessoSistema.getIdAcessoSistema());
        } else{
            return "";
        }
    }
}