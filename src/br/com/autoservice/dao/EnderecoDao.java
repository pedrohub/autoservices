package br.com.autoservice.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.Endereco;
import br.com.autoservice.util.HibernateUtil;

public class EnderecoDao extends GenericDao {

	public Endereco find(Endereco endereco) throws HibernateException{

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(endereco.getClass())
					.add(Restrictions.eq("login", endereco.getId()));

			return (Endereco) cri.uniqueResult();
		} finally {
			session.close();
		}
	}

}
