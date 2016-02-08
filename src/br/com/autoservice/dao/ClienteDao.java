package br.com.autoservice.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.util.HibernateUtil;


/**
 * @author binha
 *
 *Classe Dao Clientes
 */
public class ClienteDao extends GenericDao {

	/**
	 * consultar cliente por nome
	 * @param cliente
	 * @return
	 */
	public Cliente findByName(Cliente cliente) {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(cliente.getClass())
					.add(Restrictions.like("nome", cliente.getNome()+"%"))
					.add(Restrictions.eq("status", true));

			return (Cliente) cri.list();
		} finally {
			session.close();
		}
	}
	
	/**
	 * consultar cliente por nome e fone
	 * @param cliente
	 * @return
	 */
	public Cliente findByNameFone(Cliente cliente) {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(cliente.getClass())
					.add(Restrictions.eq("nome", cliente.getNome()))
					.add(Restrictions.eq("fone1", cliente.getFone1()))
					.add(Restrictions.eq("status", true));

			return (Cliente) cri.uniqueResult();
		} finally {
			session.close();
		}
	}
	
	/**
	 * consultar cliente por id
	 * @param cliente
	 * @return
	 */
	public Cliente find(Cliente cliente) {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(cliente.getClass())
					.add(Restrictions.eq("nome", cliente.getIdCliente()))
					.add(Restrictions.eq("status", true));

			return (Cliente) cri.uniqueResult();
		} finally {
			session.close();
		}
	}
}
