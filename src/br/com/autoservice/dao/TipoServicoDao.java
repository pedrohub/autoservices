package br.com.autoservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.Marca;
import br.com.autoservice.modelo.TipoServico;
import br.com.autoservice.util.HibernateUtil;

public class TipoServicoDao extends GenericDao{

	
	@SuppressWarnings("unchecked")
	public List<TipoServico> listar() {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(TipoServico.class);

			return (List<TipoServico>) cri.list();
		} finally {
			session.close();
		}
	}
	
	
	public TipoServico find(TipoServico tipo) throws HibernateException {

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(tipo.getClass())
					.add(Restrictions.eq("descricao", tipo.getDescricao()));

			TipoServico mrc = (TipoServico) cri.uniqueResult();
			if (mrc != null && tipo.getDescricao().equals(mrc.getDescricao())) {
				return mrc;
			} else {
				return null;
			}
		} finally {
			session.close();
		}
	}
	
}
