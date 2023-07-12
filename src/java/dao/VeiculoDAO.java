
package dao;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import model.Modelo;
import model.Veiculo;

@SessionScoped
public class VeiculoDAO extends GenericDAO<Veiculo> implements Serializable {
        
    public List<Veiculo> filtrarPorModelo( Modelo m ) {
        return em.createQuery("select v from Veiculo v where v.modelo = :mod")
                 .setParameter("mod", m) 
                 .getResultList();
    }
    
    @Override
    public String consultaListagem() {
        return "select v from Veiculo v order by v.placa";
    }
    
    public Veiculo buscarPorPlaca( String placa ) {
        return em.find(Veiculo.class, placa);
    }
    
}
