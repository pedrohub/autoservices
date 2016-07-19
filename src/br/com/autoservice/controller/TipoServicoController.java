package br.com.autoservice.controller;

import java.util.List;

import br.com.autoservice.dao.TipoServicoDao;
import br.com.autoservice.modelo.TipoServico;

public class TipoServicoController {

	
	private static TipoServicoController tipoController;
	private TipoServicoDao dao;
	
	private TipoServicoController(){
		dao = new TipoServicoDao();
	}
	
	public static TipoServicoController getInstance() { 
		if (tipoController == null) {
			tipoController = new TipoServicoController(); 
		}
		return tipoController;
	}
	
	
	public TipoServico find(TipoServico tipo){
		return dao.find(tipo);
	}
	
	public List<TipoServico> getListaTipoServico(){
		return dao.listar();
	}

	public void salvar(TipoServico tipo){
		dao.inserir(tipo);
	}
	
	public void deletar(TipoServico tipo){
		dao.excluir(tipo);
	}
	
	public void editar(TipoServico tipo){
		dao.alterar(tipo);
	}
	
}
