package br.com.autoservice.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.autoservice.dao.OSDao;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.OS;
import br.com.autoservice.modelo.Peca;

public class OSController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 325180659114136882L;
	
	private static OSController instance;
	private OSDao dao = new OSDao();
	
	private OSController(){
		
	}
	
	public static OSController getInstance(){
		if (instance == null){
			instance = new OSController();
		}
		return instance;
	}
	
	public List<OS> listaOS(){
		return dao.listar(OS.class);
	}
	
	public void salvar(OS os){
		dao.inserir(os);
		
	}
	
//	public OS find(OS tipo){
//		return dao.find(tipo);
//	}
	
	public List<OS> listarPorcliente(Cliente cliente){
		List<OS> lista = new ArrayList<OS>();
		
//		for (Veiculo veiculo : cliente.getVeiculos()) {
//			
//			if (dao.findByVeiculo(veiculo).size() > 0)
//				lista.addAll(veiculo.getAgendamentos());
//		}
		return lista;
	}

}
