package br.com.autoservice.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "agendamento")
public class Agendamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date abertura;
	private Date vencimento;
	@ManyToOne//(cascade = CascadeType.PERSIST)
	@JoinColumn(name="veiculo_id")
	private Veiculo veiculo;
	private String descricao;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getAbertura() {
		return abertura;
	}
	public void setAbertura(Date abertura) {
		this.abertura = abertura;
	}
	public Date getVencimento() {
		return vencimento;
	}
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	

}
