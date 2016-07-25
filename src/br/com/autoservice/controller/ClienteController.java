package br.com.autoservice.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;

import br.com.autoservice.dao.ClienteDao;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.Veiculo;

/**
 * @author binha
 *
 * Controller Clientes
 */
public class ClienteController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3339055576572426402L;
	private ClienteDao clienteDao = new ClienteDao();
	private Logger logger = Logger.getLogger("ClienteController");
	private VeiculoController veiculoController;
	
	private static ClienteController uniqueInstance; 
	
	private ClienteController() { 
		veiculoController = new VeiculoController();
	} 
	
	public static synchronized ClienteController getInstance() { 
		if (uniqueInstance == null) uniqueInstance = new ClienteController(); 
		return uniqueInstance;
	}


	public void inserir(Cliente cliente) {
		try {
			clienteDao.inserir(cliente);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	public void alterar(Cliente cliente) {
		clienteDao.alterar(cliente);
	}

	public void excluir(Cliente cliente) {
		
		for (Veiculo veiculo : cliente.getVeiculos()) {
			veiculoController.excluir(veiculo);
		}
		clienteDao.excluir(cliente);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listar() throws HibernateException {
		Cliente cliente = new Cliente();
		List<Cliente> listaCliente = clienteDao.listar(cliente);
		return listaCliente;
	}

	public Cliente findByName(Cliente cliente) {
		Cliente clienteRetorno = clienteDao.findByName(cliente);
		return clienteRetorno;
	}
	
	public Cliente find(Cliente cliente) {
		Cliente clienteRetorno = clienteDao.find(cliente);
		return clienteRetorno;
	}
	
	public Cliente findByNameFone(Cliente cliente) {
		Cliente clienteRetorno = clienteDao.findByNameFone(cliente);
		return clienteRetorno;
	}
	
	public Cliente findByPlaca(String placa) {
		Cliente clienteRetorno = clienteDao.findByPlaca(placa);
		return clienteRetorno;
	}
	
	public List<String> getEstados(){
		List<String> estados = new ArrayList<>();
		
		estados.add("AC");
		estados.add("AL");
		estados.add("AP");
		estados.add("AM");
		estados.add("BA");
		estados.add("CE");
		estados.add("DF");
		estados.add("ES");
		estados.add("GO");
		estados.add("MA");
		estados.add("MT");
		estados.add("MS");
		estados.add("MG");
		estados.add("PA");
		estados.add("PB");
		estados.add("PR");
		estados.add("PE");
		estados.add("PI");
			estados.add("RJ");
		estados.add("RN");
		estados.add("RS");
		estados.add("RO");
		estados.add("RR");
		estados.add("SC");
		estados.add("SP");
		estados.add("SE");
		estados.add("TO");
		
		return estados;
	}
}
