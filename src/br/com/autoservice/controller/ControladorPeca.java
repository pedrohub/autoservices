package br.com.autoservice.controller;

import java.util.List;

import org.hibernate.HibernateException;

import br.com.autoservice.dao.PecaDao;
import br.com.autoservice.modelo.Peca;

public class ControladorPeca {

	private PecaDao pecaDao = new PecaDao();

	/**
	 * inserir o objeto
	 * 
	 * @param peca
	 */
	public void inserir(Peca peca) throws HibernateException {
		pecaDao.inserir(peca);
	}

	/**
	 * alterar objeto
	 * 
	 * @param peca
	 */
	public void alterar(Peca peca) throws HibernateException {
		pecaDao.alterar(peca);
	}

	/**
	 * deleta o objeto
	 * 
	 * @param peca
	 */
	public void excluir(Peca peca) throws HibernateException {
		pecaDao.excluir(peca);
	}
	
	/**
	 * listar o objeto
	 * 
	 * @param peca
	 */
	@SuppressWarnings("unchecked")
	public List<Peca> listar(Peca peca) throws HibernateException {
		List<Peca> listaPeca = pecaDao.listar(peca);
		return listaPeca;
	}
	
	public Peca find(Peca peca) throws HibernateException{
		Peca pecaRetorno = pecaDao.find(peca); 
		return pecaRetorno;
	}
}
