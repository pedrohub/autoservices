package br.com.autoservice.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.autoservice.controller.AgendamentoController;
import br.com.autoservice.controller.ClienteController;
import br.com.autoservice.controller.VeiculoController;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.OS;
import br.com.autoservice.modelo.Veiculo;
import br.com.autoservice.util.Constantes;
import br.com.autoservice.util.StatusOS;

@ManagedBean
@SessionScoped
public class GeralMB implements Serializable{
	
	private static final long serialVersionUID = 689572449911905267L;
	private Cliente cliente;
	private ClienteController controladorCliente;
	private VeiculoController controladorVeiculo;
	private List<Veiculo> veiculos;
	private Veiculo veiculo;
	private boolean botaoVeiculo;
	private String acaoVeiculo;
	private AgendamentoController agendamentoController;
	@ManagedProperty(value="#{agendamentoMB}")
	private AgendamentoMB agendamentoMB;
	@ManagedProperty(value="#{osMB}")
	private OsMB osMB;
	private List<OS> listaOS;
	
	
	@PostConstruct
	public void init() {
		controladorCliente = ClienteController.getInstance();
		controladorVeiculo = VeiculoController.getInstance();
		agendamentoController = AgendamentoController.getInstance();
		veiculo = new Veiculo();
		listaOS = new ArrayList<OS>();
	}
	
	public void carregarInformacoes(Cliente cliente){
		
		if(cliente == null) {
			cliente = this.cliente;
		} else {
			this.cliente = cliente;
		}
		
		if (cliente.getVeiculos() != null)
			veiculos = controladorVeiculo.listar(cliente);
		
		agendamentoMB.setAgendamentos(agendamentoController.listarPorcliente(cliente));
		carregarOS();
	}
	
	/**
	 * Salvar Veiculos
	 */
	public void salvarVeiculo() {
		
		veiculo.setCliente(cliente);
		veiculo.setStatus(true);
		
		if (validarVeiculo(veiculo)){
			
			if (acaoVeiculo.equals(Constantes.INSERIR)){
				if (controladorVeiculo.find(veiculo) == null) {
					try{
						veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
						controladorVeiculo.inserir(veiculo);
						botaoVeiculo = true;
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veiculo Salvo"));
						veiculos = VeiculoController.getInstance().listar(cliente);
					} catch (Exception e) {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Salvar", "Veiculo"));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Veiculo ja cadastrado", ""));
				}
			} else if (acaoVeiculo.equals(Constantes.ALTERAR)){// fluxo alterar
				try{
					veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
					controladorVeiculo.alterar(veiculo);
					botaoVeiculo = true;
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veiculo Salvo"));
					veiculos = VeiculoController.getInstance().listar(cliente);
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Salvar", "Veiculo"));
				}
			}
		} else {
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informar Campos Obrigatorios", ""));
		}
	}
	
	/**
	 * validar campos do veiculo
	 * @param veiculo
	 * @return
	 */
	private boolean validarVeiculo(Veiculo veiculo){
		if ((veiculo.getModelo() != null && !veiculo.getModelo().isEmpty()) &&
				veiculo.getPlaca() != null && !veiculo.getPlaca().isEmpty()){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * limpar modal de veiculos
	 */
	public void limparModalVeiculo (){
		this.veiculo = new Veiculo();
		botaoVeiculo  = false;
		acaoVeiculo = Constantes.INSERIR;
	}
	
	public void habilitarModalVeiculo(){
		botaoVeiculo = false;
		acaoVeiculo = Constantes.ALTERAR;
	}
	
	/**
	 * deletar veiculo
	 * @param cliente
	 */
	public void deletarVeiculo(Veiculo veiculo){
		controladorVeiculo.excluir(veiculo);
		veiculos = VeiculoController.getInstance().listar(cliente);
	}
	
	/**
	 * Alterar Cliente
	 */
	public void alterarCliente() {
		
		if (validarCliente(cliente)) {
			try {
				controladorCliente.alterar(cliente);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Salvo"));
				
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Salvar", "Cliente"));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos Obrigatorios nao preenchidos: Nome, Fone 1 e Bairro", ""));
		}
	}
	
	/**
	 * validar atributos do cliente
	 * @param cliente
	 * @return
	 */
	private boolean validarCliente(Cliente cliente){
		if ((cliente.getNome() != null && !cliente.getNome().isEmpty()) && 
				(cliente.getFone1()!=null && !cliente.getFone1().isEmpty())
				&& (cliente.getEndereco().getBairro() != null && !cliente.getEndereco().getBairro().isEmpty())){
			return true;
		} else {
			return false;
		}
	}
	
	public void reloadAgendamentos(){
		agendamentoMB.setAgendamentos(agendamentoController.listarPorcliente(cliente));
	}

	public void redirectAgenda(){
		agendamentoMB.setVeiculos(getVeiculos());
		agendamentoMB.limparModal();
	}
	
	public void redirectOs(){
		try {
			OS os = new OS();
			os.gerarOS(cliente, veiculo);
			osMB.setOs(os);
			osMB.getItens().clear();
			osMB.setDisableButons(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("pageOS.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void redirectOsAlter(OS os){
		try {
			osMB.setOs(os);
			osMB.setItens(osMB.getItensOs(os));
			if (os.getStatus().equals(StatusOS.FINALIZADA)){
				osMB.setDisableButons(true);
			}else{
				osMB.setDisableButons(false);
			}
			FacesContext.getCurrentInstance().getExternalContext().redirect("pageOS.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void carregarOS(){
		listaOS = osMB.listbyClient(cliente.getIdCliente());
	}
	
	public void deletarOS(OS os){
		if (!StatusOS.FINALIZADA.equals(os.getStatus())) {
			try{
				osMB.deletar(os);
				listaOS = osMB.listbyClient(cliente.getIdCliente());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Os Removida"));
			} catch(Exception e){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique o status da ordem", ""));
		}
	}

	/**
	 * get e setters
	 * @return
	 */
	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<String> getEstados(){
		return ClienteController.getInstance().getEstados();
	}


	public List<Veiculo> getVeiculos() {
		return veiculos;
	}


	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public boolean isBotaoVeiculo() {
		return botaoVeiculo;
	}

	public void setBotaoVeiculo(boolean botaoVeiculo) {
		this.botaoVeiculo = botaoVeiculo;
	}

	public AgendamentoMB getAgendamentoMB() {
		return agendamentoMB;
	}

	public void setAgendamentoMB(AgendamentoMB agendamentoMB) {
		this.agendamentoMB = agendamentoMB;
	}

	public OsMB getOsMB() {
		return osMB;
	}

	public void setOsMB(OsMB osMB) {
		this.osMB = osMB;
	}

	public List<OS> getListaOS() {
		return listaOS;
	}

	public void setListaOS(List<OS> listaOS) {
		this.listaOS = listaOS;
	}
	
	
	


}
