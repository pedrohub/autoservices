package br.com.autoservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.Agendamento;
import br.com.autoservice.modelo.Veiculo;
import br.com.autoservice.util.HibernateUtil;

public class AgendamentoDao extends GenericDao{

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
	
}
