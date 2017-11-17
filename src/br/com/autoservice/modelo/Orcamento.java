package br.com.autoservice.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.autoservice.util.DateUtil;

@Entity
@Table(name = "orcamento")
public class Orcamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double valor;
	@OneToMany(mappedBy = "os", fetch = FetchType.EAGER)
	private List<ItemOrcamento> itens;
	private Date abertura;
	private String obs;
	private String placa;
	private String modelo;
	
	public void gerarOS(Cliente cliente, Veiculo veiculo){
		
		abertura = DateUtil.pegarData(); 
		placa = veiculo.getPlaca();
		modelo = veiculo.getModelo();
		obs = "";
		valor = 0d;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public List<ItemOrcamento> getItens() {
		return itens;
	}

	public void setItens(List<ItemOrcamento> itens) {
		this.itens = itens;
	}

	public Date getAbertura() {
		return abertura;
	}

	public void setAbertura(Date abertura) {
		this.abertura = abertura;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getPlaca() {
		return placa != null ? placa.toUpperCase() : placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
