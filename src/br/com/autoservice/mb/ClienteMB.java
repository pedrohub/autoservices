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
import br.com.autoservice.controller.MarcaController;
import br.com.autoservice.controller.VeiculoController;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.Endereco;
import br.com.autoservice.modelo.Marca;
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
	private MarcaController controladorMarca;
	private Cliente cliente;
	private Endereco endereco;
	private Veiculo veiculo;
	private List<Veiculo> listaVeiculo;
	private boolean renderPainelVeiculo;
	private boolean botaoCliente;
	private boolean botaoVeiculo;
	private List<Cliente> listaClientes;
	private List<Marca> listaMarcasBkp;
	private List<String> listaMarcas = null;
	private Cliente clienteSelected;
	private String tipoConsulta;
	private org.apache.log4j.Logger logger = LogUtil.logger.getLogger(ClienteMB.class);
	private ArrayList<String> marcas = null;
	private String flagTipoVeiculo = "0";
	private String flagTipoConsulta = "nome";
	
	private static final String VAZIO = "";
	private static final String NOME = "nome";
	private static final String TELEFONE = "telefone";
	private static final String PLACA = "placa";

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
		
		if (endereco.getUf() == null || endereco.getUf().equals(VAZIO)) {
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
					
					//Buscar todas as marcas de veiculos em banco
					if (listaMarcas == null) {
						listaMarcas = new ArrayList<String>();
						controladorMarca = controladorMarca.getInstance();
						listaMarcasBkp = controladorMarca.listar();
						for (Marca marca : listaMarcasBkp) {
							if (marca.getTipo().equals("0")) {
								listaMarcas.add(marca.getMarca());
							}
						}
					}
					
				} catch (Exception e) {
					renderPainelVeiculo = false;
					botaoCliente = false;
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Salvar", "Cliente"));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente Já Existe", ""));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos Obrigatorios não preenchidos: Nome, Fone 1 e Bairro", ""));
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
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veículo Salvo"));
					botaoVeiculo = true;
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Salvar", "Veículo"));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Veículo já cadastrado", ""));
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
         
//        for (int i = 0; i < allClientes.size(); i++) {
//            Cliente cliente = allClientes.get(i);
//            if(cliente.getNome().toLowerCase().startsWith(query)) {
//            	filteredClientes.add(cliente);
//            }
//        }
        for (int i = 0; i < allClientes.size(); i++) {
            Cliente cliente = allClientes.get(i);
            if (flagTipoConsulta.equalsIgnoreCase(NOME)) {
	            if(cliente.getNome().toLowerCase().startsWith(query)) {
	            	filteredClientes.add(cliente);
	            }
            } else if (flagTipoConsulta.equalsIgnoreCase(TELEFONE)) {
            	if (cliente.getFone1() != null || !cliente.getFone1().equals(VAZIO)) {
	            	String fone1 = cliente.getFone1().substring(6, 16);
	            	 if(fone1.startsWith(query)) {
	 	            	filteredClientes.add(cliente);
	 	            }
            	}
            } else if (flagTipoConsulta.equalsIgnoreCase(PLACA)) {
            	for (Veiculo veiculo : cliente.getVeiculos()) {
            		 if(veiculo.getPlaca().toLowerCase().startsWith(query)) {
      	            	filteredClientes.add(cliente);
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
	
	
	/**
	 * Metodo para carregar o combo marca
	 * @return listaMarcas
	 */
	public List<String> carregarComboMarca(){
		
		listaMarcas = new ArrayList<String>();
		
		for (Marca marca : listaMarcasBkp) {
			if (marca.getTipo().equals(flagTipoVeiculo)) {
				listaMarcas.add(marca.getMarca());
			}
		}
		
		return listaMarcas;
	}
	
	/**
	 * Metodo para converter placa para UpperCase
	 * @return veiculo
	 */
	public Veiculo convertetoUpperCase(){
		
		if (veiculo.getPlaca() != null) {
			veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
		}
		
		return veiculo;
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

	public List<String> getListaMarcas() {
		return listaMarcas;
	}

	public void setListaMarcas(List<String> listaMarcas) {
		this.listaMarcas = listaMarcas;
	}

	public String getFlagTipoConsulta() {
		return flagTipoConsulta;
	}

	public void setFlagTipoConsulta(String flagTipoConsulta) {
		this.flagTipoConsulta = flagTipoConsulta;
	}
	
}
