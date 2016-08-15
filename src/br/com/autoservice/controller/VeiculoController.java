package br.com.autoservice.controller;

import java.util.List;

import org.hibernate.HibernateException;

import br.com.autoservice.dao.VeiculoDao;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.Veiculo;

public class VeiculoController {

	private VeiculoDao veiculoDao;

	private static VeiculoController uniqueInstance; 
	
	private VeiculoController() { 
		veiculoDao = new VeiculoDao();
	} 
	
	public static synchronized VeiculoController getInstance() { 
		if (uniqueInstance == null) uniqueInstance = new VeiculoController(); 
		return uniqueInstance;
	}
	
	/**
	 * inserir o objeto
	 * 
	 * @param peca
	 */
	public void inserir(Veiculo veiculo) throws HibernateException {
		veiculoDao.inserir(veiculo);
	}

	/**
	 * alterar objeto
	 * 
	 * @param peca
	 */
	public void alterar(Veiculo veiculo) throws HibernateException {
		veiculoDao.alterar(veiculo);
	}

	/**
	 * deleta o objeto
	 * 
	 * @param peca
	 */
	public void excluir(Veiculo veiculo) throws HibernateException {
		veiculoDao.excluir(veiculo);
	}
	
	/**
	 * listar o objeto
	 * 
	 * @param veiculo
	 */
	@SuppressWarnings("unchecked")
	public List<Veiculo> listar(Veiculo veiculo) throws HibernateException {
		List<Veiculo> listaPeca = veiculoDao.listar(veiculo);
		return listaPeca;
	}
	
	public Veiculo find(Veiculo veiculo) {
		Veiculo veiculoRetorno = veiculoDao.find(veiculo); 
		return veiculoRetorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Veiculo> listar(Cliente cliente) throws HibernateException {
		List<Veiculo> listaPeca = veiculoDao.listar(cliente);
		return listaPeca;
	}
}
