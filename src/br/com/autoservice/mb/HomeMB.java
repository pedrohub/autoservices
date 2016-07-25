package br.com.autoservice.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.autoservice.controller.ClienteController;
import br.com.autoservice.modelo.Cliente;

@ManagedBean
@ViewScoped
public class HomeMB {

	private Cliente cliente;
	private ClienteController controladorCliente;
	private String placa;
	private boolean painelCliente;
	private boolean painelMensagem;
	private String mensagemConsulta = "Não existe cliente com esta placa";
	
	@PostConstruct
	public void init() {
		controladorCliente = controladorCliente.getInstance();
		painelCliente = false;
		painelMensagem = false;
	}
	
	public void findCliente(){
		cliente  = controladorCliente.findByPlaca(placa);
		
		if (cliente == null) {
			painelMensagem = true;
			painelCliente = false;
		} else {
			painelCliente = true;
			painelMensagem = false;
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public boolean isPainelCliente() {
		return painelCliente;
	}

	public void setPainelCliente(boolean painelCliente) {
		this.painelCliente = painelCliente;
	}

	public boolean isPainelMensagem() {
		return painelMensagem;
	}

	public void setPainelMensagem(boolean painelMensagem) {
		this.painelMensagem = painelMensagem;
	}

	public String getMensagemConsulta() {
		return mensagemConsulta;
	}

	public void setMensagemConsulta(String mensagemConsulta) {
		this.mensagemConsulta = mensagemConsulta;
	}
	
	
	
}
