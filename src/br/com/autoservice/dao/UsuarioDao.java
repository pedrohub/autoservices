package br.com.autoservice.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.Usuario;
import br.com.autoservice.util.HibernateUtil;
import br.com.autoservice.util.LogUtil;

public class UsuarioDao extends GenericDao {
	
	private org.apache.log4j.Logger logger = LogUtil.logger.getLogger(UsuarioDao.class);
	Usuario user = null;
	
    // Variável estática que conterá a instancia da classe
    private static UsuarioDao instance;

    // Construtor privado (suprime o construtor público padrão).
    private UsuarioDao() {}

    // Método público estático de acesso único ao objeto!
    public static UsuarioDao getInstance() {
        if (instance == null)
            instance = new UsuarioDao();
        return instance;
    }

	public Usuario find(Usuario usuario) throws HibernateException{
		
		//Usuario user = null;

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(usuario.getClass())
					.add(Restrictions.eq("login", usuario.getLogin()))
					.add(Restrictions.eq("senha", usuario.getSenha()));
			
			logger.info("Validando usuario e senha.");
			// consulta na base se o usuario existe
			user = (Usuario) cri.uniqueResult();
			if (user != null && usuario.getLogin().equals(user.getLogin())
					         && usuario.getSenha().equals(user.getSenha())) {
				logger.info("Usuario logado...");
				return user;
			} else {
				logger.info("Usuario ou senha invalidos.");
				return null;
			}
			
		} finally {
			session.close();
		}
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	

}
