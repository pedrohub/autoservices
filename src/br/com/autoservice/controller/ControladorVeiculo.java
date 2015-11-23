package br.com.autoservice.controller;

import java.util.List;

import org.hibernate.HibernateException;

import br.com.autoservice.dao.VeiculoDao;
import br.com.autoservice.modelo.Veiculo;

public class ControladorVeiculo {

	private VeiculoDao veiculoDao = new VeiculoDao();

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
	
	public Veiculo find(Veiculo veiculo) throws HibernateException{
		Veiculo veiculoRetorno = veiculoDao.find(veiculo); 
		return veiculoRetorno;
	}
}
