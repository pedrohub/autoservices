package br.com.autoservice.controller;

import java.util.List;

import org.hibernate.HibernateException;

import br.com.autoservice.dao.EnderecoDao;
import br.com.autoservice.modelo.Endereco;

public class ControladorEndereco {

	private EnderecoDao enderecoDao = new EnderecoDao();

	/**
	 * inserir o objeto
	 * 
	 * @param peca
	 */
	public void inserir(Endereco endereco) throws HibernateException {
		enderecoDao.inserir(endereco);
	}

	/**
	 * alterar objeto
	 * 
	 * @param peca
	 */
	public void alterar(Endereco endereco) throws HibernateException {
		enderecoDao.alterar(endereco);
	}

	/**
	 * deleta o objeto
	 * 
	 * @param peca
	 */
	public void excluir(Endereco endereco) throws HibernateException {
		enderecoDao.excluir(endereco);
	}

	/**
	 * listar o objeto
	 * 
	 * @param peca
	 */
	@SuppressWarnings("unchecked")
	public List<Endereco> listar(Endereco endereco) throws HibernateException {
		List<Endereco> listaEndereco = enderecoDao.listar(endereco);
		return listaEndereco;
	}

	public Endereco find(Endereco endereco) throws HibernateException {
		Endereco enderecoRetorno = enderecoDao.find(endereco);
		return enderecoRetorno;
	}
}
