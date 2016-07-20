package br.com.autoservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.Peca;
import br.com.autoservice.util.HibernateUtil;

public class PecaDao extends GenericDao{

	
	/**
	 * buscar todos as marcas
	 * @param marca
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Peca> listar() {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(Peca.class);

			return (List<Peca>) cri.list();
		} finally {
			session.close();
		}
	}
	
	
	public Peca find(Peca marca) throws HibernateException {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(marca.getClass())
					.add(Restrictions.eq("descricao", marca.getDescricao()));

			Peca mrc = (Peca) cri.uniqueResult();
			if (mrc != null && marca.getDescricao().equals(mrc.getDescricao())) {
				return mrc;
			} else {
				return null;
			}
		} finally {
			session.close();
		}
	}
	
}
