package br.com.autoservice.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.autoservice.controller.ClienteController;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.Veiculo;

@ManagedBean
@SessionScoped
public class GeralMB implements Serializable{
	
	private static final long serialVersionUID = 689572449911905267L;
	private Cliente cliente;
	private ClienteController controladorCliente;
	private List<Veiculo> veiculos;
	
	
	@PostConstruct
	public void init() {
		controladorCliente = controladorCliente.getInstance();
	}
	
	public void carregarInformacoes(Cliente cliente){
		
		this.cliente = cliente;
		
		if (cliente.getVeiculos() != null)
			veiculos = cliente.getVeiculos();
		
		
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
	
	


}
