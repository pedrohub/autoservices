package br.com.autoservice.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.Agendamento;
import br.com.autoservice.modelo.Veiculo;
import br.com.autoservice.util.DateUtil;
import br.com.autoservice.util.HibernateUtil;

public class AgendamentoDao extends GenericDao implements Serializable{

	private static final long serialVersionUID = -2524539452546020180L;

	public List findByVeiculo(Veiculo veiculo) {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(Agendamento.class)
					.add(Restrictions.eq("veiculo", veiculo));

			return cri.list();
		} finally {
			session.close();
		}
	}
	
	public List findAll() {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(Agendamento.class);

			return cri.list();
		} finally {
			session.close();
		}
	}
	
	public List getAgendamentosVencidos() {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(Agendamento.class)
					.add(Restrictions.lt("vencimento", DateUtil.getDate()));

			return cri.list();
		} finally {
			session.close();
		}
	}
	
	public List findAllStatus() {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(Agendamento.class)
					.add(Restrictions.eq("status", true));

			return cri.list();
		} finally {
			session.close();
		}
	}
	
}
