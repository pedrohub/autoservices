package br.com.autoservice.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.autoservice.controller.AgendamentoController;
import br.com.autoservice.controller.ClienteController;
import br.com.autoservice.controller.OSController;
import br.com.autoservice.modelo.Agendamento;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.OS;

@ManagedBean
@ViewScoped
public class HomeMB implements Serializable{

	private static final long serialVersionUID = 5831543201389925568L;
	private Cliente cliente;
	private ClienteController controladorCliente;
	private AgendamentoController agendamentoController;
	private OSController osController;
	private String placa;
	private boolean painelCliente;
	private boolean painelMensagem;
	private String mensagemConsulta = "Nao existe cliente com esta placa";
	@ManagedProperty(value="#{geralMB}")
	private GeralMB geralMB;
	private List<Agendamento> agendamentosVencidos = new ArrayList<Agendamento>();
	private List<Agendamento> agendamentosAbertos = new ArrayList<Agendamento>();
	private List<OS> osAbertas = new ArrayList<OS>();
	private int contAgendaVencidos = 0;
	private int contAgendaAbertos = 0;
	private int contOs = 0;
	
	
	@PostConstruct
	public void init() {
		controladorCliente = ClienteController.getInstance();
		agendamentoController = AgendamentoController.getInstance();
		osController = OSController.getInstance();
		painelCliente = false;
		painelMensagem = false;
		agendamentosVencidos = agendamentoController.listarVencidos();
		contAgendaVencidos = agendamentosVencidos.size();
		agendamentosAbertos = agendamentoController.listaAgendamentos();
		contAgendaAbertos = agendamentosAbertos.size();
		osAbertas = osController.listaOSAbertas();
		contOs = osAbertas.size();
	}
	
	public void findCliente(){
		cliente  = controladorCliente.findByPlaca(placa);
		
		if (cliente == null) {
			painelMensagem = true;
			painelCliente = false;
		} else {
			painelCliente = true;
			painelMensagem = false;
		}
	}
	
	public void redirectGeral(){
		geralMB.carregarInformacoes(cliente);
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public boolean isPainelCliente() {
		return painelCliente;
	}

	public void setPainelCliente(boolean painelCliente) {
		this.painelCliente = painelCliente;
	}

	public boolean isPainelMensagem() {
		return painelMensagem;
	}

	public void setPainelMensagem(boolean painelMensagem) {
		this.painelMensagem = painelMensagem;
	}

	public String getMensagemConsulta() {
		return mensagemConsulta;
	}

	public void setMensagemConsulta(String mensagemConsulta) {
		this.mensagemConsulta = mensagemConsulta;
	}

	public GeralMB getGeralMB() {
		return geralMB;
	}

	public void setGeralMB(GeralMB geralMB) {
		this.geralMB = geralMB;
	}

	public List<Agendamento> getAgendamentosVencidos() {
		return agendamentosVencidos;
	}

	public void setAgendamentosVencidos(List<Agendamento> agendamentosVencidos) {
		this.agendamentosVencidos = agendamentosVencidos;
	}

	public List<Agendamento> getAgendamentosAbertos() {
		return agendamentosAbertos;
	}

	public void setAgendamentosAbertos(List<Agendamento> agendamentosAbertos) {
		this.agendamentosAbertos = agendamentosAbertos;
	}

	public List<OS> getOsAbertas() {
		return osAbertas;
	}

	public void setOsAbertas(List<OS> osAbertas) {
		this.osAbertas = osAbertas;
	}

	public int getContAgendaVencidos() {
		return contAgendaVencidos;
	}

	public void setContAgendaVencidos(int contAgendaVencidos) {
		this.contAgendaVencidos = contAgendaVencidos;
	}

	public int getContAgendaAbertos() {
		return contAgendaAbertos;
	}

	public void setContAgendaAbertos(int contAgendaAbertos) {
		this.contAgendaAbertos = contAgendaAbertos;
	}

	public int getContOs() {
		return contOs;
	}

	public void setContOs(int contOs) {
		this.contOs = contOs;
	}

}
