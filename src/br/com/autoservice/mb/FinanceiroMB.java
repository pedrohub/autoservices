package br.com.autoservice.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.autoservice.controller.OSController;
import br.com.autoservice.modelo.OS;

@ManagedBean
@ViewScoped
public class FinanceiroMB implements Serializable{

	private static final long serialVersionUID = 434262412662310503L;
	private static OSController oSController;
	private List<OS> lista;
	private double total = 0;
	private Date ini;
	private Date fim;
	
	
	@PostConstruct
	public void init() {
		oSController = OSController.getInstance();
		lista = new ArrayList<OS>();
	}
	
	public void consultarPorPeriodo(){
		lista = oSController.listaOS(ini, fim);
		total = 0;
		if(!lista.isEmpty()){
			for (OS os : lista) {
				total += os.getValor();
			}
		}
	}

	public List<OS> getLista() {
		return lista;
	}

	public void setLista(List<OS> lista) {
		this.lista = lista;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getIni() {
		return ini;
	}

	public void setIni(Date ini) {
		this.ini = ini;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}
	
	
}
