package br.com.autoservice.dao;

import java.util.List;

import org.hibernate.Criteria;

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

}
