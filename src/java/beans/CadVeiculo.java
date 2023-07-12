package beans;

import dao.VeiculoDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import model.Veiculo;

@Named(value = "cadVeiculo")
@SessionScoped
public class CadVeiculo implements Serializable {
    
    Veiculo veiculo = new Veiculo();
    
    @Inject
    VeiculoDAO veiculoDAO;
    
    public CadVeiculo() {
    }  

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
    public String confirmar() {
        Veiculo v = veiculoDAO.buscarPorPlaca(veiculo.getPlaca());
        if (v != null) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Placa já cadastrada",
                    "Já existe veiculo com esta placa no cadastro"));
        } else {
            veiculoDAO.inserir(veiculo);
            veiculo = new Veiculo();
        }
        return null;
    }
    
}
