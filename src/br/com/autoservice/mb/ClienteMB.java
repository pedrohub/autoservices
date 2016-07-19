package br.com.autoservice.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.autoservice.controller.ClienteController;
import br.com.autoservice.controller.MarcaController;
import br.com.autoservice.controller.VeiculoController;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.Endereco;
import br.com.autoservice.modelo.Marca;
import br.com.autoservice.modelo.Veiculo;
import br.com.autoservice.util.Constantes;
import br.com.autoservice.util.LogUtil;

/**
 * @author pedro.edson.o.lima
 *
 */
@ManagedBean
@SessionScoped
public class ClienteMB implements Serializable{

	private static final long serialVersionUID = -2814761488716950745L;
	private ClienteController controladorCliente;
	private VeiculoController controladorVeiculo;
	private Cliente cliente;
	private Endereco endereco;
	private Veiculo veiculo;
	private boolean renderPainelVeiculo;
	private boolean botaoCliente;
	private boolean botaoVeiculo;	
	private List<Cliente> listaClientes;
	private List<Marca> listaMarcas = null;
	private Cliente clienteSelected;
	private String tipoConsulta;
	private org.apache.log4j.Logger logger = LogUtil.logger.getLogger(ClienteMB.class);
	private ArrayList<String> marcas = null;
	private String flagTipoVeiculo = "0";
	private String flagTipoConsulta = "nome";
	
	private boolean consultaNome;
	private boolean consultaFone;
	private boolean consultaPlaca;
	

	@PostConstruct
	public void init() {
		controladorCliente = controladorCliente.getInstance();
		cliente = new Cliente();
		endereco = new Endereco();
		veiculo = new Veiculo();
		renderPainelVeiculo = false;
		controladorVeiculo = new VeiculoController();
		botaoCliente = false;
		botaoVeiculo = false;
		listaClientes = controladorCliente.listar();
		listaMarcas = MarcaController.getInstance().getListaMarca();
	}

	/**
	 * Salvar Cliente
	 */
	public void salvar() {
		logger.info("salvando Cliente");
		
		if (endereco.getUf() == null || endereco.getUf().equals(Constantes.VAZIO)) {
			endereco.setUf("Pernambuco");
		}
		
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
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente Ja Existe", ""));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos Obrigatorios nao preenchidos: Nome, Fone 1 e Bairro", ""));
		}
	}

	/**
	 * Salvar Veiculos
	 */
	public void salvarVeiculo() {
		veiculo.setCliente(cliente);
		veiculo.setStatus(true);
		
		if (validarVeiculo(veiculo)){
			if (controladorVeiculo.find(veiculo) == null) {
				try{
					veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
					controladorVeiculo.inserir(veiculo);
					botaoVeiculo = true;
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veiculo Salvo"));
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Salvar", "Veiculo"));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Veiculo ja cadastrado", ""));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informar Campos Obrigatorios", ""));
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
	 * Excluir clientes e seus veiculos
	 * @param cliente
	 */
	public void removeCliente(Cliente cliente){
		controladorCliente.excluir(cliente);
		listaClientes = controladorCliente.listar();
	}
	
	public List<Cliente> completeCliente(String query) {
        List<Cliente> allClientes = controladorCliente.listar();
        List<Cliente> filteredClientes = new ArrayList<Cliente>();
        
//        for (int i = 0; i < allClientes.size(); i++) {
//            Cliente cliente = allClientes.get(i);
//            if(cliente.getNome().toLowerCase().startsWith(query)) {
//            	filteredClientes.add(cliente);
//            }
//        }
        consultaNome = false;
    	consultaFone = false;
    	consultaPlaca = false;
    	
        for (int i = 0; i < allClientes.size(); i++) {
            Cliente cliente = allClientes.get(i);
            if (flagTipoConsulta.equalsIgnoreCase(Constantes.NOME)) {
	            if(cliente.getNome().toLowerCase().startsWith(query)) {
	            	filteredClientes.add(cliente);
	            	consultaNome = true;
	            }
            } else if (flagTipoConsulta.equalsIgnoreCase(Constantes.TELEFONE)) {
            	if (cliente.getFone1() != null || !cliente.getFone1().equals(Constantes.VAZIO)) {
	            	String fone1 = cliente.getFone1().substring(6, 16);
	            	 if(fone1.startsWith(query)) {
	 	            	filteredClientes.add(cliente);
	 	            	consultaFone = true;
	 	            }
            	}
            } else if (flagTipoConsulta.equalsIgnoreCase(Constantes.PLACA)) {
            	if (!cliente.getVeiculos().isEmpty() && cliente.getVeiculos() != null) {
	            	for (Veiculo veiculo : cliente.getVeiculos()) {
	            		if (veiculo.getPlaca() != null && !veiculo.getPlaca().equals(Constantes.VAZIO)) {
		            		 if(veiculo.getPlaca().toLowerCase().startsWith(query)) {
		      	            	filteredClientes.add(cliente);
		      	            	consultaPlaca = true;
		      	            }
	            		}
	            	}
            	}
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
	
	
	public List<String> getEstados(){
		return ClienteController.getInstance().getEstados();
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

	public ArrayList<String> getMarcas() {
		return marcas;
	}

	public void setMarcas(ArrayList<String> marcas) {
		this.marcas = marcas;
	}

	public String getFlagTipoVeiculo() {
		return flagTipoVeiculo;
	}

	public void setFlagTipoVeiculo(String flagTipoVeiculo) {
		this.flagTipoVeiculo = flagTipoVeiculo;
	}

	public String getFlagTipoConsulta() {
		return flagTipoConsulta;
	}

	public void setFlagTipoConsulta(String flagTipoConsulta) {
		this.flagTipoConsulta = flagTipoConsulta;
	}

	public boolean isConsultaNome() {
		return consultaNome;
	}

	public void setConsultaNome(boolean consultaNome) {
		this.consultaNome = consultaNome;
	}

	public boolean isConsultaFone() {
		return consultaFone;
	}

	public void setConsultaFone(boolean consultaFone) {
		this.consultaFone = consultaFone;
	}

	public boolean isConsultaPlaca() {
		return consultaPlaca;
	}

	public void setConsultaPlaca(boolean consultaPlaca) {
		this.consultaPlaca = consultaPlaca;
	}

	public List<Marca> getListaMarcas() {
		return listaMarcas;
	}

	public void setListaMarcas(List<Marca> listaMarcas) {
		this.listaMarcas = listaMarcas;
	}
	
	
	
}
