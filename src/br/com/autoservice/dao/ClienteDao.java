package br.com.autoservice.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.util.HibernateUtil;

public class ClienteDao extends GenericDao {

	public Cliente findByName(Cliente cliente) {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(cliente.getClass())
					.add(Restrictions.like("nome", cliente.getNome()+"%"));

			return (Cliente) cri.list();
		} finally {
			session.close();
		}
	}
	
	public Cliente findByCpf(Cliente cliente) {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(cliente.getClass())
					.add(Restrictions.like("cpf", cliente.getCpf()));

			return (Cliente) cri.uniqueResult();
		} finally {
			session.close();
		}
	}

}
