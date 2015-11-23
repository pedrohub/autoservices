package br.com.autoservice.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "peca")
public class Peca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codPeca;
	private String nome;
	private String marca;
	private String quantidade;
	private String descricao;
	private String observacao;
	private boolean status;

	public Peca() {
		super();
	}

	public Peca(Long codPeca, String nome, String marca, String quantidade, String descricao, String observacao,
			boolean status) {
		super();
		this.codPeca = codPeca;
		this.nome = nome;
		this.marca = marca;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.observacao = observacao;
		this.status = status;
	}

	public Long getCodPeca() {
		return codPeca;
	}

	public void setCodPeca(Long codPeca) {
		this.codPeca = codPeca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
