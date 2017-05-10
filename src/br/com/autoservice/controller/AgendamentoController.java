package br.com.autoservice.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.autoservice.dao.AgendamentoDao;
import br.com.autoservice.dao.VeiculoDao;
import br.com.autoservice.modelo.Agendamento;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.Veiculo;

public class AgendamentoController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7167510669050634685L;
	private AgendamentoDao dao = new AgendamentoDao();
	private VeiculoDao daoVeiculo = new VeiculoDao();
	private static AgendamentoController uniqueInstance;
	
	
	public AgendamentoController() {

	}
	
	public static synchronized AgendamentoController getInstance() { 
		if (uniqueInstance == null) uniqueInstance = new AgendamentoController(); 
		return uniqueInstance;
	}

	public List<Agendamento> listaAgendamentos(){
		return dao.findAllStatus();
	}
	
	public void salvar(Agendamento agendamento){
		dao.inserir(agendamento);
	}
	
	public List<Agendamento> listarPorcliente(Cliente cliente){
		List<Agendamento> lista = new ArrayList<Agendamento>();
		
		List<Veiculo> veiculos = daoVeiculo.listar(cliente);
		
		for (Veiculo veiculo : veiculos) {
			
			List<Veiculo> listaVeiculo = dao.findByVeiculo(veiculo);
			
			if (listaVeiculo.size() > 0)
				lista.addAll(veiculo.getAgendamentos());
		}
		return lista;
	}
	
	

}
