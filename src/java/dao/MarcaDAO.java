package dao;

import javax.enterprise.context.ApplicationScoped;
import model.Marca;

@ApplicationScoped
public class MarcaDAO extends GenericDAO<Marca> {
    
    @Override
    public String consultaListagem() { 
        return "select m from Marca m order by m.descricao";
    }
    
    public Marca findById( int id ) {
        return em.find(Marca.class, id);
    }
}
