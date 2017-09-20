package br.com.autoservice.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.ItemServico;
import br.com.autoservice.modelo.OS;
import br.com.autoservice.util.HibernateUtil;

public class ItemServicoDao extends GenericDao{
	
	@SuppressWarnings("unchecked")
	public List<ItemServico> listar() {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(ItemServico.class);

			return (List<ItemServico>) cri.list();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemServico> listarByOS(OS os) {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(ItemServico.class)
					.add(Restrictions.eq("os", os));

			return (List<ItemServico>) cri.list();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void removeByOS(OS os) {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(ItemServico.class)
					.add(Restrictions.eq("os", os));

			List<ItemServico> lista = cri.list();
			
			for (ItemServico itemServico : lista) {
				session.beginTransaction();
				session.delete(itemServico);
				session.getTransaction().commit();
			}
			
		} finally {
			//session.close();
		}
	}
	

}
