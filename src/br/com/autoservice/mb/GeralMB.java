package br.com.autoservice.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.autoservice.controller.ClienteController;
import br.com.autoservice.controller.MarcaController;
import br.com.autoservice.controller.VeiculoController;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.Endereco;
import br.com.autoservice.modelo.Veiculo;

@ManagedBean
@ViewScoped
public class GeralMB implements Serializable{
	
	private static final long serialVersionUID = 689572449911905267L;
	private Cliente cliente;
	private ClienteController controladorCliente;
	
	
	@PostConstruct
	public void init() {
		controladorCliente = controladorCliente.getInstance();
		cliente = new Cliente();
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


}
