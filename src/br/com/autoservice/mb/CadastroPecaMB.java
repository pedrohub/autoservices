package br.com.autoservice.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.HibernateException;

import br.com.autoservice.controller.ControladorPeca;
import br.com.autoservice.modelo.Peca;

@ManagedBean
@SessionScoped
public class CadastroPecaMB {

	private static final String OUTCOME_CONSULTAR_PECA = "cadastroPeca";
	private Peca peca;
	private Peca pecaConsultar;
	private List<Peca> pecas;
	private ControladorPeca controladorPeca;

	public CadastroPecaMB() {
		peca = new Peca();
		pecaConsultar = new Peca();
		if (pecas == null) {
			pecas = new ArrayList<Peca>();
			Peca peca1 = new Peca(1l, "teste1", "minha1", "1", "teste1", "teste1", true);
			Peca peca2 = new Peca(2l, "teste2", "minha2", "2", "teste2", "teste2", true);
			Peca peca3 = new Peca(3l, "teste3", "minha3", "3", "teste3", "teste3", true);
			Peca peca4 = new Peca(4l, "teste4", "minha4", "4", "teste4", "teste4", true);
			Peca peca5 = new Peca(5l, "teste1", "minha1", "1", "teste1", "teste1", true);
			Peca peca6 = new Peca(6l, "teste2", "minha2", "2", "teste2", "teste2", true);
			Peca peca7 = new Peca(7l, "teste3", "minha3", "3", "teste3", "teste3", true);
			Peca peca8 = new Peca(8l, "teste4", "minha4", "4", "teste4", "teste4", true);
			Peca peca9 = new Peca(9l, "teste1", "minha1", "1", "teste1", "teste1", true);
			Peca peca10 = new Peca(10l, "teste2", "minha2", "2", "teste2", "teste2", true);
			Peca peca11 = new Peca(11l, "teste3", "minha3", "3", "teste3", "teste3", true);
			Peca peca12 = new Peca(12l, "teste4", "minha4", "4", "teste4", "teste4", true);
			pecas.add(peca1);
			pecas.add(peca2);
			pecas.add(peca3);
			pecas.add(peca4);
			pecas.add(peca5);
			pecas.add(peca6);
			pecas.add(peca7);
			pecas.add(peca8);
			pecas.add(peca9);
			pecas.add(peca10);
			pecas.add(peca11);
			pecas.add(peca12);
		}
		controladorPeca = new ControladorPeca();
	}

	public String inserirPeca() {
		this.peca.setCodPeca(5l);
		pecas.add(this.peca);
		try {
			if(null != this.peca.getCodPeca()){
//				getControladorPeca().alterar(peca);
			} else {
//				getControladorPeca().inserir(peca);
			}
//			Peca pecaListar = new Peca();
//			pecas = getControladorPeca().listar(pecaListar);
			peca = new Peca();

		} catch (HibernateException e) {

		}
		return OUTCOME_CONSULTAR_PECA;
	}

	public void alterarPeca(Peca peca) {
		
		this.peca = peca;
//		pecas.remove(peca);

	}

	public String excluirPeca(Peca peca) {
		pecas.remove(peca);
		try {
//			getControladorPeca().excluir(peca);
//			Peca pecaListar = new Peca();
//			pecas = getControladorPeca().listar(pecaListar);
			peca = new Peca();

		} catch (HibernateException e) {
//			FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
		}

		return OUTCOME_CONSULTAR_PECA;
	}
	
	public String consultarPeca(){
//		this.pecas = controladorPeca.listar(this.peca);
		
		return OUTCOME_CONSULTAR_PECA;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public Peca getPecaConsultar() {
		return pecaConsultar;
	}

	public void setPecaConsultar(Peca pecaConsultar) {
		this.pecaConsultar = pecaConsultar;
	}

	public List<Peca> getPecas() {
		return pecas;
	}

	public void setPecas(List<Peca> pecas) {
		this.pecas = pecas;
	}

	public ControladorPeca getControladorPeca() {
		return controladorPeca;
	}

	public void setControladorPeca(ControladorPeca controladorPeca) {
		this.controladorPeca = controladorPeca;
	}

}
