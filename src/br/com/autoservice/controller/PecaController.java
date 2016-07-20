package br.com.autoservice.controller;

import java.io.Serializable;
import java.util.List;

import br.com.autoservice.dao.PecaDao;
import br.com.autoservice.modelo.Peca;

public class PecaController implements Serializable{

	
	private static final long serialVersionUID = -3894365654965256714L;

	
	private static PecaController pecaController;
	private PecaDao dao;
	
	private PecaController(){
		dao = new PecaDao();
	}
	
	public static PecaController getInstance() { 
		if (pecaController == null) {
			pecaController = new PecaController(); 
		}
		return pecaController;
	}
	
	
	public Peca find(Peca tipo){
		return dao.find(tipo);
	}
	
	public List<Peca> getLista(){
		return dao.listar();
	}

	public void salvar(Peca tipo){
		dao.inserir(tipo);
	}
	
	public void deletar(Peca tipo){
		dao.excluir(tipo);
	}
	
	public void editar(Peca tipo){
		dao.alterar(tipo);
	}
	
}
