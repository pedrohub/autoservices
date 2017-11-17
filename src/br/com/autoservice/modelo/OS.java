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
import br.com.autoservice.util.StatusOS;

@Entity
@Table(name = "os")
public class OS {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double valor;
	private StatusOS status;
	@OneToMany(mappedBy = "os", fetch = FetchType.EAGER)
	private List<ItemServico> itens;
	private Date abertura;
	private Date fechamento;
	private String obs;
	private String nomeCliente;
	private String telefoneCliente;
	private Long idCliente;
	private Long idVeiculo;
	private String placa;
	private String modelo;
	private String km;
	
	public void gerarOS(Cliente cliente, Veiculo veiculo){
		
		abertura = DateUtil.pegarData(); 
		fechamento = null;
		idCliente = cliente.getIdCliente();
		nomeCliente = cliente.getNome();
		telefoneCliente = cliente.getFone1();
		idVeiculo = veiculo.getCodVeiculo();
		placa = veiculo.getPlaca();
		modelo = veiculo.getModelo();
		obs = "";
		status = StatusOS.ABERTA;
		valor = 0d;
		km = "";
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public StatusOS getStatus() {
		return status;
	}
	public void setStatus(StatusOS status) {
		this.status = status;
	}
	public List<ItemServico> getItens() {
		return itens;
	}
	public void setItens(List<ItemServico> itens) {
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
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getTelefoneCliente() {
		return telefoneCliente;
	}
	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Long getIdVeiculo() {
		return idVeiculo;
	}
	public void setIdVeiculo(Long idVeiculo) {
		this.idVeiculo = idVeiculo;
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

	public Date getFechamento() {
		return fechamento;
	}

	public void setFechamento(Date fechamento) {
		this.fechamento = fechamento;
	}

	public String getKm() {
		return km;
	}

	public void setKm(String km) {
		this.km = km;
	}

	
	
	
	
}
