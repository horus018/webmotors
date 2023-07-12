package beans;

import dao.MarcaDAO;
import dao.ModeloDAO;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import model.Marca;
import model.Modelo;


@Named("aplicacao")
@ApplicationScoped
public class AplicacaoBean {
    
    @Inject
    private MarcaDAO marcaDAO; 
    
    @Inject
    private ModeloDAO modeloDAO;
    
    private List<Modelo> modelos;
    
    private List<SelectItem> itensMarca;
    
    private Marca marcaSelecionada; 
    
    private String nomeMarca, nomeModelo;
    
    public AplicacaoBean() { }
    
    @PostConstruct
    public void iniciar() {
        modelos = null;
    } 
    
    public List<SelectItem> getItensMarca() {
        if (itensMarca == null) {
            itensMarca = new LinkedList<>();
            itensMarca.add( new SelectItem(null,"Selecione a marca"));
            for (Marca m : marcaDAO.listar()) {
                itensMarca.add(new SelectItem(m, m.getDescricao()));
            }
        }
        return itensMarca;
    }   

    public Marca getMarcaSelecionada() {
        return marcaSelecionada;
    }

    public void setMarcaSelecionada(Marca marcaSelecionada) {
        this.marcaSelecionada = marcaSelecionada;
        if (modelos != null) {
            modelos.clear();
            modelos = null;
        }
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }
    
    public String inserirMarca() {
        Marca nova = new Marca( );
        nova.setDescricao( nomeMarca );
        marcaDAO.inserir( nova );
        nomeMarca = null;
        itensMarca.clear();
        itensMarca = null;
        return null;
    }
    
    public List<Modelo> getModelos() {
        if (modelos == null) {
            if (marcaSelecionada != null) {
                modelos = modeloDAO.filtarPorMarca(marcaSelecionada);
            }
        }
        return modelos;
    }

    public String getNomeModelo() {
        return nomeModelo;
    }

    public void setNomeModelo(String nomeModelo) {
        this.nomeModelo = nomeModelo;
    }
    
    public String inserirModelo() {
        if (marcaSelecionada != null) {
            Modelo mod = new Modelo();
            mod.setMarca( marcaSelecionada );
            mod.setNome( nomeModelo );
            modeloDAO.inserir( mod );
            nomeModelo = null;
            modelos.add( mod );
        }
        return null;
    }
}
