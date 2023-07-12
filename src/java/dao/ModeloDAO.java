package dao;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import model.Marca;
import model.Modelo;

@ApplicationScoped
public class ModeloDAO extends GenericDAO<Modelo> implements Serializable {
    
    public List<Modelo> filtarPorMarca( Marca marca ) {
        return em.createQuery("select mo from Modelo mo where mo.marca = :ma order by mo.nome")
                 .setParameter("ma", marca)
                 .getResultList();
    }
    
    public Modelo findById(int id) {
        return em.find(Modelo.class, id);
    }
    
    @Override
    public String consultaListagem() {
        return "select mo from Modelo mo order by mo.nome";
    }
}
