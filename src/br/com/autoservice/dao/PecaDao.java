package br.com.autoservice.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.Peca;
import br.com.autoservice.util.HibernateUtil;

public class PecaDao extends GenericDao {

	public Peca find(Peca o) throws HibernateException{

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(o.getClass())
					.add(Restrictions.eq("login", o.getCodPeca()));

			return (Peca) cri.uniqueResult();
		} finally {
			session.close();
		}
	}

}
