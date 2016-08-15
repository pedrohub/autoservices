package br.com.autoservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.Veiculo;
import br.com.autoservice.util.HibernateUtil;

public class VeiculoDao extends GenericDao {

	public Veiculo find(Veiculo veiculo) throws HibernateException{

		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cri = session.createCriteria(veiculo.getClass())
					.add(Restrictions.eq("placa", veiculo.getPlaca()))
					.add(Restrictions.eq("status", true));

			return (Veiculo) cri.uniqueResult();
		} finally {
			session.close();
		}
	}
	
	public List listar(Cliente cliente) throws HibernateException{
		session = HibernateUtil.getSessionFactory().openSession();

		try{
			Criteria cri = session.createCriteria(Veiculo.class).add(Restrictions.eq("status", true)).
					add(Restrictions.eq("cliente", cliente));
			return cri.list();
		}finally{
			session.close();

		}
	}

}
