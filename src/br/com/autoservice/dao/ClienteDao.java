package br.com.autoservice.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.util.HibernateUtil;

public class ClienteDao extends GenericDao {

	public Cliente find(Cliente cliente) throws HibernateException{

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(cliente.getClass())
					.add(Restrictions.eq("login", cliente.getCodCliente()));

			return (Cliente) cri.uniqueResult();
		} finally {
			session.close();
		}
	}

}
