package br.com.autoservice.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.autoservice.controller.TipoServicoController;
import br.com.autoservice.modelo.TipoServico;
import br.com.autoservice.util.Constantes;

@ManagedBean
@SessionScoped
public class TipoServicoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -448273945145471935L;
	private List<TipoServico> listaTipos;
	List<TipoServico> listaServFilters;
	private TipoServicoController tipoController;
	private TipoServico tipo;
	private String descricao;
	
	@PostConstruct
	public void init (){
		tipoController = TipoServicoController.getInstance();
		listaTipos = tipoController.getListaTipoServico();
		tipo = new TipoServico();
	}
	
	
	public void salvar(){
		
		if(tipoController.find(this.tipo) == null && !Constantes.VAZIO.equals(this.tipo.getDescricao())){
		
			TipoServico tipoServico = new TipoServico();
			tipoServico.setDescricao(tipo.getDescricao());
			tipoServico.setValor(tipo.getValor());
			
			tipoController.salvar(tipoServico);
			this.tipo.setDescricao(null);
			this.tipo.setValor(null);
			listaTipos = tipoController.getListaTipoServico();
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Success",  "Registro Salvo" ) );
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "",  "Registro ja existe" ) );
		}
	}
	
	
	public void editar() {

		tipoController.editar(tipo);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,
				new FacesMessage("Sucess", "Registro Alterado"));
		listaTipos = tipoController.getListaTipoServico();
	}

	public void deletar(TipoServico tipo) {
		tipoController.deletar(tipo);
		listaTipos = tipoController.getListaTipoServico();
	}

	public void filterlistDesc(){
		
		List<TipoServico> listaRetorno = new ArrayList<TipoServico>();
		for (TipoServico item : listaTipos) {
			if(descricao.equals(item.getDescricao())){
				listaRetorno.add(item);
			}
		}
		
		if(!listaRetorno.isEmpty()){
			listaServFilters = listaRetorno;
		}
	}
	
	public void limparFiltros(){
		descricao = "";
		if ((listaServFilters != null) && (!listaServFilters.isEmpty())){
			listaServFilters.clear();
			listaServFilters.addAll(tipoController.getListaTipoServico()) ;
		}
	}

	public List<TipoServico> getListaTipos() {
		return listaTipos;
	}


	public void setListaTipos(List<TipoServico> listaTipos) {
		this.listaTipos = listaTipos;
	}


	public TipoServico getTipo() {
		return tipo;
	}


	public void setTipo(TipoServico tipo) {
		this.tipo = tipo;
	}


	public List<TipoServico> getListaServFilters() {
		return listaServFilters;
	}


	public void setListaServFilters(List<TipoServico> listaServFilters) {
		this.listaServFilters = listaServFilters;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
