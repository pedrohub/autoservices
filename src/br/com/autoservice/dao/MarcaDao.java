package br.com.autoservice.dao;

import java.util.List;

import org.hibernate.Criteria;

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
	public List<Marca> listar(Marca marca) {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(marca.getClass());

			return (List<Marca>) cri.list();
		} finally {
			session.close();
		}
	}
	
}
