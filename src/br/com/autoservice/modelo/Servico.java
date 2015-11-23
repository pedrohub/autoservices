package br.com.autoservice.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "servico")
public class Servico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codServico;
	private Date dataEntrada;
	private Date dataPrevista;
	private Date dataRetirada;
	private String obs;
	private boolean status;

	public Servico() {
		super();
	}

	public Servico(Long codServico, Date dataEntrada, Date dataPrevista, Date dataRetirada, String obs, boolean status) {
		super();
		this.codServico = codServico;
		this.dataEntrada = dataEntrada;
		this.dataPrevista = dataPrevista;
		this.dataRetirada = dataRetirada;
		this.obs = obs;
		this.status = status;
	}

	public Long getCodServico() {
		return codServico;
	}

	public void setCodServico(Long codServico) {
		this.codServico = codServico;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public Date getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
