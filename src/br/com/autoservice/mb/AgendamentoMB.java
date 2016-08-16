package br.com.autoservice.mb;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.autoservice.controller.AgendamentoController;
import br.com.autoservice.modelo.Agendamento;

@ManagedBean
@ViewScoped
public class AgendamentoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 705257259861914374L;
	private AgendamentoController agendamentoController;
	private List<Agendamento> agendamentos;

	
	@PostConstruct
	public void init() {
		agendamentoController = agendamentoController.getInstance();
	}

	public void findAll(){
		agendamentos = agendamentoController.listaAgendamentos();
	}
	

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}


	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}
	
	
	
}
