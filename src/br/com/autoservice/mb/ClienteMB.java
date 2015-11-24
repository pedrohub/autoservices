package br.com.autoservice.mb;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.com.autoservice.controller.ControladorCliente;
import br.com.autoservice.controller.ControladorVeiculo;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.Endereco;
import br.com.autoservice.modelo.Veiculo;

/**
 * @author pedro.edson.o.lima
 *
 */
@ManagedBean
@ViewScoped
public class ClienteMB {

	private ControladorCliente controladorCliente;
	private ControladorVeiculo controladorVeiculo;
	private Cliente cliente;
	private Endereco endereco;
	private Veiculo veiculo;
	private boolean renderPainelVeiculo;
	private boolean botaoCliente;
	private boolean botaoVeiculo;

	@PostConstruct
	public void init() {
		controladorCliente = new ControladorCliente();
		cliente = new Cliente();
		endereco = new Endereco();
		veiculo = new Veiculo();
		renderPainelVeiculo = false;
		controladorVeiculo = new ControladorVeiculo();
		botaoCliente = false;
		botaoVeiculo = false;
	}

	/**
	 * Salvar Cliente
	 */
	public void salvar() {

		cliente.setEndereco(endereco);
		cliente.setStatus(true);

		if (controladorCliente.findByCpf(cliente) == null) {
			try {

				controladorCliente.inserir(cliente);
				renderPainelVeiculo = true;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Cliente Salvo"));
				botaoCliente = true;
			} catch (Exception e) {
				renderPainelVeiculo = false;
				botaoCliente = false;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Erro ao Salvar", "Cliente"));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Cliente Já Existe", ""));
		}
	}

	/**
	 * Salvar Veiculos
	 */
	public void salvarVeiculo() {

		veiculo.setCliente(cliente);
		veiculo.setStatus(true);

		try {
			controladorVeiculo.inserir(veiculo);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Veículo Salvo"));
			botaoVeiculo = true;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro ao Salvar", "Veículo"));
		}
	}
	
	public void adicionarVeiculo(){
		
		veiculo = new Veiculo();
		botaoVeiculo = false;
		
	}
	
	public void limparModal(){
		cliente = new Cliente();
		endereco = new Endereco();
		veiculo = new Veiculo();
		renderPainelVeiculo = false;
		botaoCliente = false;
		botaoVeiculo = false;
	}

	/**
	 * Getters e Setters abaixo
	 * 
	 * @return
	 */
	public List<Cliente> getLista() {
		return controladorCliente.listar();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public boolean isRenderPainelVeiculo() {
		return renderPainelVeiculo;
	}

	public void setRenderPainelVeiculo(boolean renderPainelVeiculo) {
		this.renderPainelVeiculo = renderPainelVeiculo;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public boolean isBotaoCliente() {
		return botaoCliente;
	}

	public void setBotaoCliente(boolean botaoCliente) {
		this.botaoCliente = botaoCliente;
	}

	public boolean isBotaoVeiculo() {
		return botaoVeiculo;
	}

	public void setBotaoVeiculo(boolean botaoVeiculo) {
		this.botaoVeiculo = botaoVeiculo;
	}
	

}
