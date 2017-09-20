package br.com.autoservice.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.autoservice.dao.ItemServicoDao;
import br.com.autoservice.dao.OSDao;
import br.com.autoservice.modelo.ItemServico;
import br.com.autoservice.modelo.OS;

public class OSController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 325180659114136882L;
	
	private static OSController instance;
	private OSDao dao = new OSDao();
	private ItemServicoDao itemDao = new ItemServicoDao();
	
	private OSController(){
		
	}
	
	public static OSController getInstance(){
		if (instance == null){
			instance = new OSController();
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<OS> listaOS(){
		return dao.listar(OS.class);
	}
	
	public void salvar(OS os){
		dao.saveOrUpdate(os);
		
		List<ItemServico> itens = new ArrayList<ItemServico>();
		
		itemDao.removeByOS(os);
		if (os.getItens() != null && !os.getItens().isEmpty()){
			itens = toNewItens(os.getItens());
		}
		
		for (ItemServico itemServico : itens) {
			itemServico.setOs(os);
			itemDao.inserir(itemServico);
		}
	}
	
	public void deletar(OS os){
		itemDao.removeByOS(os);
		dao.excluir(os);
	}
	
	private List<ItemServico> toNewItens(List<ItemServico> itens){
		
		List<ItemServico> listaNew = new ArrayList<ItemServico>();
		for (ItemServico itemServico : itens) {
			ItemServico item = new ItemServico();
			item.setDescricao(itemServico.getDescricao());
			item.setQuantidade(itemServico.getQuantidade());
			item.setValor(itemServico.getValor());
			item.setValorUnitario(itemServico.getValorUnitario());
			item.setOs(itemServico.getOs());
			listaNew.add(item);
		}
		return listaNew;
	}
	
	public List<OS> listarPorcliente(Long idCliente){
		return dao.listarByCliente(idCliente);
	}
	
	public List<ItemServico> getItens(OS os){
		return itemDao.listarByOS(os);
	}

}
