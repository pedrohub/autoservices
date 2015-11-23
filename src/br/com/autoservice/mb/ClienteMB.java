package br.com.autoservice.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.autoservice.controller.ControladorCliente;
import br.com.autoservice.controller.ControladorVeiculo;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.Endereco;
import br.com.autoservice.modelo.Veiculo;

@ManagedBean
public class ClienteMB {

	private ControladorCliente controladorCliente;
	private ControladorVeiculo controladorVeiculo;
	private Cliente cliente;
	private Endereco endereco;
	private Veiculo veiculo;
	private boolean renderPainelVeiculo;

	@PostConstruct
	public void init() {
		controladorCliente = new ControladorCliente();
		cliente = new Cliente();
		endereco = new Endereco();
		veiculo = new Veiculo();
		renderPainelVeiculo = true;
		controladorVeiculo = new ControladorVeiculo();
	}

	public void salvar() {

		cliente.setEndereco(endereco);
		cliente.setStatus(true);

		try {
			controladorCliente.inserir(cliente);
			renderPainelVeiculo = true;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Cliente Salvo"));
		} catch (Exception e) {
			renderPainelVeiculo = false;
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro ao Salvar", "Cliente"));
		}
	}
	
	
	public void salvarVeiculo(Cliente cliente) {

		veiculo.setCliente(cliente);

		try {
			controladorVeiculo.inserir(veiculo);
			renderPainelVeiculo = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Veículo Salvo"));
		} catch (Exception e) {
			renderPainelVeiculo = false;
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro ao Salvar", "Veículo"));
		}
	}

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
	

}
