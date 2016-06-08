package br.com.autoservice.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.autoservice.modelo.Marca;

import com.google.gson.Gson;

/**
 * @author robson.carlos.santos
 *
 * Controller Marcas
 */
public class MarcaController {

	private static MarcaController marcaController;
	
	private MarcaController(){
		
	}
	
	public static MarcaController getInstance() { 
		if (marcaController == null) {
			marcaController = new MarcaController(); 
		}
		return marcaController;
	}
	
	public List<Marca> getListaMarca(){
		Gson gson = new Gson();
		List<Marca> lista = new ArrayList<Marca>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("marcas.json"));
			// Converte String JSON para objeto Java
			Marca[] obj = gson.fromJson(br, Marca[].class);
			lista = Arrays.asList(obj);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

	

}
