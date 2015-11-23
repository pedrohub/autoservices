package br.com.autoservice.mb;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.autoservice.dao.UsuarioDao;
import br.com.autoservice.modelo.Usuario;

@ManagedBean
public class UserMB {

	private UsuarioDao userDao;
	private Usuario usuario;
	
	public UserMB(){

		userDao = new UsuarioDao();
		usuario = new Usuario();
	}

	public String validarLogin(){
		userDao.find(usuario);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return "home";
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
	
	
}
