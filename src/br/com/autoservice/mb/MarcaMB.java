package br.com.autoservice.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
	public void salvar(){
		
		if(marcaController.find(this.marca) == null){
		
			Marca marca = new Marca();
			marca.setMarca(this.marca.getMarca());
			marcaController.salvar(marca);
			listaMarcas = marcaController.getListaMarca();
			this.marca.setMarca(null);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Success",  "Registro Salvo" ) );
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "",  "Registro ja existe" ) );
		}
	}
	
	/**
	 * editar Marca
	 * @param marca
	 */
	public void editar(){
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucess",  "Registro salvo" ) );
		listaMarcas = marcaController.getListaMarca();
	}
	
	/**
	 * salvar Marca
	 * @param marca
	 */
	public void deletar(Marca marca){
		marcaController.deletar(marca);
		listaMarcas = marcaController.getListaMarca();
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
