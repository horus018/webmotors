package dao;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

public abstract class GenericDAO<T> {
    
    @PersistenceContext(name = "webmotorsPU")
    EntityManager em;
    
    @Resource
    UserTransaction tx;
    
    public GenericDAO() { }
    
    public void inserir(T obj) {
        try {
            tx.begin();
            em.persist( obj );
            tx.commit();    
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }
    
    public void remover(T obj) {
        em.remove(obj);
    }
    
    public void atualiar(T obj) {
        em.merge(obj);
    }
    
    public List<T> listar() {
        return em.createQuery(consultaListagem()).getResultList();
    }
    
    public abstract String consultaListagem();
       
}
