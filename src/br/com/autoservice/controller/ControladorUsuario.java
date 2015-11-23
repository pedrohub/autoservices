package br.com.autoservice.controller;

import java.util.List;

import org.hibernate.HibernateException;

import br.com.autoservice.dao.UsuarioDao;
import br.com.autoservice.modelo.Usuario;

public class ControladorUsuario {

	private UsuarioDao usuarioDao = new UsuarioDao();

	/**
	 * inserir o objeto
	 * 
	 * @param peca
	 */
	public void inserir(Usuario usuario) throws HibernateException {
		usuarioDao.inserir(usuario);
	}

	/**
	 * alterar objeto
	 * 
	 * @param peca
	 */
	public void alterar(Usuario usuario) throws HibernateException {
		usuarioDao.alterar(usuario);
	}

	/**
	 * deleta o objeto
	 * 
	 * @param peca
	 */
	public void excluir(Usuario usuario) throws HibernateException {
		usuarioDao.excluir(usuario);
	}
	
	/**
	 * listar o objeto
	 * 
	 * @param peca
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> listar(Usuario usuario) throws HibernateException {
		List<Usuario> listaUsuario = usuarioDao.listar(usuario);
		return listaUsuario;
	}
	
	public Usuario find(Usuario usuario) throws HibernateException{
		Usuario usuarioRetorno = usuarioDao.find(usuario); 
		return usuarioRetorno;
	}
}
