package br.com.autoservice.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.autoservice.dao.ItemOrcamentoDao;
import br.com.autoservice.dao.OrcamentoDao;
import br.com.autoservice.modelo.ItemOrcamento;
import br.com.autoservice.modelo.Orcamento;

public class OrcamentoController implements Serializable{

	
	private static final long serialVersionUID = 3603828650464877293L;
	private static OrcamentoController instance;
	private OrcamentoDao dao = new OrcamentoDao();
	private ItemOrcamentoDao itemDao = new ItemOrcamentoDao();
	
	private OrcamentoController(){
		
	}
	
	public static OrcamentoController getInstance(){
		if (instance == null){
			instance = new OrcamentoController();
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<Orcamento> listaOS(){
		return dao.listarOrcamentos();
	}
	
	public void salvar(Orcamento os){
		dao.saveOrUpdate(os);
		
		List<ItemOrcamento> itens = new ArrayList<ItemOrcamento>();
		
		itemDao.removeByOS(os);
		if (os.getItens() != null && !os.getItens().isEmpty()){
			itens = toNewItens(os.getItens());
		}
		
		for (ItemOrcamento itemServico : itens) {
			itemServico.setOs(os);
			itemDao.inserir(itemServico);
		}
	}
	
	public void deletar(Orcamento os){
		itemDao.removeByOS(os);
		dao.excluir(os);
	}
	
	private List<ItemOrcamento> toNewItens(List<ItemOrcamento> itens){
		
		List<ItemOrcamento> listaNew = new ArrayList<ItemOrcamento>();
		for (ItemOrcamento itemServico : itens) {
			ItemOrcamento item = new ItemOrcamento();
			item.setDescricao(itemServico.getDescricao());
			item.setQuantidade(itemServico.getQuantidade());
			item.setValor(itemServico.getValor());
			item.setValorUnitario(itemServico.getValorUnitario());
			item.setOs(itemServico.getOs());
			listaNew.add(item);
		}
		return listaNew;
	}
	
	
	public List<ItemOrcamento> getItens(Orcamento os){
		return itemDao.listarByOS(os);
	}

}
