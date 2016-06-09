package br.com.autoservice.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.autoservice.controller.MarcaController;
import br.com.autoservice.modelo.Marca;

/**
 * @author binha
 *
 */
@ManagedBean
@ViewScoped
public class MarcaMB {
	
	private List<Marca> listaMarcas;
	
	@PostConstruct
	public void init (){
		listaMarcas = MarcaController.getInstance().getListaMarca();
	}

	public List<Marca> getListaMarcas() {
		return listaMarcas;
	}

	public void setListaMarcas(List<Marca> listaMarcas) {
		this.listaMarcas = listaMarcas;
	}
	
	

}
