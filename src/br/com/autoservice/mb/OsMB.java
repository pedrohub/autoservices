package br.com.autoservice.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.autoservice.controller.OSController;
import br.com.autoservice.modelo.ItemServico;
import br.com.autoservice.modelo.OS;
import br.com.autoservice.modelo.Peca;
import br.com.autoservice.modelo.TipoServico;
import br.com.autoservice.util.StatusOS;

@ManagedBean
@SessionScoped
public class OsMB implements Serializable{

	private static final long serialVersionUID = -7697103019906115702L;
	private OS os;
	private static OSController oSController;
	private ItemServico item;
	private List<ItemServico> itens;
	private Integer quantidade;
	@ManagedProperty(value="#{pecaMB}")
	private PecaMB pecaMB;
	@ManagedProperty(value="#{tipoServicoMB}")
	private TipoServicoMB tipoMB;
	@ManagedProperty(value="#{printMB}")
	private PrintMB printMB;
	private int quantidadeEstoque;
	private boolean disableButons;
	
	@PostConstruct
	public void init() {
		oSController = OSController.getInstance();
		itens = new ArrayList<ItemServico>();
		item = new ItemServico();
	}

	public void salvar(){
		
		if (validateOs(os)) {
			try{
				os.setItens(itens);
				countValor();
				oSController.salvar(os);
				if(os.getItens() != null && !os.getItens().isEmpty()){
					itens = getItensOs(os);
				}
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Os Salva"));
			} catch(Exception e){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique o preenchimento da ordem", ""));
		}
	}
	
	public void deletar(OS os){
		oSController.deletar(os);
	}
	
	public void pecaToItem(Peca peca){
		quantidade = 1;
		quantidadeEstoque = peca.getQtd();
		item = new ItemServico();
		item.setDescricao(peca.getDescricao());
		item.setValorUnitario(peca.getValor());
		
	}
	
	public void pecaToService(TipoServico tipo){
		
		item = new ItemServico();
		item.setDescricao(tipo.getDescricao());
		item.setValor(tipo.getValor());
		item.setQuantidade(1);
		item.setValorUnitario(tipo.getValor());
		
	}
	
	public void addItemPeca(){
		
		//if((quantidadeEstoque > 0) && (quantidadeEstoque - quantidade >= 0)){
			item.setValor(item.getValorUnitario()* quantidade);
			item.setQuantidade(quantidade);
			itens.add(item);
			countValor();
			pecaMB.limparFiltros();
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Inserido", ""));
		//} else {
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique o estoque do item", ""));
		//}
	}
	
	public void addItemServico(){
		if(item.getValor() > 0){
			item.setValorUnitario(item.getValor());
			itens.add(item);
			countValor();
			tipoMB.limparFiltros();
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Inserido", ""));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique o valor do item", ""));
		}
	}
	
	public void removeItem(int indice){
		itens.remove(indice);
		countValor();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,new FacesMessage("Sucess", "Registro Removido"));
	}
	
	private void countValor(){
		double valor = 0;
		for (ItemServico itemServico : itens) {
			valor += itemServico.getValor();
		}
		os.setValor(valor);
		os.setTotal(valor-os.getDesconto());
	}
	
	private boolean validateOs(OS os){
		
//		if (itens == null || itens.isEmpty())
//			return false;
		
		if (os.getKm() == null || os.getKm().length() < 1)
			return false;
		
		return true;
	}
	
	public List<OS> listbyClient(Long idCliente){
		
		return oSController.listarPorcliente(idCliente);
	}
	
	public List<ItemServico> getItensOs(OS os){
		
		 List<ItemServico> lista = oSController.getItens(os);
		 
		 return lista != null ? lista : new ArrayList<ItemServico>();
	}
	
	/**
	 * Fechar a OS
	 */
	public void fecharOS(){
		
		if(validateOs(os) && !os.getItens().isEmpty()){
			os.setFechamento(new Date());
			os.setStatus(StatusOS.FINALIZADA);
			oSController.salvar(os);
			disableButons = true;
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "OS nao pode ser fechada", ""));
		}
	}
	
	/**
	 * imprimir OS
	 */
	public void createPdfOS(){
		OS osPrint = oSController.getOSById(os);
		osPrint.setItens(getItensOs(os));
		printMB.setOs(osPrint);
		printMB.createPdfOS();
	}
	
	// get e set **************************************************
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public PecaMB getPecaMB() {
		return pecaMB;
	}

	public void setPecaMB(PecaMB pecaMB) {
		this.pecaMB = pecaMB;
	}

	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public TipoServicoMB getTipoMB() {
		return tipoMB;
	}

	public void setTipoMB(TipoServicoMB tipoMB) {
		this.tipoMB = tipoMB;
	}

	public boolean isDisableButons() {
		return disableButons;
	}

	public void setDisableButons(boolean disableButons) {
		this.disableButons = disableButons;
	}

	public PrintMB getPrintMB() {
		return printMB;
	}

	public void setPrintMB(PrintMB printMB) {
		this.printMB = printMB;
	}

}
