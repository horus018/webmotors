
package converters;

import dao.ModeloDAO;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import model.Modelo;

@Named("modeloConverter")
@ApplicationScoped
public class ModeloConverter implements Converter<Modelo>{

    @Inject
    ModeloDAO dao;

    @Override
    public Modelo getAsObject(FacesContext context, UIComponent component, 
            String value) {
        try {
            Integer id = Integer.parseInt( value );
            return dao.findById( id );
        } catch (Exception t) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, 
            Modelo value) {
        if (value == null) {
            return null;
        }
        return String.valueOf( value.getId() ); 
    }
        
}
