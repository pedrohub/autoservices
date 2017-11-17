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

import br.com.autoservice.controller.OrcamentoController;
import br.com.autoservice.modelo.ItemOrcamento;
import br.com.autoservice.modelo.Orcamento;
import br.com.autoservice.modelo.Peca;
import br.com.autoservice.modelo.TipoServico;

@ManagedBean
@SessionScoped
public class OrcamentoMB implements Serializable{

	private static final long serialVersionUID = -2573197656673380989L;
	
	private Orcamento os;
	private static OrcamentoController oSController;
	private ItemOrcamento item;
	private List<ItemOrcamento> itens;
	private Integer quantidade;
	@ManagedProperty(value="#{pecaMB}")
	private PecaMB pecaMB;
	@ManagedProperty(value="#{tipoServicoMB}")
	private TipoServicoMB tipoMB;
	@ManagedProperty(value="#{printMB}")
	private PrintMB printMB;
	private int quantidadeEstoque;
	private List<Orcamento> orcamentos;
	private boolean desabilitarBotao;
	private int listaSize;
	
	
	@PostConstruct
	public void init() {
		oSController = OrcamentoController.getInstance();
		itens = new ArrayList<ItemOrcamento>();
		item = new ItemOrcamento();
		orcamentos = new ArrayList<Orcamento>();
	}

	public void salvar(){
		
		if (validateOs(os)) {
			try{
				os.setItens(itens);
				oSController.salvar(os);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Os Salva"));
			} catch(Exception e){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique o preenchimento da ordem", ""));
		}
	}
	
	public void deletar(Orcamento os){
		oSController.deletar(os);
		carregarListaOrcamentos();
	}
	
	public void pecaToItem(Peca peca){
		quantidade = 1;
		quantidadeEstoque = peca.getQtd();
		item = new ItemOrcamento();
		item.setDescricao(peca.getDescricao());
		item.setValorUnitario(peca.getValor());
		
	}
	
	public void pecaToService(TipoServico tipo){
		
		item = new ItemOrcamento();
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
		for (ItemOrcamento itemServico : itens) {
			valor += itemServico.getValor();
		}
		os.setValor(valor);
	}
	
	private boolean validateOs(Orcamento os){
		
//		if (itens == null || itens.isEmpty())
//			return false;
		
		if (os.getModelo() == null || os.getModelo().length() < 1)
			return false;
		
		return true;
	}
	
	
	public List<ItemOrcamento> getItensOs(Orcamento os){
		
		 List<ItemOrcamento> lista = oSController.getItens(os);
		 
		 return lista != null ? lista : new ArrayList<ItemOrcamento>();
	}
	
	public void redirectOrcamento(){
		os = new Orcamento();
		os.setAbertura(new Date());
		itens.clear();
		desabilitarBotao = false;
	}
	
	public void redirectAlter(Orcamento os){
		this.os = os;
		if(!os.getItens().isEmpty())
			itens = os.getItens();
		desabilitarBotao = true;
	}
	
	public void carregarListaOrcamentos(){
		orcamentos = oSController.listaOS();
		listaSize = orcamentos.size();
	}
	
	/**
	 * imprimir OS
	 */
	public void createPdfOS(){
		printMB.setOrcamento(os);
		printMB.createPdfOrcamento();
	}

	// get e set **************************************************
	public Orcamento getOs() {
		return os;
	}

	public void setOs(Orcamento os) {
		this.os = os;
	}

	public ItemOrcamento getItem() {
		return item;
	}

	public void setItem(ItemOrcamento item) {
		this.item = item;
	}

	public List<ItemOrcamento> getItens() {
		return itens;
	}

	public void setItens(List<ItemOrcamento> itens) {
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

	public TipoServicoMB getTipoMB() {
		return tipoMB;
	}

	public void setTipoMB(TipoServicoMB tipoMB) {
		this.tipoMB = tipoMB;
	}

	public PrintMB getPrintMB() {
		return printMB;
	}

	public void setPrintMB(PrintMB printMB) {
		this.printMB = printMB;
	}

	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public List<Orcamento> getOrcamentos() {
		return orcamentos;
	}

	public void setOrcamentos(List<Orcamento> orcamentos) {
		this.orcamentos = orcamentos;
	}

	public boolean isDesabilitarBotao() {
		return desabilitarBotao;
	}

	public void setDesabilitarBotao(boolean desabilitarBotao) {
		this.desabilitarBotao = desabilitarBotao;
	}

	public int getListaSize() {
		return listaSize;
	}

	public void setListaSize(int listaSize) {
		this.listaSize = listaSize;
	}

	
	
}
