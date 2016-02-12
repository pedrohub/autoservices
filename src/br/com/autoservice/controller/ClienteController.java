package br.com.autoservice.controller;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;

import br.com.autoservice.dao.ClienteDao;
import br.com.autoservice.modelo.Cliente;

/**
 * @author binha
 *
 * Controller Clientes
 */
public class ClienteController {

	private ClienteDao clienteDao = new ClienteDao();
	private Logger logger = Logger.getLogger("ClienteController");
	
	private static ClienteController uniqueInstance; 
	
	private ClienteController() { 
		
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
}
