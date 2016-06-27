package br.com.autoservice.controller;

import java.util.List;
import br.com.autoservice.dao.MarcaDao;
import br.com.autoservice.modelo.Marca;

/**
 * @author robson.carlos.santos
 *
 * Controller Marcas
 */
public class MarcaController {

	private static MarcaController marcaController;
	private MarcaDao dao;
	
	private MarcaController(){
		dao = new MarcaDao();
	}
	
	public static MarcaController getInstance() { 
		if (marcaController == null) {
			marcaController = new MarcaController(); 
		}
		return marcaController;
	}
	
	public Marca find(Marca marca){
		return dao.find(marca);
	}
	
	public List<Marca> getListaMarca(){
		return dao.listar();
	}

	public void salvar(Marca marca){
		dao.inserir(marca);
	}
	
	public void deletar(Marca marca){
		dao.excluir(marca);
	}
}
