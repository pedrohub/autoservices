package br.com.autoservice.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.Usuario;
import br.com.autoservice.util.HibernateUtil;

public class UsuarioDao extends GenericDao {

	public Usuario find(Usuario usuario) throws HibernateException{

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(usuario.getClass())
					.add(Restrictions.eq("login", usuario.getLogin()))
					.add(Restrictions.eq("senha", usuario.getSenha()));

			return (Usuario) cri.uniqueResult();
		} finally {
			session.close();
		}
	}

}
