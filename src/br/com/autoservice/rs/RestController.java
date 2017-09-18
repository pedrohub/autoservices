package br.com.autoservice.rs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.autoservice.controller.ClienteController;
import br.com.autoservice.controller.NoticiaController;
import br.com.autoservice.dao.NoticiaDao;
import br.com.autoservice.modelo.Cliente;
import br.com.autoservice.modelo.ItemServico;
import br.com.autoservice.modelo.OS;
import br.com.autoservice.modelo.Veiculo;
import br.com.robson.rest.Noticia;

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
//	@GET
//	@Path("/getCliente/")
//	@Produces("application/json")
//	public String getCliente(@QueryParam("placa")String valor) {
//		
//		ClienteController controladorCliente = ClienteController.getInstance();
//		
//		Cliente retorno = controladorCliente.findByPlaca(valor);
//		if(retorno != null){
//			
//			Map<String , String> mapCliente = new HashMap<String, String>();
//			mapCliente.put("nome", retorno.getNome());
//			mapCliente.put("fone1", retorno.getFone1());
//			
//			Map<String , String> mapVeiculos = new HashMap<String, String>();
//			for (Veiculo veiculo : retorno.getVeiculos()) {
//				mapVeiculos.put("placa", veiculo.getPlaca());
//				mapVeiculos.put("modelo", veiculo.getModelo());
//				mapVeiculos.put("ano", veiculo.getAno());
//			}
//			
//			Gson gson = new Gson();
//			
//			String veiculos = mapVeiculos.toString();
//			
//			mapCliente.put("veiculos", veiculos.replace("\u003d", ":"));
//			
//			return gson.toJson(mapCliente);
//		}
//		
//		return "nao encontrado clientes para essa placa";
//	}

	
	//http://ismaelpecaseservicos-domaindev.rhcloud.com/autoServices/rest/servicoWS/todasNoticias
	/**
	 * Esse método lista todas noticias cadastradas na base
	 * */
	@GET
	@Produces("application/json")
	@Path("/todasNoticias")
	public List<Noticia> TodasNoticias(){
		
		NoticiaController controller = NoticiaController.getInstance();
		return controller.listar();
	}
	
	//http://localhost:8080/autoService/rest/servicoWS/salvar?autor=teste&descricao=descriteste&foto=2132j4j34ji3&titulo=tututo&url=httpurl
	/**
	 * Esse método lista todas noticias cadastradas na base
	 * */
	@GET
	@Produces("application/json")
	@Path("/salvar")
	public String salvar(@QueryParam("autor")String autor, @QueryParam("descricao")String descricao,
			@QueryParam("foto")String foto, @QueryParam("titulo")String titulo, @QueryParam("url")String url
			){
		
		try{
			Noticia item = new Noticia();
			item.setAutor(autor);
			item.setDecricao(descricao);
			item.setFoto(foto.getBytes());
			item.setTitulo(titulo);
			item.setUrl(url);
			NoticiaController controller = NoticiaController.getInstance();
			controller.salvar(item);
			return "salvo";
		}catch(Exception e){
			return e.getMessage();
		}
	}
	
	//http://ismaelpecaseservicos-domaindev.rhcloud.com/autoService/rest/servicoWS/apagar/4
	/**
	 * Esse método lista todas noticias cadastradas na base
	 * */
	@GET
	@Path("/apagar/{codigo}/")
	@Produces("application/json")
	public String apagar(@PathParam("codigo") String codigo){
		
		try{
			NoticiaController controller = NoticiaController.getInstance();
			controller.apagar(codigo);
			return codigo;
		}catch(Exception e){
			return e.getMessage();
		}
	}


}
