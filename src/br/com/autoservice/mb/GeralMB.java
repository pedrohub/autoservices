package br.com.autoservice.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.autoservice.controller.ClienteController;
import br.com.autoservice.controller.VeiculoController;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.Veiculo;

@ManagedBean
@SessionScoped
public class GeralMB implements Serializable{
	
	private static final long serialVersionUID = 689572449911905267L;
	private Cliente cliente;
	private ClienteController controladorCliente;
	private VeiculoController controladorVeiculo;
	private List<Veiculo> veiculos;
	private Veiculo veiculo;
	private boolean botaoVeiculo;
	
	
	@PostConstruct
	public void init() {
		controladorCliente = controladorCliente.getInstance();
		controladorVeiculo = controladorVeiculo.getInstance();
	}
	
	public void carregarInformacoes(Cliente cliente){
		
		this.cliente = cliente;
		
		if (cliente.getVeiculos() != null)
			veiculos = cliente.getVeiculos();
		
		
	}
	
	/**
	 * Salvar Veiculos
	 */
	public void salvarVeiculo() {
		
		if (veiculo == null)
			veiculo = new Veiculo();
		
		veiculo.setCliente(cliente);
		veiculo.setStatus(true);
		
		if (validarVeiculo(veiculo)){
			
			if (controladorVeiculo.find(veiculo) == null) {
				try{
					veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
					controladorVeiculo.inserir(veiculo);
				//	botaoVeiculo = true;
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veiculo Salvo"));
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Salvar", "Veiculo"));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Veiculo ja cadastrado", ""));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informar Campos Obrigatorios", ""));
		}
	}
	
	/**
	 * validar campos do veiculo
	 * @param veiculo
	 * @return
	 */
	private boolean validarVeiculo(Veiculo veiculo){
		if ((veiculo.getModelo() != null && !veiculo.getModelo().isEmpty()) &&
				veiculo.getPlaca() != null && !veiculo.getPlaca().isEmpty()){
			return true;
		} else {
			return false;
		}
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<String> getEstados(){
		return ClienteController.getInstance().getEstados();
	}


	public List<Veiculo> getVeiculos() {
		return veiculos;
	}


	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public boolean isBotaoVeiculo() {
		return botaoVeiculo;
	}

	public void setBotaoVeiculo(boolean botaoVeiculo) {
		this.botaoVeiculo = botaoVeiculo;
	}
	
	
		

}
