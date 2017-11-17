package br.com.autoservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.Orcamento;
import br.com.autoservice.util.HibernateUtil;

public class OrcamentoDao extends GenericDao {

	@SuppressWarnings("unchecked")
	public List<Orcamento> listar() {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(Orcamento.class);

			return (List<Orcamento>) cri.list();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List listarOrcamentos() throws HibernateException{
		session = HibernateUtil.getSessionFactory().openSession();

		try{
			Criteria cri = session.createCriteria(Orcamento.class);
			cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return cri.list();
		}finally{
			session.close();

		}
	}
	
	
	
	
}
