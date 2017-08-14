package br.com.autoservice.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.autoservice.controller.PecaController;
import br.com.autoservice.modelo.Peca;
import br.com.autoservice.util.Constantes;

@ManagedBean
@SessionScoped
public class PecaMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7763748530287660122L;
	private List<Peca> listaPecas;
	private List<Peca> listaPecasFilters;
	private PecaController pecaController;
	private Peca peca;
	private int listSize;
	private String descricao;
	private String tipo;
	private String marca;
	
	@PostConstruct
	public void init (){
		pecaController = PecaController.getInstance();
		listaPecas = pecaController.getLista();
		listSize = listaPecas.size();
		peca = new Peca();
	}
	
	
	public void salvar(){
		
		if(pecaController.find(this.peca) == null && !Constantes.VAZIO.equals(this.peca.getDescricao())){
		
			Peca tipoServico = new Peca();
			tipoServico.setDescricao(peca.getDescricao());
			tipoServico.setQtd(peca.getQtd());
			tipoServico.setQtdMin(peca.getQtdMin());
			tipoServico.setValor(peca.getValor());
			tipoServico.setReferencia(peca.getReferencia());
			tipoServico.setMarca(peca.getMarca());
			tipoServico.setTipo(peca.getTipo());
			
			pecaController.salvar(tipoServico);
			this.peca.setDescricao(null);
			listaPecas = pecaController.getLista();
			listSize = listaPecas.size();
			limparFiltros();
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Success",  "Registro Salvo" ) );
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "",  "Registro ja existe" ) );
		}
	}
	
	
	public void editar() {
		
		if (!Constantes.VAZIO.equals(peca.getDescricao())){
			pecaController.editar(peca);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Sucess", "Registro Alterado"));
			listaPecas = pecaController.getLista();
			listSize = listaPecas.size();
			limparFiltros();
		}
	}

	public void deletar(Peca tipo) {
		pecaController.deletar(tipo);
		listaPecas = pecaController.getLista();
		listSize = listaPecas.size();
		limparFiltros();
		
	}
	
	public void filterlistDesc(){
		
		List<Peca> listaRetorno = new ArrayList<Peca>();
		for (Peca item : listaPecas) {
			if(descricao.equals(item.getDescricao())){
				listaRetorno.add(item);
			}
		}
		
		if(!listaRetorno.isEmpty()){
			listaPecasFilters = listaRetorno;
		}
	}
	
	public void filterlistMarca(){
		
		List<Peca> listaRetorno = new ArrayList<Peca>();
		for (Peca item : listaPecas) {
			if(marca.equals(item.getDescricao())){
				listaRetorno.add(item);
			}
		}
		
		if(!listaRetorno.isEmpty()){
			listaPecasFilters = listaRetorno;
		}
	}
	
	public void filterlistTipo(){
		
		List<Peca> listaRetorno = new ArrayList<Peca>();
		for (Peca item : listaPecas) {
			if(tipo.equals(item.getDescricao())){
				listaRetorno.add(item);
			}
		}
		
		if(!listaRetorno.isEmpty()){
			listaPecasFilters = listaRetorno;
		}
	}
	
	public void limparFiltros(){
		marca = "";
		tipo = "";
		descricao = "";
		listaPecasFilters.clear();
		listaPecasFilters.addAll(pecaController.getLista()) ;
	}
	
	public void limparModal(){
		peca = new Peca();
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


	public int getListSize() {
		return listSize;
	}


	public void setListSize(int listSize) {
		this.listSize = listSize;
	}


	public List<Peca> getListaPecasFilters() {
		return listaPecasFilters;
	}


	public void setListaPecasFilters(List<Peca> listaPecasFilters) {
		this.listaPecasFilters = listaPecasFilters;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	
	
	
}
