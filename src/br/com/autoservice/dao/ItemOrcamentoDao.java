package br.com.autoservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.ItemOrcamento;
import br.com.autoservice.modelo.Orcamento;
import br.com.autoservice.util.HibernateUtil;

public class ItemOrcamentoDao extends GenericDao{
	
	@SuppressWarnings("unchecked")
	public List<ItemOrcamento> listar() {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(ItemOrcamento.class);

			return (List<ItemOrcamento>) cri.list();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemOrcamento> listarByOS(Orcamento os) {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(ItemOrcamento.class)
					.add(Restrictions.eq("os", os));

			return (List<ItemOrcamento>) cri.list();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void removeByOS(Orcamento os) {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(ItemOrcamento.class)
					.add(Restrictions.eq("os", os));

			List<ItemOrcamento> lista = cri.list();
			
			for (ItemOrcamento ItemOrcamento : lista) {
				session.beginTransaction();
				session.delete(ItemOrcamento);
				session.getTransaction().commit();
			}
			
		} finally {
			//session.close();
		}
	}
	

}
