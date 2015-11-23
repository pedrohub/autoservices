package br.com.autoservice.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.Servico;
import br.com.autoservice.util.HibernateUtil;

public class ServicoDao extends GenericDao {

	public Servico find(Servico servico) throws HibernateException{

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(servico.getClass())
					.add(Restrictions.eq("login", servico.getCodServico()));

			return (Servico) cri.uniqueResult();
		} finally {
			session.close();
		}
	}

}
