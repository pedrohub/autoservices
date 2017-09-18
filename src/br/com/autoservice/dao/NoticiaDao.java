package br.com.autoservice.dao;

import java.util.List;

import org.hibernate.Criteria;

import br.com.autoservice.util.HibernateUtil;
import br.com.robson.rest.NoticiaBase;

public class NoticiaDao extends GenericDao{

	@SuppressWarnings("unchecked")
	public List<NoticiaBase> listar() {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(NoticiaBase.class);

			return (List<NoticiaBase>) cri.list();
		} finally {
			session.close();
		}
	}
}
