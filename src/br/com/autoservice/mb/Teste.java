package br.com.autoservice.mb;

import br.com.autoservice.controller.ControladorCliente;
import br.com.autoservice.modelo.Usuario;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		UserMB mb = new UserMB();
		Usuario u = new Usuario();
		
		ClienteMB mb2 = new ClienteMB();
		mb2.salvar();
		
		
	}

}
