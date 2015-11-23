package br.com.autoservice.controller;

import java.util.List;

import org.hibernate.HibernateException;

import br.com.autoservice.dao.ServicoDao;
import br.com.autoservice.modelo.Servico;

public class ControladorServico {

	private ServicoDao servicoDao = new ServicoDao();

	/**
	 * inserir o objeto
	 * 
	 * @param peca
	 */
	public void inserir(Servico servico) throws HibernateException {
		servicoDao.inserir(servico);
	}

	/**
	 * alterar objeto
	 * 
	 * @param peca
	 */
	public void alterar(Servico servico) throws HibernateException {
		servicoDao.alterar(servico);
	}

	/**
	 * deleta o objeto
	 * 
	 * @param peca
	 */
	public void excluir(Servico servico) throws HibernateException {
		servicoDao.excluir(servico);
	}
	
	/**
	 * listar o objeto
	 * 
	 * @param peca
	 */
	@SuppressWarnings("unchecked")
	public List<Servico> listar(Servico servico) throws HibernateException {
		List<Servico> listaServico = servicoDao.listar(servico);
		return listaServico;
	}
	
	public Servico find(Servico servico) throws HibernateException{
		Servico servicoRetorno = servicoDao.find(servico); 
		return servicoRetorno;
	}
}
