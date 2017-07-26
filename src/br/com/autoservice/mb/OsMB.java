package br.com.autoservice.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.autoservice.controller.OSController;
import br.com.autoservice.modelo.ItemServico;
import br.com.autoservice.modelo.OS;
import br.com.autoservice.modelo.Peca;

@ManagedBean
@SessionScoped
public class OsMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2573197656673380989L;
	
	private OS os;
	private static OSController oSController;
	private ItemServico item;
	private List<ItemServico> itens;
	
	
	
	@PostConstruct
	public void init() {
		oSController = OSController.getInstance();
		itens = new ArrayList<ItemServico>();
		item = new ItemServico();
	}

	public void salvar(){
		
		if (validateOs(os)) {
			try{
				oSController.salvar(os);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Os Salva"));
			} catch(Exception e){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique o preenchimento da ordem", ""));
		}
	}
	
	public void pecaToItem(Peca peca){
		item.setDescricao(peca.getDescricao());
		item.setValor(peca.getValor());
	}
	
	public void addItemPeca(){
		
		itens.add(item);
	}
	
	private boolean validateOs(OS os){
		return true;
	}
	
	public OS getOs() {
		return os;
	}

	public void setOs(OS os) {
		this.os = os;
	}

	public ItemServico getItem() {
		return item;
	}

	public void setItem(ItemServico item) {
		this.item = item;
	}

	public List<ItemServico> getItens() {
		return itens;
	}

	public void setItens(List<ItemServico> itens) {
		this.itens = itens;
	}
	
	

}
