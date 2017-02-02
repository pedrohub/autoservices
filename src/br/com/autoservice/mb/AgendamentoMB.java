package br.com.autoservice.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import br.com.autoservice.controller.AgendamentoController;
import br.com.autoservice.modelo.Agendamento;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.Veiculo;
import br.com.autoservice.util.Constantes;
import br.com.autoservice.util.DateUtil;

@ManagedBean
@SessionScoped
public class AgendamentoMB implements Serializable{

	private static final long serialVersionUID = 705257259861914374L;
	private AgendamentoController agendamentoController;
	private List<Agendamento> agendamentos;
	private Agendamento agendamento;
	private String retorno;
	private String acaoAgenda;
	private Long idVeiculo;
	private List<Veiculo> veiculos;

	
	@PostConstruct
	public void init() {
		agendamentoController = AgendamentoController.getInstance();
		agendamento = new Agendamento();
		retorno = "";
		
	}
	
	public void limparModal(){
		agendamento = new Agendamento();
		acaoAgenda = Constantes.INSERIR;
		retorno = "";
	}
	
	
	public void calcularRetorno(){
		if(!"".equals(retorno)){
			Date dateAtual = DateUtil.getDate();
			int mes = Integer.valueOf(retorno);
			dateAtual.setMonth(dateAtual.getMonth()+mes);
			agendamento.setVencimento(dateAtual);
		}
	}
	
	public void salvar(){
		
		agendamento.setVeiculo(getVeiculoAgenda(idVeiculo));
		
		if (validarCamposAgenda(agendamento)){
			
			if(acaoAgenda.equals(Constantes.INSERIR)){
				agendamento.setAbertura(DateUtil.getDate());
				agendamento.setStatus(true);
				agendamentoController.salvar(agendamento);
				agendamentos = agendamentoController.listarPorcliente(agendamento.getVeiculo().getCliente());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Agendamento Salvo", ""));
			}
			
		} else {
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informar Campos Obrigatorios", ""));
		}
		
	}
	
	private boolean validarCamposAgenda(Agendamento agendamento){
		
		if(agendamento.getVencimento() == null)
			return false;
		
		if(agendamento.getVeiculo() == null)
			return false;
		
		if(agendamento.getDescricao() == null || agendamento.getDescricao().isEmpty())
			return false;
		
		return true;
	}
	
	private Veiculo getVeiculoAgenda(Long id){
		
		for (Veiculo vei : veiculos) {
			if (vei.getCodVeiculo().longValue() == id.longValue())
				return vei;
		}
		return null;
	}
	
	public Map<String, String> getAgendaPeriodo(){
		Map<String, String> periodos = new LinkedHashMap<String, String>();
		periodos.put("1 Mes", "1");
		periodos.put("2 Meses", "2");
		periodos.put("3 Meses", "3");
		periodos.put("4 Meses", "4");
		periodos.put("5 Meses", "5");
		periodos.put("6 Meses", "6");
		periodos.put("7 Meses", "7");
		periodos.put("8 Meses", "8");
		periodos.put("9 Meses", "9");
		periodos.put("10 Meses", "10");
		periodos.put("11 Meses", "11");
		periodos.put("12 Meses", "12");
		return periodos;
		
	}

	public void findAll(){
		agendamentos = agendamentoController.listaAgendamentos();
	}
	
	public void findByCliente(Cliente cliente){
		agendamentos = agendamentoController.listarPorcliente(cliente);
	}
	

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}


	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public String getRetorno() {
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	public Long getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(Long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
	
	
	
	
}
