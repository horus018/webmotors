
package beans;

import dao.ModeloDAO;
import dao.VeiculoDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import model.Marca;
import model.Modelo;
import model.Veiculo;


@Named(value = "buscaBean")
@SessionScoped
public class BuscaBean implements Serializable {

    private Marca marcaSelecionada;
    private Modelo modeloSelecionado;
    
    Integer ano;

    Double valor;
    
    @Inject
    ModeloDAO modeloDAO;
    
    @Inject
    VeiculoDAO veiculoDAO;
    
    List<Veiculo> veiculos = new LinkedList<>();
    
    private LinkedList<SelectItem> itensModelo;
    
    public BuscaBean() {
    }

    @PostConstruct
    public void iniciar() {
        itensModelo = new LinkedList<SelectItem>();
        itensModelo.add(new SelectItem(null,"Selecione a marca primeiro"));
    }
    
    public Marca getMarcaSelecionada() {
        return marcaSelecionada;
    }

    public void setMarcaSelecionada(Marca marcaSelecionada) {
        this.marcaSelecionada = marcaSelecionada;
        itensModelo.clear();
        if (marcaSelecionada == null) {
            itensModelo.add(new SelectItem(null,"Selecione a marca primeiro"));
        } else {
            itensModelo.add(new SelectItem(null,"Modelo..."));
            for (Modelo m : modeloDAO.filtarPorMarca( marcaSelecionada ) ) {
                itensModelo.add( new SelectItem(m, m.getNome() ) );
            }
        }
    }

    public LinkedList<SelectItem> getItensModelo() {
        return itensModelo;
    }

    
    public Modelo getModeloSelecionado() {
        return modeloSelecionado;
    }

    public void setModeloSelecionado(Modelo modeloSelecionado) {
        this.modeloSelecionado = modeloSelecionado;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    public String filtrar() {
        veiculos = veiculoDAO.filtrarPorModelo(modeloSelecionado);        
        return null;
    }
    
    public List<Veiculo> getVeiculosFiltrados() {
        return veiculos;
    }
}
