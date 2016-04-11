package br.com.autoservice.mb;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.autoservice.dao.UsuarioDao;
import br.com.autoservice.modelo.Usuario;
import br.com.autoservice.util.LogUtil;

@ManagedBean
public class UserMB {

	//private UsuarioDao userDao;
	private UsuarioDao userDao = UsuarioDao.getInstance();
	private Usuario usuario;
	private org.apache.log4j.Logger logger = LogUtil.logger.getLogger(UserMB.class);
	private boolean habilitaLabelErro = false;
	
	public UserMB(){

		//userDao = new UsuarioDao();
		usuario = new Usuario();
	}

	public void validarLogin(){
		
		Usuario user = null;
		
		logger.info("Validando existencia do usuario.");
		user = userDao.find(usuario);
		if (user != null) {
			String teste = userDao.getUser().getLogin();
		}
		
		
		if (user != null) {
			habilitaLabelErro = false;
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
				logger.info("Carregando pagina -> home.xhtml");
			} catch (IOException e) {
				logger.error("Erro ao tentar carregar pagina: "+e.getMessage());
			} 
		} else {
			habilitaLabelErro = true;
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isHabilitaLabelErro() {
		return habilitaLabelErro;
	}

	public void setHabilitaLabelErro(boolean habilitaLabelErro) {
		this.habilitaLabelErro = habilitaLabelErro;
	}

	public UsuarioDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UsuarioDao userDao) {
		this.userDao = userDao;
	}
	
}
