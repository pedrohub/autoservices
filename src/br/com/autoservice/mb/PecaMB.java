package br.com.autoservice.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.autoservice.controller.PecaController;
import br.com.autoservice.modelo.Peca;
import br.com.autoservice.util.Constantes;

@ManagedBean
@ViewScoped
public class PecaMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7763748530287660122L;
	private List<Peca> listaPecas;
	private PecaController pecaController;
	private Peca peca;
	
	@PostConstruct
	public void init (){
		pecaController = PecaController.getInstance();
		listaPecas = pecaController.getLista();
		peca = new Peca();
	}
	
	
	public void salvar(){
		
		if(pecaController.find(this.peca) == null && !Constantes.VAZIO.equals(this.peca.getDescricao())){
		
			Peca tipoServico = new Peca();
			tipoServico.setDescricao(peca.getDescricao());
			
			pecaController.salvar(tipoServico);
			this.peca.setDescricao(null);
			listaPecas = pecaController.getLista();
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Success",  "Registro Salvo" ) );
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "",  "Registro ja existe" ) );
		}
	}
	
	
	public void editar() {

		pecaController.editar(peca);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,
				new FacesMessage("Sucess", "Registro Alterado"));
		listaPecas = pecaController.getLista();
	}

	public void deletar(Peca tipo) {
		pecaController.deletar(tipo);
		listaPecas = pecaController.getLista();
	}


	public List<Peca> getListaPecas() {
		return listaPecas;
	}


	public void setListaPecas(List<Peca> listaPecas) {
		this.listaPecas = listaPecas;
	}


	public Peca getPeca() {
		return peca;
	}


	public void setPeca(Peca peca) {
		this.peca = peca;
	}
	
	
	
	
}
