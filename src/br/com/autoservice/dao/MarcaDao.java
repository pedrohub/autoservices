package br.com.autoservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.Marca;
import br.com.autoservice.util.HibernateUtil;


/**
 * @author robson.carlos.santos
 *
 *Classe Dao Marcas
 */
public class MarcaDao extends GenericDao {

	/**
	 * buscar todos as marcas
	 * @param marca
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Marca> listar() {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(Marca.class);

			return (List<Marca>) cri.list();
		} finally {
			session.close();
		}
	}
	
	
	public Marca find(Marca marca) throws HibernateException {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(marca.getClass())
					.add(Restrictions.eq("marca", marca.getMarca()));

			Marca mrc = (Marca) cri.uniqueResult();
			if (mrc != null && marca.getMarca().equals(mrc.getMarca())) {
				return mrc;
			} else {
				return null;
			}
		} finally {
			session.close();
		}
	}
	
}
