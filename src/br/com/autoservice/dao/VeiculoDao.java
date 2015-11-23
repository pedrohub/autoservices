package br.com.autoservice.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.Veiculo;
import br.com.autoservice.util.HibernateUtil;

public class VeiculoDao extends GenericDao {

	public Veiculo find(Veiculo veiculo) throws HibernateException{

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(veiculo.getClass())
					.add(Restrictions.eq("placa", veiculo.getPlaca()));

			return (Veiculo) cri.uniqueResult();
		} finally {
			session.close();
		}
	}

}
