package br.com.autoservice.controller;

import java.io.Serializable;
import java.util.List;

import br.com.autoservice.dao.AgendamentoDao;
import br.com.autoservice.modelo.Agendamento;

public class AgendamentoController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7167510669050634685L;
	private AgendamentoDao dao = new AgendamentoDao();
	private static AgendamentoController uniqueInstance;
	
	
	public AgendamentoController() {

	}
	
	public static synchronized AgendamentoController getInstance() { 
		if (uniqueInstance == null) uniqueInstance = new AgendamentoController(); 
		return uniqueInstance;
	}

	public List<Agendamento> listaAgendamentos(){
		return dao.findAll();
	}
	
	

}
