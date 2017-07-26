package br.com.autoservice.rs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.autoservice.controller.ClienteController;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.ItemServico;
import br.com.autoservice.modelo.OS;
import br.com.autoservice.modelo.Veiculo;

import com.google.gson.Gson;

@Path("/servicoWS")
public class RestController {
	
	// http://localhost:8080/autoService/rest/servicoWS/getOs/1
	@GET
	@Path("/getOs/{valor}/")
	@Produces("application/json")
	public String getOs(@PathParam("valor") String valor) {
		
		OS os = new OS();
		os.setId(1l);
		os.setDesconto(2.2);
		os.setModelo("fiat");
		os.setNomeCliente("nomeOba da name");
		os.setPlaca("KTM-0393");
		os.setItens(new ArrayList<ItemServico>());
		
		ItemServico item = new ItemServico();
		item.setDescricao("booracha");
		item.setQuantidade(2);
		item.setValor(2.34);
		
		ItemServico itemb = new ItemServico();
		itemb.setDescricao("booracha");
		itemb.setQuantidade(2);
		itemb.setValor(2.34);
		
		os.getItens().add(item);
		os.getItens().add(itemb);
		
		Gson gson = new Gson();
		return gson.toJson(os);	
	}
	
	//http://localhost:8080/autoService/rest/servicoWS/getCliente?placa=FFF-0000
	@GET
	@Path("/getCliente/")
	@Produces("application/json")
	public String getCliente(@QueryParam("placa")String valor) {
		
		ClienteController controladorCliente = ClienteController.getInstance();
		
		Cliente retorno = controladorCliente.findByPlaca(valor);
		if(retorno != null){
			
			Map<String , String> mapCliente = new HashMap<String, String>();
			mapCliente.put("nome", retorno.getNome());
			mapCliente.put("fone1", retorno.getFone1());
			
			Map<String , String> mapVeiculos = new HashMap<String, String>();
			for (Veiculo veiculo : retorno.getVeiculos()) {
				mapVeiculos.put("placa", veiculo.getPlaca());
				mapVeiculos.put("modelo", veiculo.getModelo());
				mapVeiculos.put("ano", veiculo.getAno());
			}
			
			Gson gson = new Gson();
			
			String veiculos = mapVeiculos.toString();
			
			mapCliente.put("veiculos", veiculos.replace("\u003d", ":"));
			
			return gson.toJson(mapCliente);
		}
		
		return "nao encontrado clientes para essa placa";
	}


}
