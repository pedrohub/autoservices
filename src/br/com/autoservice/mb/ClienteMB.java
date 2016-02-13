package br.com.autoservice.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.autoservice.controller.ClienteController;
import br.com.autoservice.controller.VeiculoController;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.Endereco;
import br.com.autoservice.modelo.Veiculo;
import br.com.autoservice.util.LogUtil;

/**
 * @author pedro.edson.o.lima
 *
 */
@ManagedBean
@ViewScoped
public class ClienteMB implements Serializable{

	private static final long serialVersionUID = -2814761488716950745L;
	private ClienteController controladorCliente;
	private VeiculoController controladorVeiculo;
	private Cliente cliente;
	private Endereco endereco;
	private Veiculo veiculo;
	private List<Veiculo> listaVeiculo;
	private boolean renderPainelVeiculo;
	private boolean botaoCliente;
	private boolean botaoVeiculo;
	private List<Cliente> listaClientes;
	private Cliente clienteSelected;
	private String tipoConsulta;
	private org.apache.log4j.Logger logger = LogUtil.logger.getLogger(ClienteMB.class);

	@PostConstruct
	public void init() {
		controladorCliente = controladorCliente.getInstance();
		cliente = new Cliente();
		endereco = new Endereco();
		veiculo = new Veiculo();
		listaVeiculo = new ArrayList<Veiculo>();
		renderPainelVeiculo = false;
		controladorVeiculo = new VeiculoController();
		botaoCliente = false;
		botaoVeiculo = false;
		listaClientes = controladorCliente.listar();
	}

	/**
	 * Salvar Cliente
	 */
	public void salvar() {
		logger.info("salvando Cliente");
		cliente.setEndereco(endereco);
		cliente.setStatus(true);
		if (validarCliente(cliente)) {
			if (controladorCliente.findByNameFone(cliente) == null) {
				try {
					controladorCliente.inserir(cliente);
					renderPainelVeiculo = true;
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Salvo"));
					botaoCliente = true;
					listaClientes = controladorCliente.listar();
				} catch (Exception e) {
					renderPainelVeiculo = false;
					botaoCliente = false;
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Salvar", "Cliente"));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente J� Existe", ""));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos Obrigatorios n�o preenchidos", ""));
		}
	}

	/**
	 * Salvar Veiculos
	 */
	public void salvarVeiculo() {
		veiculo.setCliente(cliente);
		veiculo.setStatus(true);
		listaVeiculo.add(veiculo);
		if (validarVeiculo(veiculo)){
			if (controladorVeiculo.find(veiculo) == null) {
				try{
					controladorVeiculo.inserir(veiculo);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ve�culo Salvo"));
					botaoVeiculo = true;
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Salvar", "Ve�culo"));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ve�culo j� cadastrado", ""));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informar o Modelo", ""));
		}
	}
	
	public void adicionarVeiculo(){
		veiculo = new Veiculo();
		botaoVeiculo = false;
	}
	
	public void limparModal(){
		cliente = new Cliente();
		endereco = new Endereco();
		veiculo = new Veiculo();
		renderPainelVeiculo = false;
		botaoCliente = false;
		botaoVeiculo = false;
	}
	
	private boolean validarCliente(Cliente cliente){
		if ((cliente.getNome() != null && !cliente.getNome().isEmpty()) && 
				(cliente.getFone1()!=null && !cliente.getFone1().isEmpty())
				&& (cliente.getEndereco().getBairro() != null && !cliente.getEndereco().getBairro().isEmpty())){
			return true;
		} else {
			return false;
		}
	}
	
	private boolean validarVeiculo(Veiculo veiculo){
		if (veiculo.getModelo() != null && !veiculo.getModelo().isEmpty()){
			return true;
		} else {
			return false;
		}
	}
	
	public List<Cliente> completeCliente(String query) {
        List<Cliente> allClientes = controladorCliente.listar();
        List<Cliente> filteredClientes = new ArrayList<Cliente>();
         
        for (int i = 0; i < allClientes.size(); i++) {
            Cliente cliente = allClientes.get(i);
            if(cliente.getNome().toLowerCase().startsWith(query)) {
            	filteredClientes.add(cliente);
            }
        }
        return filteredClientes;
    }
	
	public void selecionarTipoConsulta(){
		System.out.println("tipo consulta==============");
	}
	
	public void selecionarClienteConsulta() {
		if (clienteSelected != null) {
			listaClientes.clear();
			listaClientes.add(clienteSelected);
		} else {
			listaClientes = controladorCliente.listar();
		}
	}
	
	/**
	 * Getters e Setters abaixo
	 * 
	 * @return
	 */
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public boolean isRenderPainelVeiculo() {
		return renderPainelVeiculo;
	}

	public void setRenderPainelVeiculo(boolean renderPainelVeiculo) {
		this.renderPainelVeiculo = renderPainelVeiculo;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public boolean isBotaoCliente() {
		return botaoCliente;
	}

	public void setBotaoCliente(boolean botaoCliente) {
		this.botaoCliente = botaoCliente;
	}

	public boolean isBotaoVeiculo() {
		return botaoVeiculo;
	}

	public void setBotaoVeiculo(boolean botaoVeiculo) {
		this.botaoVeiculo = botaoVeiculo;
	}

	public List<Veiculo> getListaVeiculo() {
		return listaVeiculo;
	}

	public void setListaVeiculo(List<Veiculo> listaVeiculo) {
		this.listaVeiculo = listaVeiculo;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Cliente getClienteSelected() {
		return clienteSelected;
	}

	public void setClienteSelected(Cliente clienteSelected) {
		this.clienteSelected = clienteSelected;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
	
	
	

}
