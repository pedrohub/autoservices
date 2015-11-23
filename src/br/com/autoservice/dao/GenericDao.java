package br.com.autoservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.util.HibernateUtil;

public class GenericDao {
	
	protected Session session;  
	
	/**
	 * salvar objeto
	 * @param o
	 */
	public void inserir(Object o) throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();

		try{
			session.beginTransaction();
			session.save(o);
			session.getTransaction().commit();

		} finally {
			session.close();

		}
	}
	
	/**
	 * alterar objeto
	 * @param pessoa
	 */
	public void alterar(Object o) throws HibernateException{
		session = HibernateUtil.getSessionFactory().openSession();

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(o);
			session.getTransaction().commit();
		}finally{
			session.close();

		}
	}
	
	/**
	 * deleta o objeto
	 * @param pessoa
	 */
	public void excluir(Object o) throws HibernateException{
		session = HibernateUtil.getSessionFactory().openSession();

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(o);
			session.getTransaction().commit();

		}finally{
			session.close();

		}
	}

	
	@SuppressWarnings("unchecked")
	public List listar(Object o) throws HibernateException{
		session = HibernateUtil.getSessionFactory().openSession();

		try{
			Criteria cri = session.createCriteria(o.getClass()).add(Restrictions.eq("status", true));
			return cri.list();
		}finally{
			session.close();

		}
	}
	

}
