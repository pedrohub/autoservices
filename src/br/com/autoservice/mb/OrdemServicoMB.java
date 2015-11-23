package br.com.autoservice.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.autoservice.modelo.Cliente;

/**
 * @author Robson Carlos
 * 14/11/2015
 */
@ManagedBean
@SessionScoped
public class OrdemServicoMB {
	
	 private Cliente cliente = new Cliente();
	 
	 public OrdemServicoMB(){
		 cliente.setNome("Rodrigo Batista");
	 }
	 

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	 
	 

}
