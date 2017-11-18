package br.com.autoservice.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.OS;
import br.com.autoservice.util.HibernateUtil;
import br.com.autoservice.util.StatusOS;

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
	public List<OS> listarPorData(Date dtInicial, Date dtFinal) {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(OS.class)
					.add(Restrictions.between("fechamento", dtInicial, dtFinal));

			return (List<OS>) cri.list();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<OS> listarAbertas() {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(OS.class)
					.add(Restrictions.eq("status", StatusOS.ABERTA));

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
					.add(Restrictions.eq("idCliente", valor));
			cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return (List<OS>) cri.list();
		} finally {
			session.close();
		}
	}
	
	
	
}
