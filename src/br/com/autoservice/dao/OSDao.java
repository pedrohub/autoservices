package br.com.autoservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.OS;
import br.com.autoservice.util.HibernateUtil;

public class OSDao extends GenericDao {

	@SuppressWarnings("unchecked")
	public List<OS> listar() {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(OS.class);

			return (List<OS>) cri.list();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<OS> listarByCliente(Long valor) {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(OS.class)
					.add(Restrictions.eq("idCliente", valor))
					.add(Restrictions.eq("tipo", "os"));
			cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return (List<OS>) cri.list();
		} finally {
			session.close();
		}
	}
	
	
	
}
