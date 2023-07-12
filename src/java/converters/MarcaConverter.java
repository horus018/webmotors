package converters;

import dao.MarcaDAO;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import model.Marca;

@Named("marcaConverter")
@ApplicationScoped
public class MarcaConverter implements Converter<Marca> {

    @Inject
    MarcaDAO dao;
    
    @Override
    public Marca getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            int id = Integer.parseInt( value );
            return dao.findById( id );
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Marca value) {
        if ( value == null) {
            return null;
        }
        return String.valueOf( value.getId() );
    }
    
}
