package br.com.autoservice.controller;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;

import br.com.autoservice.dao.MarcaDao;
import br.com.autoservice.modelo.Marca;

/**
 * @author robson.carlos.santos
 *
 * Controller Marcas
 */
public class MarcaController {

	private MarcaDao marcaDao = new MarcaDao();
	private Logger logger = Logger.getLogger("MarcaController");
	
	private static MarcaController uniqueInstance; 
	
	private MarcaController() { 
		
	} 
	
	public static synchronized MarcaController getInstance() { 
		if (uniqueInstance == null) uniqueInstance = new MarcaController(); 
		return uniqueInstance;
	}

	@SuppressWarnings("unchecked")
	public List<Marca> listar() throws HibernateException {
		Marca marca = new Marca();
		List<Marca> listaMarca = marcaDao.listar(marca);
		return listaMarca;
	}
	
}
