package br.com.autoservice.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.autoservice.controller.ClienteController;
import br.com.autoservice.controller.PecaController;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.Peca;

@ManagedBean
@ViewScoped
public class HomeMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5831543201389925568L;
	private Cliente cliente;
	private ClienteController controladorCliente;
	private PecaController pecaController;
	private String placa;
	private boolean painelCliente;
	private boolean painelMensagem;
	private String mensagemConsulta = "Nao existe cliente com esta placa";
	@ManagedProperty(value="#{geralMB}")
	private GeralMB geralMB;
	private List<Peca> pecas = new ArrayList<Peca>();
	
	@PostConstruct
	public void init() {
		controladorCliente = ClienteController.getInstance();
		pecaController = PecaController.getInstance();
		painelCliente = false;
		painelMensagem = false;
		pecas=pecaController.getLista();
		
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

	public void redirectGeral(){
		geralMB.carregarInformacoes(cliente);
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

	public GeralMB getGeralMB() {
		return geralMB;
	}

	public void setGeralMB(GeralMB geralMB) {
		this.geralMB = geralMB;
	}

	public List<Peca> getPecas() {
		return pecas;
	}

	public void setPecas(List<Peca> pecas) {
		this.pecas = pecas;
	}
	
	
	
}
