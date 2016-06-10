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
	private MarcaController marcaController;
	private Marca marca;
	
	@PostConstruct
	public void init (){
		marcaController = MarcaController.getInstance();
		listaMarcas = marcaController.getListaMarca();
		marca = new Marca();
	}
	
	/**
	 * salvar Marca
	 * @param marca
	 */
	public void salvar(Marca marca){
		marcaController.salvar(marca);
	}

	public List<Marca> getListaMarcas() {
		return listaMarcas;
	}

	public void setListaMarcas(List<Marca> listaMarcas) {
		this.listaMarcas = listaMarcas;
	}
	
	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	
}
