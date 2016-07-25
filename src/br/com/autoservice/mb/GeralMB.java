package br.com.autoservice.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import br.com.autoservice.controller.ClienteController;
import br.com.autoservice.controller.MarcaController;
import br.com.autoservice.controller.VeiculoController;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.Endereco;
import br.com.autoservice.modelo.Veiculo;

@ManagedBean
@SessionScoped
public class GeralMB implements Serializable{
	
	private static final long serialVersionUID = 689572449911905267L;
	private Cliente cliente;
	private ClienteController controladorCliente;
	
	
	@PostConstruct
	public void init() {
		controladorCliente = controladorCliente.getInstance();
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


}
