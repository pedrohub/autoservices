package br.com.autoservice.controller;

import java.util.List;

import org.hibernate.HibernateException;

import br.com.autoservice.dao.ClienteDao;
import br.com.autoservice.modelo.Cliente;

public class ControladorCliente {

	private ClienteDao clienteDao = new ClienteDao();

	/**
	 * inserir o objeto
	 * 
	 * @param peca
	 */
	public void inserir(Cliente cliente) throws HibernateException {
		clienteDao.inserir(cliente);
	}

	/**
	 * alterar objeto
	 * 
	 * @param peca
	 */
	public void alterar(Cliente cliente) throws HibernateException {
		clienteDao.alterar(cliente);
	}

	/**
	 * deleta o objeto
	 * 
	 * @param peca
	 */
	public void excluir(Cliente cliente) throws HibernateException {
		clienteDao.excluir(cliente);
	}

	/**
	 * listar o objeto
	 * 
	 * @param peca
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> listar() throws HibernateException {
		Cliente cliente = new Cliente();
		List<Cliente> listaCliente = clienteDao.listar(cliente);
		return listaCliente;
	}

	public Cliente find(Cliente cliente) throws HibernateException {
		Cliente clienteRetorno = clienteDao.find(cliente);
		return clienteRetorno;
	}
}
